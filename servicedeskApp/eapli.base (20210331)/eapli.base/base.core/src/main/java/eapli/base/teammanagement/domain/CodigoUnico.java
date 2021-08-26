package eapli.base.teammanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class CodigoUnico implements ValueObject, Comparable<CodigoUnico> {

    private static final long serialVersionUID = 1L;

    private String number;

    public CodigoUnico(final String CodigoUnico) {
        if (StringPredicates.isNullOrEmpty(CodigoUnico)) {
            throw new IllegalArgumentException(
                    "Mecanographic Number should neither be null nor empty");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.number = CodigoUnico;
    }

    protected CodigoUnico() {
        // for ORM
    }

    public static CodigoUnico valueOf(final String CodigoUnico) {
        return new CodigoUnico(CodigoUnico);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodigoUnico)) {
            return false;
        }

        final CodigoUnico that = (CodigoUnico) o;
        return this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return this.number.hashCode();
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final CodigoUnico arg0) {
        return number.compareTo(arg0.number);
    }
}

