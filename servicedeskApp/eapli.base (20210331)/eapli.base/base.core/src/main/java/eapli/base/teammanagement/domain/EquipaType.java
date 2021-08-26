package eapli.base.teammanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
public class EquipaType implements Serializable, AggregateRoot<Long>, DTOable<EquipaTypeDTO> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long id;

    private Cor corTipoEquipa;

    @Column(nullable = false)
    private Designacao descricao;

    private boolean active;

    protected EquipaType() {
        // for ORM
    }

    /**
     * EquipaType constructor.
     *
     * @param descricao
     *            Mandatory
     * @param cor
     *            Mandatory
     */
    public EquipaType(final Designacao descricao, final Cor cor) {

        if(descricao == null || cor == null){
            throw new IllegalArgumentException("Erro na criação do tipo de Equipa: Descrição ou cor vazia.");
        }
        this.descricao= descricao;
        this.active = true;
        this.corTipoEquipa = cor;
    }

    public EquipaType(final Designacao descricao) {

        if(descricao == null){
            throw new IllegalArgumentException("Erro na criação do tipo de Equipa: Descrição vazia.");
        }
        this.descricao= descricao;
    }

    public EquipaType(Long id, final Designacao descricao, final Cor cor) {
        this.id = id;
        this.descricao = descricao;
        this.corTipoEquipa = cor;
    }

    public Designacao descricao() {
        return this.descricao;
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the dishtype, activating it or deactivating it
     * accordingly.
     *
     * @return whether the dishtype is active or not
     */
    public boolean toogleState() {
        this.active = !this.active;
        return isActive();
    }

    /**
     * Change EquipaType description
     *
     * @param novaDescricao
     *            New description.
     */
    public void changeDescriptionTo(final String novaDescricao) {
        if (novaDescricao.isEmpty()) {
            throw new IllegalArgumentException("A nova descrição não pode ser vazia.");
        }
        this.descricao = new Designacao(novaDescricao);
    }

    @Override
    public boolean hasIdentity(final Long id) {
        return id.equals(this.id);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    public Designacao designacao() {
        return this.descricao;
    }

    public Cor cor() {
        return this.corTipoEquipa;
    }

    @Override
    public boolean sameAs(final Object other) {
        final EquipaType equipaType = (EquipaType) other;
        return this.equals(equipaType) && descricao().equals(equipaType.descricao())
                && isActive() == equipaType.isActive();
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    //public TeamTypeDTO toDTO() {
    //    return new TeamTypeDTO(acronym, description, active);
    //}

    @Override
    public String toString() {
        return identity().toString();
    }

    @Override
    public EquipaTypeDTO toDTO() {
        return new EquipaTypeDTO(this.descricao.toString(), this.corTipoEquipa.toString());
    }
}
