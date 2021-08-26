package eapli.base.servicomanagement.domain.servico;


import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.catalogoservicemanagement.domain.Catalogo;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.framework.domain.model.DomainFactory;
import org.hibernate.mapping.Formula;

import java.util.Set;

/**
 * A factory Servico
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Rui Marinho
 */

public class ServicoBuilder implements DomainFactory<Servico> {


    private Catalogo catalogo;
    private CodigoUnico codigoUnico;
    private Titulo titulo;
    private DescricaoBreve descricaoBreve;
    private DescricaoCompleta descricaoCompleta;
    private Icone icone;
    private Set<PalavraChave> palavrasChave;
    private Formulario formulario;
    private EstadoServico estado;
    private Set<PalavraChave> keywords;

    private FluxoAprovacao fluxoAprovacao;
    private FluxoResolucao fluxoResolucao;

    //Atributotos FormularioPedido
    //Script Validacao FormularioPedido

    private Atributo atributo;

    public ServicoBuilder withCatalogo(Long idCatalogo) {
        //TODO rm Refactor idcatalogo
        //Identificador identificador = new Identificador(identificadorCatalogo);
        //this.catalogo =  PersistenceContext.repositories().catalog().findById(identificador).get();

        return this;
    }
    public ServicoBuilder withCatalogo (Catalogo catalogo) {
        this.catalogo = catalogo;
        return this;
    }
    public ServicoBuilder withCodigoUnico (String codigoUnico) {
        this.codigoUnico = new CodigoUnico(codigoUnico);
        return this;
    }
    public ServicoBuilder withCodigoUnico (CodigoUnico codigoUnico) {
        this.codigoUnico = codigoUnico;
        return this;
    }

    public ServicoBuilder withTitulo (String titulo) {
        this.titulo = new Titulo(titulo);
        return this;
    }

    public ServicoBuilder withTitulo (Titulo titulo) {
        this.titulo = titulo;
        return this;
    }

    public ServicoBuilder withDescricaoBreve (String descricaoBreve) {
        this.descricaoBreve = new DescricaoBreve(descricaoBreve);
        return this;
    }
    public ServicoBuilder withDescricaoBreve(DescricaoBreve descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
        return this;
    }

    public ServicoBuilder withDescricaoCompleta (String descricaoCompleta) {
        this.descricaoCompleta = new DescricaoCompleta(descricaoCompleta);
        return this;
    }
    public ServicoBuilder withDescricaoCompleta(DescricaoCompleta descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
        return this;
    }

    public ServicoBuilder withIcone (String icone) {
        this.icone = new Icone(icone);
        return this;
    }
    public ServicoBuilder withIcone(Icone icone) {
        this.icone = icone;
        return this;
    }

    public ServicoBuilder withFormulario(Formulario formulario) {
        this.formulario = formulario;
        return this;
    }

    public ServicoBuilder withPalavrasChaves(Set<PalavraChave> kw) {
        this.keywords = kw;
        return this;
    }

    public ServicoBuilder withFluxoAprovacao(String cargoResponsavel, Atividade atividade) {
        this.fluxoAprovacao = new FluxoAprovacao(cargoResponsavel, atividade);
        return this;
    }

    public ServicoBuilder withFluxoAprovacao(Set<Colaborador> aprovadores, Atividade atividade) {
        this.fluxoAprovacao = new FluxoAprovacao(aprovadores, atividade);
        return this;
    }


    public ServicoBuilder withFluxoResolucao(Atividade atividade) {
        this.fluxoResolucao = new FluxoResolucao(atividade);
        return this;
    }

    public ServicoBuilder withStatusIncompleted() {
        this.estado = EstadoServico.INCOMPLETO;
        return this;
    }

    public ServicoBuilder withStatusCompleted() {
        this.estado = EstadoServico.COMPLETO;
        return this;
    }

    @Override
    public Servico build() {

         Servico servico = new Servico(this.catalogo, this.codigoUnico);

         servico.addTitulo(titulo);
         servico.addDescricaoBreve(descricaoBreve);
         servico.addDescricaoCompleta(descricaoCompleta);
         servico.addIcone(icone);
         servico.addFormulario(formulario);
         servico.addFluxoAprovacao(fluxoAprovacao);
         servico.addFluxoResolucao(fluxoResolucao);

        for (PalavraChave pc : keywords) {
            servico.addPalavraChave(pc);
        }

         if (this.estado.equals(EstadoServico.COMPLETO)){
             servico.publicarServico();
         }

         return servico;
    }


}
