package eapli.base.catalogoservicemanagement.domain;

import eapli.base.catalogoservicemanagement.domain.dto.CatalogoDTO;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.teammanagement.domain.Equipa;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import java.io.Serializable;

/**
 * O Catalogo.
 *
 * Esta class representa um catalogo.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@Entity
public class Catalogo implements Serializable, AggregateRoot<Long>, DTOable<CatalogoDTO> {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long identificador;

    private Titulo titulo;
    private DescricaoBreve descricaoBreve;
    private DescricaoCompleta descricaoCompleta;
    private Icone icone;

    @OneToOne
    private Colaborador responsavel;
    @OneToOne
    private NiveisCriticidade nivelCriticidade;

    @ManyToMany
    @JoinTable(name = "equipa_criterios")
    private Set<Equipa> criterios = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "equipas_resolucao")
    private Set<Equipa> equipasResolucao = new HashSet<>();

    public Catalogo(final Titulo titulo, final DescricaoBreve descricaoBreve,
                    final DescricaoCompleta descricaoCompleta, final Icone icone,
                    final Colaborador responsavel, final NiveisCriticidade nivelCriticidade,
                    final Set<Equipa> criterios, final Set<Equipa> equipasResolucao) {

        Preconditions.noneNull(titulo, "A titulo do Catalogo não deve estar vazio!");
        Preconditions.noneNull(descricaoBreve, "A descricao breve do Catalogo não deve estar vazia!");
        Preconditions.noneNull(descricaoCompleta, "A descricao completa do Catalogo não deve estar vazia!");
        Preconditions.noneNull(icone, "O icone do Catalogo não deve estar vazio!");
        Preconditions.noneNull(responsavel, "Deve introduzir um colbaborador responsavel do Catalogo valido!");
        Preconditions.noneNull(nivelCriticidade, "Deve introduzir um nivdel de criticidade do Catalogo valido!");
        Preconditions.nonEmpty(criterios, "Deve introduzir pelo menos uma equipa como criterio no Catalogo!");
        Preconditions.nonEmpty(criterios, "Deve introduzir pelo menos uma equipa como executora de pedidos relacionados com o Catalogo!");

        this.titulo = titulo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoCompleta = descricaoCompleta;
        this.icone = icone;
        this.responsavel = responsavel;
        this.nivelCriticidade = nivelCriticidade;
        this.criterios = new HashSet<>(criterios);
        this.equipasResolucao = new HashSet<>(equipasResolucao);
    }

    protected Catalogo() {
        // for ORM only
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

    public Long identificador() {
        return identity();
    }

    public Titulo titulo() {
        return this.titulo;
    }

    public DescricaoBreve descricaoBreve() {
        return this.descricaoBreve;
    }

    public DescricaoCompleta descricaoCompleta() {
        return this.descricaoCompleta;
    }

    public Icone icone() {
        return this.icone;
    }

    public Colaborador responsavel() {
        return this.responsavel;
    }

    public NiveisCriticidade nivelCriticidade() {
        return this.nivelCriticidade;
    }

    public Set<Equipa> criterios() {
        return new HashSet<>(this.criterios);
    }

    public Set<Equipa> equipasResolucao() {
        return new HashSet<>(this.equipasResolucao);
    }

    public void changeNivelCriticidade(NiveisCriticidade nivelCriticidade) {
        this.nivelCriticidade = nivelCriticidade;
    }

    @Override
    public Long identity() {
        return this.identificador;
    }

    @Override
    public String toString() {
        // TODO: Falta imprimir correctamente os nivels de criticidade para sempre mais legiveis
        return "Catalogo{" +
                "version=" + this.version +
                ", identificador=" + this.identificador +
                ", titulo=" + this.titulo +
                ", descricao breve=" + this.descricaoBreve +
                ", descricao completa=" + this.descricaoCompleta +
                ", icone=" + this.icone +
                ", colaborador responsavel=" + ((this.responsavel == null) ? "N/A" : this.responsavel.mecanographicNumber()) +
                ", nivel de criticidade=" + ((this.nivelCriticidade == null) ? "N/A" : this.nivelCriticidade.etiqueta()) +
                ", criterios==" + ((this.criterios.isEmpty()) ? "N/A" : this.criterios) +
                '}';
    }

    @Override
    public CatalogoDTO toDTO() {
        CatalogoDTO catalogoDTO = new CatalogoDTO(this.identificador, titulo.toString(), descricaoBreve.toString(),
        descricaoCompleta.toString());

        return catalogoDTO;
    }
}
