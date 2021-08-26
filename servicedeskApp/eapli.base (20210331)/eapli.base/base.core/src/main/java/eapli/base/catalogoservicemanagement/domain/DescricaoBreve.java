package eapli.base.catalogoservicemanagement.domain;

import eapli.base.settings.regex.StringRegex;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * A descricao breve do Catalogo.
 *
 * Esta class representa uma drescricao breve de um catalogo.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@Embeddable
public class DescricaoBreve implements ValueObject, Comparable<DescricaoBreve>{

    private static final long serialVersionUID = 1L;

    private String descricaoBreve;

    public DescricaoBreve(final String descricaoBreve) {
        Preconditions.nonEmpty(descricaoBreve, "A descrição breve do Catalogo não deve estar vazia!");
        Preconditions.ensure(StringRegex.isDescricaoBreveCatalogo(descricaoBreve), "A descricao breve do Catalogo é representada por um frase curta com um maximo de 30 caracteres!");
        this.descricaoBreve = descricaoBreve;
    }

    protected DescricaoBreve() {
        // for ORM
    }

    public static DescricaoBreve valueOf(final String descricaoBreve) {
        return new DescricaoBreve(descricaoBreve);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DescricaoBreve)) {
            return false;
        }

        final DescricaoBreve that = (DescricaoBreve) o;
        return this.descricaoBreve.equals(that.descricaoBreve);
    }

    @Override
    public int hashCode() {
        return this.descricaoBreve.hashCode();
    }

    @Override
    public String toString() {
        return this.descricaoBreve;
    }

    @Override
    public int compareTo(final DescricaoBreve otherDescricaoBreve) {
        return this.descricaoBreve.compareTo(otherDescricaoBreve.descricaoBreve);
    }
}
