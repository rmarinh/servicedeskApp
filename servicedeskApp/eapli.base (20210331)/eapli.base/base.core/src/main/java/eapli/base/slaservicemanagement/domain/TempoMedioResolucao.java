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
public class TempoMedioResolucao implements ValueObject, Comparable<TempoMedioResolucao>{

    private static final long serialVersionUID = 1L;

    private String tempoMedioResolucao;

    public TempoMedioResolucao(final String tempoMedioResolucao) {
        if (StringPredicates.isNullOrEmpty(tempoMedioResolucao)) {
            throw new IllegalArgumentException(
                    "\nERRO: Deve assinalar um tempo maximo.\n\n");
        }
            if (!TempoMedioIsValid(tempoMedioResolucao)) {
                throw new IllegalArgumentException(
                        "\nERRO: Deve assinalar um tempo maximo válido (valores inteiros superiores a 0\n\n");
            }
            this.tempoMedioResolucao = tempoMedioResolucao;
        }

        public static boolean TempoMedioIsValid(String tempoMaximo) {
            String regex = "^[1-9][0-9]*$";
            return Pattern.matches(regex, tempoMaximo);
        }



    protected TempoMedioResolucao() {
        // for ORM
    }

    public static TempoMedioResolucao valueOf(final String tempoMedio) {
        return new TempoMedioResolucao(tempoMedio);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TempoMedioResolucao)) {
            return false;
        }

        final TempoMedioResolucao that = (TempoMedioResolucao) o;
        return this.tempoMedioResolucao.equals(that.tempoMedioResolucao);
    }

    @Override
    public int hashCode() {
        return this.tempoMedioResolucao.hashCode();
    }

    @Override
    public String toString() {
        return this.tempoMedioResolucao;
    }

    @Override
    public int compareTo(final TempoMedioResolucao arg0) {
        return tempoMedioResolucao.compareTo(arg0.tempoMedioResolucao);
    }
}
