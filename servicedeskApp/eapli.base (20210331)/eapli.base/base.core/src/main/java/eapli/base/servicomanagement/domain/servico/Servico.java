package eapli.base.servicomanagement.domain.servico;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.formulario.Formulario;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta class representa um Servico.
 *
 *  Um serviço possui um código único, um título, uma descrição breve e outro
 *  completa, um ícone, um conjunto de palavras-chave (keywords) de pesquisa e o
 *  catálogo onde é disponibilizado. Assim como um FormularioPedido de requisição e um fluxo de atividades
 *
 * @author Rui Marinho 1171602@isep.ipp.pt
 *
 *
 */



@Entity
public class Servico implements Serializable, AggregateRoot<CodigoUnico>, DTOable<ServicoDTO> {

    @Version
    private Long version;

    @Id
    private CodigoUnico codigoUnico;

    @Lob
    private String formulario;

    @OneToOne
    private Catalogo catalogo;

    @Enumerated(EnumType.STRING)     @Column(nullable = false)
    private EstadoServico estadoServico;

    @Embedded
    private Titulo titulo;

    @Embedded
    private DescricaoBreve descricaoBreve;

    @Embedded
    private DescricaoCompleta descricaoCompleta;

    @Embedded
    private Icone icone;

    @ElementCollection
    private Set<PalavraChave> palavrasChave;

    //FluxoAtividades
    @OneToOne(cascade = CascadeType.ALL)
    private FluxoAprovacao fluxoAprovacao;

    @OneToOne(cascade = CascadeType.ALL)
    private FluxoResolucao fluxoResolucao;

    @OneToOne
    private NiveisCriticidade nivelCrtiticidade;

    protected Servico(final Catalogo catalogo, final CodigoUnico codigoUnico) {
        Preconditions.noneNull(catalogo, codigoUnico);
        //Initialize as Empty Params
        this.catalogo = catalogo;
        this.codigoUnico = codigoUnico;
        this.titulo = null;
        this.descricaoBreve = null;
        this.descricaoCompleta = null;
        this.icone = null;
        this.palavrasChave = new HashSet<>();
        this.formulario = null;
        this.nivelCrtiticidade = catalogo.nivelCriticidade();
        this.estadoServico = EstadoServico.INCOMPLETO;
    }

    protected Servico() {
        // for ORM only
    }

    void addTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    void addDescricaoBreve(DescricaoBreve descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
    }

    void addDescricaoCompleta(DescricaoCompleta descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    void addIcone(Icone icone) {
        this.icone = icone;
    }

    void addPalavraChave(PalavraChave palavraChave) {
        this.palavrasChave.add(palavraChave);
    }

    void addFormulario(Formulario formulario) {
        if (formulario != null) {
            this.formulario = Formulario.formularioToXML(formulario);
        }else
            this.formulario = null;
    }

    void addFluxoAprovacao(FluxoAprovacao fluxoAprovacao) {
        this.fluxoAprovacao = fluxoAprovacao;
    }
    void addFluxoResolucao(FluxoResolucao fluxoResolucao) {
        this.fluxoResolucao = fluxoResolucao;
    }

    private void setEstadoServico(EstadoServico estadoServico) {
        this.estadoServico = estadoServico;
    }

    public boolean isPublicado() {
        return EstadoServico.COMPLETO.equals(this.estadoServico);
    }
    public boolean isIncompleto() {
        return EstadoServico.INCOMPLETO.equals(this.estadoServico);
    }

    public boolean publicarServico(){
        if (isPublicado()) return true;
        else {
            if(isValid()) {
               setEstadoServico(EstadoServico.COMPLETO);
            }
            return false;
        }

    }

    //TODO -RM - Validacao do SServico Completo
    private boolean isValid(){
        return (
                (catalogo != null) &&
                (codigoUnico != null) &&
                (titulo != null) &&
                (descricaoBreve != null) &&
                (descricaoCompleta != null) &&
                (icone != null) &&
                (palavrasChave != null)
                );
    }

    public FluxoAprovacao getFluxoAprovacao() {
        return this.fluxoAprovacao;
    }

    public FluxoResolucao getFluxoResolucao() {
        return this.fluxoResolucao;
    }

    public Set<PalavraChave> palavraChaves() {
        return this.palavrasChave;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public CodigoUnico codigoUnico() {
        return identity();
    }

    public Catalogo catalogo() {
        return this.catalogo;
    }

    public DescricaoBreve descricaoBreve() {
        return this.descricaoBreve;
    }

    public DescricaoCompleta descricaoCompleta() {
        return this.descricaoCompleta;
    }

    public Formulario formulario() {
        return Formulario.xmlToFormulario(this.formulario);
    }

    public String formularioXML() {
        return this.formulario;
    }

    public NiveisCriticidade nivelCrtiticidade(){
        return this.nivelCrtiticidade;
    }

    @Override
    public CodigoUnico identity() {
        return this.codigoUnico;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "codigoUnico=" + codigoUnico +
                "catalogo=" + catalogo +
                "codigoUnico=" + codigoUnico +
                "titulo=" + titulo +
                "descricaoBreve=" + descricaoBreve +
                "descricaoCompleta=" + descricaoCompleta +
                "icone=" + icone +
                "palavrasChave=" + palavrasChave +
                '}';
    }

    @Override
    public ServicoDTO toDTO() {
        return new ServicoDTO(this.identity().toString(), this.titulo.toString(), this.descricaoBreve.toString(), this.descricaoCompleta.toString(), this.icone.toString());
    }
}
