package eapli.base.colaboradormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O numero de contacto do colaborador.
 * <p>
 * Esta class representa o numero de contacto de um colaborador.
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
@Embeddable
public class NumeroContacto implements ValueObject, Comparable<NumeroContacto> {

    private static final long serialVersionUID = 1L;

    private String numeroContacto;

    public NumeroContacto(final String numeroContacto) {
        if (StringPredicates.isNullOrEmpty(numeroContacto)) {
            throw new IllegalArgumentException(
                    "Identifier numeroContacto for Catalog should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier numeroContacto regular
        // expression
        this.numeroContacto = numeroContacto;
    }

    protected NumeroContacto() {
        // for ORM
    }

    public static NumeroContacto valueOf(final String numeroContacto) {
        return new NumeroContacto(numeroContacto);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumeroContacto)) {
            return false;
        }

        final NumeroContacto that = (NumeroContacto) o;
        return this.numeroContacto.equals(that.numeroContacto);
    }

    @Override
    public int hashCode() {
        return this.numeroContacto.hashCode();
    }

    @Override
    public String toString() {
        return this.numeroContacto;
    }

    @Override
    public int compareTo(final NumeroContacto arg0) {
        return numeroContacto.compareTo(arg0.numeroContacto);
    }
}
