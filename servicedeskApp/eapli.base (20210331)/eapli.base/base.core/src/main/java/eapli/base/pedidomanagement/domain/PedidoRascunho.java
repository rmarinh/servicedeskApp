package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;

import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.validatorservice.formulario.FormularioParser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

@Entity
public class PedidoRascunho implements AggregateRoot<Long>, DTOable<PedidoRascunhoDTO> {

    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY)
    private Long pk;

    @Version
    private Long version;

    @ManyToOne
    private Servico servico;

    @OneToOne
    private Colaborador autor;

    @OneToOne
    private Colaborador benificiario;

    @Enumerated
    private UrgenciaPedido urgencia;

    @Temporal(TemporalType.DATE)
    private Calendar dataLimiteResolucao;

    @Lob
    private byte[] ficheirosEmAnexo;

    @Lob
    private String formularioPedido;

    private boolean submetido;

    protected PedidoRascunho() {
        // for ORM
    }

    public PedidoRascunho(final Colaborador autor,final Colaborador  benificiario, final Servico servico,
                          final UrgenciaPedido urgencia, final Calendar dataLimiteResolucao,
                          byte[] ficheirosEmAnexo, final String formularioPedido ) {
        Preconditions.noneNull(autor,benificiario ,servico);
        this.autor = autor;
        this.benificiario = benificiario;
        this.servico = servico;
        this.urgencia = urgencia;
        this.dataLimiteResolucao = dataLimiteResolucao;
        this.formularioPedido = formularioPedido;
        changeFicheirosEmAnexo(ficheirosEmAnexo);
        this.submetido = false;

    }

    public Servico servico(){
        return this.servico;
    }

    @Override
    public boolean sameAs(Object other) {
        if (other instanceof PedidoRascunho) {
            other = (PedidoRascunho) other;
            if (((PedidoRascunho) other).pk.equals(this.pk)) {
                return true;
            }
            return false;
        }
        return false;

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

    public Colaborador autorPedido() {
        return this.autor;
    }

    public Colaborador benificiarioPedido() {
        return this.benificiario;
    }

    public Formulario formulario(){
        return Formulario.xmlToFormulario(this.formularioPedido);

    }
    public String formularioPedido(){
        return this.formularioPedido;
    }
    public Set<Atributo> atributosFormulario(){
        return Formulario.xmlToFormulario(this.formularioPedido).atributos();

    }

    public boolean isSubmetido(){
        return this.submetido;
    }

    public boolean submeterPedido(){
        if (this.submetido == true){
            return false;
        }
        else{
            this.submetido = true;
            return true;
        }

    }

    public boolean preencherFormulario (Set<Atributo> atributosPreenchidos){
        String form =  Formulario.formularioToXML(new Formulario(this.formulario().nome(), atributosPreenchidos,formulario().scriptValidacao()));
        final FormularioParser parser = new FormularioParser(form);
        parser.parse();
        if(parser.parsedwithSuccess()){
            this.formularioPedido = parser.getParsedForm();
            parser.executeScript();
            return true;
        }else {
            parser.printErrors();
            return false;
        }
    }

    @Override
    public Long identity() {
        return pk;
    }

    @Override
    public PedidoRascunhoDTO toDTO() {
        return new PedidoRascunhoDTO(this.pk, this.version, this.autor.identity(),
                            this.servico.identity().toString(), this.urgencia.toString(),
                            this.dataLimiteResolucao.getTime().toString());
    }
}
