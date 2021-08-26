package eapli.base.atividademanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoBreve implements ValueObject, Comparable<DescricaoBreve>{
    private static final long serialVersionUID = 1L;

    private String descricaoBreve;

    public DescricaoBreve(final String descricaoBreve) {
        if (StringPredicates.isNullOrEmpty(descricaoBreve)) {
            throw new IllegalArgumentException(
                    "Identifier descricaoBreve for Servico should neither be null nor empty");
        }
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
    public int compareTo(final DescricaoBreve arg0) {
        return descricaoBreve.compareTo(arg0.descricaoBreve);
    }
}
