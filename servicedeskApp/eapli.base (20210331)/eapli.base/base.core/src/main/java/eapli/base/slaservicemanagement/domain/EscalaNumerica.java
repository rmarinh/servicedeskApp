package eapli.base.slaservicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.strings.util.StringPredicates;
import org.apache.commons.lang3.math.NumberUtils;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * A escala numérica do Nivel de Criticidade.
 *
 * Esta class representa uma escala numérica de um Nivel de Criticidade.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class EscalaNumerica implements ValueObject, Comparable<EscalaNumerica>{

    private static final long serialVersionUID = 1L;

    private String escalaNumerica;

    public EscalaNumerica(final String escalaNumerica) {
        if (StringPredicates.isNullOrEmpty(escalaNumerica)) {
            throw new IllegalArgumentException(
                    "\nERRO: A escala numérica não pode ser vazia\n\n");
        }
        if(!EscalaNumericaIsValid(escalaNumerica)){
            throw new IllegalArgumentException(
                    "\nERRO: A escala numérica é representada por valores numéricos superiores a 0.\n\n");
        }this.escalaNumerica = escalaNumerica;
    }

    public static boolean EscalaNumericaIsValid(String escalaNumerica) {
        String regex = "^[1-9][0-9]*$";
        return Pattern.matches(regex, escalaNumerica);
    }


    protected EscalaNumerica() {
        // for ORM
    }

    public static EscalaNumerica valueOf(final String escalaNumerica) {
        return new EscalaNumerica(escalaNumerica);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EscalaNumerica)) {
            return false;
        }

        final EscalaNumerica that = (EscalaNumerica) o;
        return this.escalaNumerica.equals(that.escalaNumerica);
    }

    @Override
    public int hashCode() {
        return this.escalaNumerica.hashCode();
    }

    @Override
    public String toString() {
        return this.escalaNumerica;
    }

    @Override
    public int compareTo(final EscalaNumerica arg0) {
        return escalaNumerica.compareTo(arg0.escalaNumerica);
    }
}
