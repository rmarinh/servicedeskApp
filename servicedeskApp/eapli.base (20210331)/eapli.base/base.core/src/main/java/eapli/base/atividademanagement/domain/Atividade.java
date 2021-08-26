package eapli.base.atividademanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ATIVIDADE_TYPE")
public abstract class Atividade implements Serializable, AggregateRoot<Long> {

    @Version
    private Long version;

    //Informacao base do Servico

    @Id
    @GeneratedValue
    private Long identificador;

    @Embedded
    private DescricaoBreve descricaoBreve;

    protected Atividade(final DescricaoBreve descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
    }

    protected Atividade() {
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

    @Override
    public Long identity() {
        return this.identificador;
    }
    
    public DescricaoBreve descricaoBreve() {
        return this.descricaoBreve;
    }

    @Override
    public String toString() {
        return "Atividade {" +
                "descricaoBreve=" + descricaoBreve +
                '}';
    }

}
