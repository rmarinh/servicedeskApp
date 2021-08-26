package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * A acronimo do Nivel de Criticidade.
 *
 * Esta class representa um desginacao ou descricao que identifica um Nivel de Criticidade.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class Acronimo implements ValueObject, Comparable<Acronimo>{

    private static final long serialVersionUID = 1L;

    private String acronimo;

    public Acronimo(final String acronimo) {
        if (StringPredicates.isNullOrEmpty(acronimo)) {
            throw new IllegalArgumentException(
                    "Identifier acronimo should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier acronimo regular
        // expression
        this.acronimo = acronimo;
    }

    protected Acronimo() {
        // for ORM
    }

    public static Acronimo valueOf(final String acronimo) {
        return new Acronimo(acronimo);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acronimo)) {
            return false;
        }

        final Acronimo that = (Acronimo) o;
        return this.acronimo.equals(that.acronimo);
    }

    @Override
    public int hashCode() {
        return this.acronimo.hashCode();
    }

    @Override
    public String toString() {
        return this.acronimo;
    }

    @Override
    public int compareTo(final Acronimo arg0) {
        return acronimo.compareTo(arg0.acronimo);
    }
}
