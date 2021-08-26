package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoCompleta implements ValueObject, Comparable<DescricaoCompleta>{
    private static final long serialVersionUID = 1L;

    private String descricaoCompleta;

    public DescricaoCompleta(final String descricaoCompleta) {
        if (StringPredicates.isNullOrEmpty(descricaoCompleta)) {
            throw new IllegalArgumentException(
                    "Identifier descricaoCompleta for Servico should neither be null nor empty");
        }
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
    public int compareTo(final DescricaoCompleta arg0) {
        return descricaoCompleta.compareTo(arg0.descricaoCompleta);
    }
}
