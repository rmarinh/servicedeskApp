package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O titulo do Servico.
 *
 * Esta class representa um titulo que identifica um Servico.
 *
 * @author Rui Marinho 1171602@isep.ipp.pt
 *
 */
@Embeddable
public class Titulo implements ValueObject, Comparable<Titulo>{

    private static final long serialVersionUID = 1L;

    private String titulo;

    public Titulo(final String titulo) {
        if (StringPredicates.isNullOrEmpty(titulo)) {
            throw new IllegalArgumentException(
                    "Title for Service should neither be null nor empty");
        }
        // TODO RM  validate Max Lenght
        // expression
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
    public int compareTo(final Titulo arg0) {
        return titulo.compareTo(arg0.titulo);
    }
}