package eapli.base.slaservicemanagement.domain;

import junit.framework.TestCase;


public class NiveisCriticidadeTest extends TestCase {


    private static final Cor COR = Cor.valueOf("AZUL");
    private static final EscalaNumerica ESCALA_NUMERICA = EscalaNumerica.valueOf("5");
    private static final Etiqueta ETIQUETA = Etiqueta.valueOf("Medio");
    private static final TempoMedioAprovacao TEMPO_MEDIO_APROVACAO = TempoMedioAprovacao.valueOf("12");
    private static final TempoMaximoAprovacao TEMPO_MAXIMO_APROVACAO = TempoMaximoAprovacao.valueOf("14");
    private static final TempoMedioResolucao TEMPO_MEDIO_RESOLUCAO = TempoMedioResolucao.valueOf("12");
    private static final TempoMaximoResolucao TEMPO_MAXIMO_RESOLUCAO = TempoMaximoResolucao.valueOf("14");


    private static final NiveisCriticidade NIVEL_CRITICIDADE_UM = new NiveisCriticidade(COR, ESCALA_NUMERICA, ETIQUETA, TEMPO_MAXIMO_APROVACAO, TEMPO_MEDIO_APROVACAO, TEMPO_MAXIMO_RESOLUCAO, TEMPO_MEDIO_RESOLUCAO);
    private static final NiveisCriticidade NIVEL_CRITICIDADE_DOIS = new NiveisCriticidade(COR, ESCALA_NUMERICA, ETIQUETA, TEMPO_MAXIMO_APROVACAO, TEMPO_MEDIO_APROVACAO, TEMPO_MAXIMO_RESOLUCAO, TEMPO_MEDIO_RESOLUCAO);


    public void testTestEquals() {
        assertEquals(NIVEL_CRITICIDADE_UM, NIVEL_CRITICIDADE_DOIS);
    }

    public void testTestHashCode() {
        assertEquals(NIVEL_CRITICIDADE_UM, NIVEL_CRITICIDADE_DOIS);
    }

    public void testSameAs() {
        final boolean expected = NIVEL_CRITICIDADE_UM.sameAs(NIVEL_CRITICIDADE_UM);
        assertTrue(expected);
    }


    public void testCor() {
        Cor cor = NIVEL_CRITICIDADE_UM.cor();

        final boolean expected = cor.equals(NIVEL_CRITICIDADE_UM.cor());

    }

    public void testEscalaNumerica() {
        EscalaNumerica escalaNumerica = NIVEL_CRITICIDADE_UM.escalaNumerica();

        final boolean expected = escalaNumerica.equals(NIVEL_CRITICIDADE_UM.escalaNumerica());
    }

    public void testEtiqueta() {

        Etiqueta etiqueta = NIVEL_CRITICIDADE_UM.etiqueta();

        final boolean expected = etiqueta.equals(NIVEL_CRITICIDADE_UM.etiqueta());

    }

    public void testTempoMaximo() {

        TempoMaximoAprovacao tempoMaximoAprovacao = NIVEL_CRITICIDADE_UM.tempoMaximoAprovacao();

        final boolean expected = tempoMaximoAprovacao.equals(NIVEL_CRITICIDADE_UM.tempoMaximoAprovacao());
    }

    public void testTempoMedio() {

        TempoMedioAprovacao tempoMedioAprovacao = NIVEL_CRITICIDADE_UM.tempoMedioAprovacao();

        final boolean expected = tempoMedioAprovacao.equals(NIVEL_CRITICIDADE_UM.tempoMedioAprovacao());
    }
}