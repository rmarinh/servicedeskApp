package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O Icone do Servico.
 *
 * Esta class representa um Icone que identifica um Servico.
 *
 * @author Rui Marinho 1171602@isep.ipp.pt
 *
 */
@Embeddable
public class Icone implements ValueObject, Comparable<Icone>{

    private static final long serialVersionUID = 1L;

    private String icone;

    public Icone(final String Icone) {
        if (StringPredicates.isNullOrEmpty(Icone)) {
            throw new IllegalArgumentException(
                    "Title for Service should neither be null nor empty");
        }
        // expression
        this.icone = Icone;
    }

    protected Icone() {
        // for ORM
    }

    public static Icone valueOf(final String Icone) {
        return new Icone(Icone);
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
    public int compareTo(final Icone arg0) {
        return icone.compareTo(arg0.icone);
    }
}
