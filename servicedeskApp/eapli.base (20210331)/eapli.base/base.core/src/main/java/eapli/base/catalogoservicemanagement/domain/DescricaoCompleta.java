package eapli.base.catalogoservicemanagement.domain;

import eapli.base.settings.regex.StringRegex;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * A descricao completa do Catalogo.
 *
 * Esta class representa uma drescricao completa de um catalogo.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@Embeddable
public class DescricaoCompleta implements ValueObject, Comparable<DescricaoCompleta>{

    private static final long serialVersionUID = 1L;

    private String descricaoCompleta;

    public DescricaoCompleta(final String descricaoCompleta) {
        Preconditions.nonEmpty(descricaoCompleta, "A descrição completa do Catalogo não eve estar vazia!");
        Preconditions.ensure(StringRegex.isDescricaoCompletaCatalogo(descricaoCompleta), "A descricao completa do Catalogo é representada por um frase longa com um maximo de 100 caracteres!");
        this.descricaoCompleta = descricaoCompleta;
    }

    protected DescricaoCompleta() {
        // for ORM
    }

    public static DescricaoCompleta valueOf(final String descricaoCompleta) {
        return new DescricaoCompleta(descricaoCompleta);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoCompleta)) {
            return false;
        }

        final DescricaoCompleta that = (DescricaoCompleta) o;
        return this.descricaoCompleta.equals(that.descricaoCompleta);
    }

    @Override
    public int hashCode() {
        return this.descricaoCompleta.hashCode();
    }

    @Override
    public String toString() {
        return this.descricaoCompleta;
    }

    @Override
    public int compareTo(final DescricaoCompleta otherDescricaoCompleta) {
        return this.descricaoCompleta.compareTo(otherDescricaoCompleta.descricaoCompleta);
    }
}
