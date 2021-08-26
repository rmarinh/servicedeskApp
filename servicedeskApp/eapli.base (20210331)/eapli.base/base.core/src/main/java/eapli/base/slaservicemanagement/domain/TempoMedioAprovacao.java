package eapli.base.slaservicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * O Tempo médio a ser satifeito para o Nivel de Criticidade.
 *
 * Esta class representa um dos objetivos do nivel de criticidade - tempo médio.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class TempoMedioAprovacao implements ValueObject, Comparable<TempoMedioAprovacao>{

    private static final long serialVersionUID = 1L;

    private String tempoMedioAprovacao;

    public TempoMedioAprovacao(final String tempoMedioAprovacao) {
        if (StringPredicates.isNullOrEmpty(tempoMedioAprovacao)) {
            throw new IllegalArgumentException(
                    "\nERRO: Deve assinalar um tempo maximo.\n\n");
        }
            if (!TempoMedioIsValid(tempoMedioAprovacao)) {
                throw new IllegalArgumentException(
                        "\nERRO: Deve assinalar um tempo maximo válido (valores inteiros superiores a 0\n\n");
            }
            this.tempoMedioAprovacao = tempoMedioAprovacao;
        }

        public static boolean TempoMedioIsValid(String tempoMaximo) {
            String regex = "^[1-9][0-9]*$";
            return Pattern.matches(regex, tempoMaximo);
        }



    protected TempoMedioAprovacao() {
        // for ORM
    }

    public static TempoMedioAprovacao valueOf(final String tempoMedio) {
        return new TempoMedioAprovacao(tempoMedio);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TempoMedioAprovacao)) {
            return false;
        }

        final TempoMedioAprovacao that = (TempoMedioAprovacao) o;
        return this.tempoMedioAprovacao.equals(that.tempoMedioAprovacao);
    }

    @Override
    public int hashCode() {
        return this.tempoMedioAprovacao.hashCode();
    }

    @Override
    public String toString() {
        return this.tempoMedioAprovacao;
    }

    @Override
    public int compareTo(final TempoMedioAprovacao arg0) {
        return tempoMedioAprovacao.compareTo(arg0.tempoMedioAprovacao);
    }
}
