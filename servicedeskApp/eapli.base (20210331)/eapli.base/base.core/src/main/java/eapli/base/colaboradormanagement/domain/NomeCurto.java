package eapli.base.colaboradormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O nome curto do colaborador.
 * <p>
 * Esta class representa o nome curto de um colaborador.
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
@Embeddable
public class NomeCurto implements ValueObject, Comparable<NomeCurto> {

    private static final long serialVersionUID = 1L;

    private String nomeCurto;

    public NomeCurto(final String nomeCurto) {
        if (StringPredicates.isNullOrEmpty(nomeCurto)) {
            throw new IllegalArgumentException(
                    "Identifier nomeCurto for Catalog should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier nomeCurto regular
        // expression
        this.nomeCurto = nomeCurto;
    }

    protected NomeCurto() {
        // for ORM
    }

    public static NomeCurto valueOf(final String nomeCurto) {
        return new NomeCurto(nomeCurto);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NomeCurto)) {
            return false;
        }

        final NomeCurto that = (NomeCurto) o;
        return this.nomeCurto.equals(that.nomeCurto);
    }

    @Override
    public int hashCode() {
        return this.nomeCurto.hashCode();
    }

    @Override
    public String toString() {
        return this.nomeCurto;
    }

    @Override
    public int compareTo(final NomeCurto arg0) {
        return nomeCurto.compareTo(arg0.nomeCurto);
    }
}
