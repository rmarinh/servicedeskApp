package eapli.base.slaservicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * A etiqueta do Nivel de Criticidade.
 *
 * Esta class representa um etiqueta que identifica um Nivel de Criticidade.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class Etiqueta implements ValueObject, Comparable<Etiqueta>{

    private static final long serialVersionUID = 1L;

    private String etiqueta;

    public Etiqueta(final String etiqueta) {
        if (StringPredicates.isNullOrEmpty(etiqueta)) {
            throw new IllegalArgumentException(
                    "\nERRO: A etiqueta nao pode ser vazia\n\n");
        }
        if (!EtiquetaIsValid(etiqueta)) {
            throw new IllegalArgumentException(
                    "\nERRO: A etiqueta deve possuir até 25 carateres\n\n");
        }
        this.etiqueta = etiqueta;
    }

    public static boolean EtiquetaIsValid(String etiqueta) {
        String regex = "^[a-zA-Z ]{1,25}$";
        return Pattern.matches(regex, etiqueta);
    }

    protected Etiqueta() {
        // for ORM
    }

    public static Etiqueta valueOf(final String etiqueta) {
        return new Etiqueta(etiqueta);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Etiqueta)) {
            return false;
        }

        final Etiqueta that = (Etiqueta) o;
        return this.etiqueta.equals(that.etiqueta);
    }

    @Override
    public int hashCode() {
        return this.etiqueta.hashCode();
    }

    @Override
    public String toString() {
        return this.etiqueta;
    }

    @Override
    public int compareTo(final Etiqueta arg0) {
        return etiqueta.compareTo(arg0.etiqueta);
    }
}
