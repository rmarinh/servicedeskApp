package eapli.base.catalogoservicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * O Icone do Servico.
 *
 * Esta class representa um Icone que identifica um Catalogo.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@Embeddable
public class Icone implements ValueObject, Comparable<Icone>{

    private static final long serialVersionUID = 1L;

    private String icone;

    public Icone(final String icone) {
        Preconditions.nonEmpty(icone, "O icone do Catalogo não deve estar vazio!");
        // TODO: Validar o Icone
        //Preconditions.ensure(StringRegex.isDescricaoBreveCatalogo(descricaoBreve), "A descricao breve do Catalogo é representada por um frase curta com um maximo de 30 caracteres!");
        // expression
        this.icone = icone;
    }

    protected Icone() {
        // for ORM
    }

    public static Icone valueOf(final String icone) {
        return new Icone(icone);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Icone)) {
            return false;
        }

        final Icone that = (Icone) o;
        return this.icone.equals(that.icone);
    }

    @Override
    public int hashCode() {
        return this.icone.hashCode();
    }

    @Override
    public String toString() {
        return this.icone;
    }

    @Override
    public int compareTo(final Icone otherIcone) {
        return this.icone.compareTo(otherIcone.icone);
    }
}
