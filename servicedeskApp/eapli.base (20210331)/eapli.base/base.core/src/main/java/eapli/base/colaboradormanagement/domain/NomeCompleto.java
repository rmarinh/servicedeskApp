package eapli.base.colaboradormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O nome completo do Colaborador.
 * <p>
 * Esta class representa o nome completo de um Colaborador.
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
@Embeddable
public class NomeCompleto implements ValueObject, Comparable<NomeCompleto> {

    private static final long serialVersionUID = 1L;

    private String nomeCompleto;

    public NomeCompleto(final String nomeCompleto) {
        if (StringPredicates.isNullOrEmpty(nomeCompleto)) {
            throw new IllegalArgumentException(
                    "Identifier nomeCompleto for Catalog should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier nomeCompleto regular
        // expression
        this.nomeCompleto = nomeCompleto;
    }

    protected NomeCompleto() {
        // for ORM
    }

    public static NomeCompleto valueOf(final String nomeCompleto) {
        return new NomeCompleto(nomeCompleto);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NomeCompleto)) {
            return false;
        }

        final NomeCompleto that = (NomeCompleto) o;
        return this.nomeCompleto.equals(that.nomeCompleto);
    }

    @Override
    public int hashCode() {
        return this.nomeCompleto.hashCode();
    }

    @Override
    public String toString() {
        return this.nomeCompleto;
    }

    @Override
    public int compareTo(final NomeCompleto arg0) {
        return nomeCompleto.compareTo(arg0.nomeCompleto);
    }
}
