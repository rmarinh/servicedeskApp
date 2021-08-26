package eapli.base.slaservicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * O Tempo máximo a ser satifeito para o Nivel de Criticidade.
 *
 * Esta class representa um dos objetivos do nivel de criticidade - tempo máximo.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class TempoMaximoAprovacao implements ValueObject, Comparable<TempoMaximoAprovacao>{

    private static final long serialVersionUID = 1L;

    private String tempoMaximoAprovacao;

    public TempoMaximoAprovacao(final String tempoMaximoAprovacao) {
        if (StringPredicates.isNullOrEmpty(tempoMaximoAprovacao)) {
            throw new IllegalArgumentException(
                    "\nERRO: Deve assinalar um tempo maximo.\n\n");
        }
        if (!TempoMaximoIsValid(tempoMaximoAprovacao)) {
            throw new IllegalArgumentException(
                    "\nERRO: Deve assinalar um tempo maximo válido (valores inteiros superiores a 0\n\n");
        }
            this.tempoMaximoAprovacao = tempoMaximoAprovacao;
        }


    public static boolean TempoMaximoIsValid(String tempoMaximo) {
        String regex = "^[1-9][0-9]*$";
        return Pattern.matches(regex, tempoMaximo);
    }



    protected TempoMaximoAprovacao() {
        // for ORM
    }

    public static TempoMaximoAprovacao valueOf(final String tempoMaximo) {
        return new TempoMaximoAprovacao(tempoMaximo);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TempoMaximoAprovacao)) {
            return false;
        }

        final TempoMaximoAprovacao that = (TempoMaximoAprovacao) o;
        return this.tempoMaximoAprovacao.equals(that.tempoMaximoAprovacao);
    }

    @Override
    public int hashCode() {
        return this.tempoMaximoAprovacao.hashCode();
    }

    @Override
    public String toString() {
        return this.tempoMaximoAprovacao;
    }

    @Override
    public int compareTo(final TempoMaximoAprovacao arg0) {
        return tempoMaximoAprovacao.compareTo(arg0.tempoMaximoAprovacao);
    }
}
