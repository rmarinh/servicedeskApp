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
public class TempoMaximoResolucao implements ValueObject, Comparable<TempoMaximoResolucao>{

    private static final long serialVersionUID = 1L;

    private String tempoMaximoResolucao;

    public TempoMaximoResolucao(final String tempoMaximoResolucao) {
        if (StringPredicates.isNullOrEmpty(tempoMaximoResolucao)) {
            throw new IllegalArgumentException(
                    "\nERRO: Deve assinalar um tempo maximo.\n\n");
        }
        if (!TempoMaximoIsValid(tempoMaximoResolucao)) {
            throw new IllegalArgumentException(
                    "\nERRO: Deve assinalar um tempo maximo válido (valores inteiros superiores a 0\n\n");
        }
            this.tempoMaximoResolucao = tempoMaximoResolucao;
        }


    public static boolean TempoMaximoIsValid(String tempoMaximo) {
        String regex = "^[1-9][0-9]*$";
        return Pattern.matches(regex, tempoMaximo);
    }



    protected TempoMaximoResolucao() {
        // for ORM
    }

    public static TempoMaximoResolucao valueOf(final String tempoMaximo) {
        return new TempoMaximoResolucao(tempoMaximo);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TempoMaximoResolucao)) {
            return false;
        }

        final TempoMaximoResolucao that = (TempoMaximoResolucao) o;
        return this.tempoMaximoResolucao.equals(that.tempoMaximoResolucao);
    }

    @Override
    public int hashCode() {
        return this.tempoMaximoResolucao.hashCode();
    }

    @Override
    public String toString() {
        return this.tempoMaximoResolucao;
    }

    @Override
    public int compareTo(final TempoMaximoResolucao arg0) {
        return tempoMaximoResolucao.compareTo(arg0.tempoMaximoResolucao);
    }
}
