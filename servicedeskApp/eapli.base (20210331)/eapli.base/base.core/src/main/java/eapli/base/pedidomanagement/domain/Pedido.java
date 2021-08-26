package eapli.base.pedidomanagement.domain;

import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.validatorservice.formulario.FormularioParser;
import eapli.base.validatorservice.script.ParserScript;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.time.util.Calendars;
import eapli.framework.validations.Invariants;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.*;

@Entity
public class Pedido implements AggregateRoot<Long>, DTOable<PedidoDTO> {
    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY)
    private Long pk;

    @Version
    private Long version;

    // business id
    @Column(unique = true, nullable = false)
    private PedidoToken token;

    @ManyToOne()
    private Colaborador colaborador;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdOn;

    @ManyToOne()
    private Servico servico;

    @ManyToOne()
    private NiveisCriticidade nivelCriticidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PedidoStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<AbstractPedidoEvent> events = new HashSet<>();

    @Lob
    private String formularioPedido;


    @Lob
    private byte[] ficheirosEmAnexo;


    protected Pedido() {
        // for ORM
    }
    public Pedido(final Colaborador colaborador, final Servico what) {
        Preconditions.noneNull(colaborador, what);

        this.colaborador = colaborador;
        this.servico = what;
        this.nivelCriticidade = servico.nivelCrtiticidade();
        this.token = PedidoToken.newToken();
        this.createdOn = Calendars.now();
        final SubmetidoPedidoEvent pedidoEvent = new SubmetidoPedidoEvent(this);
        this.events.add(pedidoEvent);
        this.status = PedidoStatus.SUBMETIDO;
        this.formularioPedido = null;
        changeFicheirosEmAnexo(new byte[0]);

    }


    public Pedido(final Colaborador colaborador, final Servico what, final String formularioPedido, byte[] ficheirosEmAnexo) {
        Preconditions.noneNull(colaborador, what);

        this.colaborador = colaborador;
        this.servico = what;
        this.nivelCriticidade = servico.nivelCrtiticidade();
        this.token = PedidoToken.newToken();
        this.createdOn = Calendars.now();
        final SubmetidoPedidoEvent pedidoEvent = new SubmetidoPedidoEvent(this);
        this.events.add(pedidoEvent);
        this.status = PedidoStatus.SUBMETIDO;
        this.formularioPedido = formularioPedido;
        changeFicheirosEmAnexo(ficheirosEmAnexo);
    }

    @Override
    public boolean sameAs(Object other) {
        if (other instanceof Pedido) {
            other = (PedidoRascunho) other;
            if (((Pedido) other).pk.equals(this.pk)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Long identity() {
        return pk;
    }

    public PedidoStatus status() {
        return status;
    }

    public Servico servico() {
        return this.servico;
    }

    public PedidoToken token() {
        return this.token;
    }

    public Colaborador colaborador() {
        return this.colaborador;
    }

    public boolean isSubmetido() {
        return status() == PedidoStatus.SUBMETIDO;
    }

    public boolean isAprovado() {
        return status() == PedidoStatus.APROVADO;
    }

    public boolean isRejeitado() {
        return status() == PedidoStatus.REJEITADO;
    }

    public boolean isEmExecucao() {
        return status() == PedidoStatus.EM_EXECUCAO;
    }

    public boolean isConcluido() {
        return status() == PedidoStatus.CONCLUIDO;
    }

    public EmAprovacaoPedidoEvent emAprovacaoPedidoEvent(Colaborador colaborador) {
        Invariants.ensure(this.status == PedidoStatus.SUBMETIDO,
                "So pode estar em aprovacao se estiver em estado de submetido");

        this.status = PedidoStatus.EM_APROVACAO;
        final EmAprovacaoPedidoEvent emExecucaoPedido = new EmAprovacaoPedidoEvent(this, colaborador);
        this.events.add(emExecucaoPedido);

        return emExecucaoPedido;
    }

    public AprovadoPedidoEvent aprovarPedido(Colaborador colaborador) {
        Invariants.ensure(this.status == PedidoStatus.EM_APROVACAO,
                "So pode ser aprovado se estiver em estado de em aprovacao");

        this.status = PedidoStatus.APROVADO;
        final AprovadoPedidoEvent aprovarPedido = new AprovadoPedidoEvent(this, colaborador);
        events.add(aprovarPedido);

        return aprovarPedido;
    }

    public Formulario formularioAprovacao(){
      Atividade atividade = this.servico.getFluxoAprovacao().getAtividade();
      if (atividade instanceof AtividadeManual){
         return ((AtividadeManual) atividade).formulario();
      }
      else
          return null;
    }
    public Formulario formularioExecucao(){
        Atividade atividade = this.servico.getFluxoResolucao().getAtividade();;
        if (atividade instanceof AtividadeManual){
            return ((AtividadeManual) atividade).formulario();
        }
        else
            return null;
    }



    public AprovadoPedidoEvent aprovarPedido(Colaborador colaborador, String formularioAprovacao) {
        Invariants.ensure(this.status == PedidoStatus.EM_APROVACAO,
                "So pode ser aprovado se estiver em estado de em aprovacao");
        final FormularioParser parser = new FormularioParser(formularioAprovacao);
        parser.parse();
        if(parser.parsedwithSuccess()){
            parser.executeScript();
            this.status = PedidoStatus.APROVADO;
            final AprovadoPedidoEvent aprovarPedido = new AprovadoPedidoEvent(this, colaborador, parser.getParsedForm());
            events.add(aprovarPedido);
            return aprovarPedido;
        }else {
            parser.printErrors();
            return null;
        }
    }

    public RejeitarPedidoEvent recusarPedido(Colaborador colaborador) {
        Invariants.ensure(this.status == PedidoStatus.EM_APROVACAO,
                "So pode ser recusado se estiver em estado de submetido");

        this.status = PedidoStatus.REJEITADO;
        final RejeitarPedidoEvent rejeitarPedido = new RejeitarPedidoEvent(this, colaborador);
        events.add(rejeitarPedido);

        return rejeitarPedido;
    }

    public EmExecucaoPedidoEvent executarPedidoEvent(Colaborador colaborador) {
        Invariants.ensure(this.status == PedidoStatus.APROVADO,
                "So pode ser executado se estiver em estado de aprovado");

        this.status = PedidoStatus.EM_EXECUCAO;

        final EmExecucaoPedidoEvent emExecucaoPedido = new EmExecucaoPedidoEvent(this, colaborador);
        this.events.add(emExecucaoPedido);

        return emExecucaoPedido;
    }

    public ConcluidoPedidoEvent concluirPedidoEvent() {
        Invariants.ensure(this.status == PedidoStatus.EM_EXECUCAO,
                "So pode ser concluido se estiver em estado de em execucao");

        //Executar Parser
        //this.servico.getFluxoResolucao().executar();

        this.status = PedidoStatus.CONCLUIDO;
        if ((this.servico.getFluxoResolucao().getAtividade()) instanceof AtividadeAutomatica){
            String script = ((AtividadeAutomatica)this.servico.getFluxoResolucao().getAtividade()).Script();
            ParserScript.parse(script);
        }
        final ConcluidoPedidoEvent concluidoPedido = new ConcluidoPedidoEvent(this);
        this.events.add(concluidoPedido);

        return concluidoPedido;
    }
    public ConcluidoPedidoEvent concluirPedidoEvent(String formularioExecucao) {
        Invariants.ensure(this.status == PedidoStatus.EM_EXECUCAO,
                "So pode ser concluido se estiver em estado de em execucao");

        final FormularioParser parser = new FormularioParser(formularioExecucao);
        parser.parse();
        if(parser.parsedwithSuccess()){
            parser.executeScript();
            this.status = PedidoStatus.CONCLUIDO;
            final ConcluidoPedidoEvent concluidoPedido = new ConcluidoPedidoEvent(this,parser.getParsedForm());
            this.events.add(concluidoPedido);
            return concluidoPedido;
        }else {
            parser.printErrors();
            return null;
        }

    }

    public Long tempoDecorrido() {
        Date dataNow = Calendars.now().getTime();
        Date dataSubmissao = Collections.unmodifiableSet(events).iterator().next().when().getTime();

        long tempoDecorrido = dataNow.getTime() - dataSubmissao.getTime();

        tempoDecorrido = tempoDecorrido / 1000;
        tempoDecorrido = tempoDecorrido / 60;
        tempoDecorrido = tempoDecorrido / 60;

        return tempoDecorrido;
    }

    public Long tempoDecorridoAprovacao() {
        Date dataEmAprovacao = Calendar.getInstance().getTime();
        Date dataAprovado = Calendar.getInstance().getTime();

        for (AbstractPedidoEvent e : events){
            if(e instanceof EmAprovacaoPedidoEvent){
                dataEmAprovacao = e.when().getTime();
            }else if (e instanceof AprovadoPedidoEvent){
                dataAprovado = e.when().getTime();
            }
        }

        long tempoDecorrido = dataAprovado.getTime() - dataEmAprovacao.getTime();

        tempoDecorrido = tempoDecorrido / 1000;
        tempoDecorrido = tempoDecorrido / 60;
        tempoDecorrido = tempoDecorrido / 60;

        return tempoDecorrido;
    }

    public boolean cumprimentoSLA(){
        // Return true se o SLA esta em cumprimento ou false se está em incumprimento
        long tempoMaximoResolucao = Long.parseLong(this.nivelCriticidade.tempoMaximoResolucao().toString());
        long tempoMaximoAprovacao = Long.parseLong(this.nivelCriticidade.tempoMaximoAprovacao().toString());

        if(tempoDecorrido() > tempoMaximoResolucao || tempoDecorridoAprovacao() > tempoMaximoAprovacao){
            return false;
        }else{
            return true;
        }
    }
    public byte[] ficheirosEmAnexo() {
        // defensive copy
        return Arrays.copyOf(ficheirosEmAnexo, ficheirosEmAnexo.length);
    }

    public void changeFicheirosEmAnexo(final byte[] ficheirosEmAnexo) {
        // defensive copy
        this.ficheirosEmAnexo = Arrays.copyOf(ficheirosEmAnexo, ficheirosEmAnexo.length);
    }

    public boolean hasFicheirosEmAnexo() {
        return ficheirosEmAnexo != null && ficheirosEmAnexo.length != 0;
    }


    public Set<AbstractPedidoEvent> history() {
        return Collections.unmodifiableSet(events);
    }




    @Override
    public String toString() {
        return "Pedido{" +
                "pk=" + pk +
                ", version=" + version +
                ", token=" + token +
                ", colaborador=" + colaborador +
                ", servico=" + servico +
                ", status=" + status +
                ", events=" + events +
                '}';
    }

    @Override
    public PedidoDTO toDTO() {
        return new PedidoDTO(this.pk, this.version, this.token.toString(), this.colaborador.identity(),
                this.servico.identity().toString(), this.nivelCriticidade.identificador(), this.status.toString());  }
}
