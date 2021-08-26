package eapli.base.colaboradormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O local de residencia do Colaborador.
 * <p>
 * Esta class representa o local de residencia do Colaborador.
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
@Embeddable
public class LocalResidencia implements ValueObject, Comparable<LocalResidencia> {

    private static final long serialVersionUID = 1L;

    private String localResidencia;

    public LocalResidencia(final String localResidencia) {
        if (StringPredicates.isNullOrEmpty(localResidencia)) {
            throw new IllegalArgumentException(
                    "Identifier localResidencia for Catalog should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier localResidencia regular
        // expression
        this.localResidencia = localResidencia;
    }

    protected LocalResidencia() {
        // for ORM
    }

    public static LocalResidencia valueOf(final String localResidencia) {
        return new LocalResidencia(localResidencia);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalResidencia)) {
            return false;
        }

        final LocalResidencia that = (LocalResidencia) o;
        return this.localResidencia.equals(that.localResidencia);
    }

    @Override
    public int hashCode() {
        return this.localResidencia.hashCode();
    }

    @Override
    public String toString() {
        return this.localResidencia;
    }

    @Override
    public int compareTo(final LocalResidencia arg0) {
        return localResidencia.compareTo(arg0.localResidencia);
    }
}
