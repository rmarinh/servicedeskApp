package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * O codigo unico de um Servico.
 * <p>
 * Esta class representa um valor unico e numerico que identifica um Servico.
 *
 * @author Rui Marinho 1171602@isep.ipp.pt
 */
@Embeddable
public class CodigoUnico implements ValueObject, Comparable<CodigoUnico> {

    private static final long serialVersionUID = 1L;

    private String codigoUnico;

    public CodigoUnico(final String codigoUnico) {
        if (StringPredicates.isNullOrEmpty(codigoUnico)) {
            throw new IllegalArgumentException(
                    "Identifier unique code for Service should neither be null nor empty");
        }
        //TOdo RM : check codigo unico already exists in BD

        this.codigoUnico = codigoUnico;
    }

    protected CodigoUnico() {
        // for ORM
    }

    public static CodigoUnico valueOf(final String codigoUnico) {
        return new CodigoUnico(codigoUnico);
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
        return this.codigoUnico.equals(that.codigoUnico);
    }

    @Override
    public int hashCode() {
        return this.codigoUnico.hashCode();
    }

    @Override
    public String toString() {
        return this.codigoUnico;
    }

    @Override
    public int compareTo(final CodigoUnico arg0) {
        return codigoUnico.compareTo(arg0.codigoUnico);
    }
}