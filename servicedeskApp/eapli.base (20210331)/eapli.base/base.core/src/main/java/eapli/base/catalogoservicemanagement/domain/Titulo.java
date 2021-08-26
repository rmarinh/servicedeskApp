package eapli.base.catalogoservicemanagement.domain;

import eapli.base.settings.regex.StringRegex;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * O titulo do Catalogo.
 *
 * Esta class representa um titulo que identifica um catalogo.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@Embeddable
public class Titulo implements ValueObject, Comparable<Titulo>{

    private static final long serialVersionUID = 1L;

    private String titulo;

    public Titulo(final String titulo) {
        Preconditions.nonEmpty(titulo, "O titulo do Catalogo não deve estar vazia!");
        Preconditions.ensure(StringRegex.isTituloCatalogo(titulo), "O titulo do Catalogo é representada por um frase curta com um maximo de 25 caracteres!");
        this.titulo = titulo;
    }

    protected Titulo() {
        // for ORM
    }

    public static Titulo valueOf(final String titulo) {
        return new Titulo(titulo);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Titulo)) {
            return false;
        }

        final Titulo that = (Titulo) o;
        return this.titulo.equals(that.titulo);
    }

    @Override
    public int hashCode() {
        return this.titulo.hashCode();
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    @Override
    public int compareTo(final Titulo otherTitulo) {
        return titulo.compareTo(otherTitulo.titulo);
    }
}
