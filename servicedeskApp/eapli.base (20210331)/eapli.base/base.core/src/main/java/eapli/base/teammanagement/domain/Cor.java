package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * A cor do Nivel de Criticidade.
 *
 * Esta class representa uma cor de um Nivel de Criticidade.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class Cor implements ValueObject, Comparable<Cor>{

    private static final long serialVersionUID = 1L;

    private String cor;

    public Cor(final String cor) {
        if (StringPredicates.isNullOrEmpty(cor)) {
            throw new IllegalArgumentException(
                    "Identifier cor for SLA should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier cor regular
        // expression
        this.cor = cor;
    }

    protected Cor() {
        // for ORM
    }

    public static Cor valueOf(final String cor) {
        return new Cor(cor);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cor)) {
            return false;
        }

        final Cor that = (Cor) o;
        return this.cor.equals(that.cor);
    }

    @Override
    public int hashCode() {
        return this.cor.hashCode();
    }

    @Override
    public String toString() {
        return this.cor;
    }

    @Override
    public int compareTo(final Cor arg0) {
        return cor.compareTo(arg0.cor);
    }
}
