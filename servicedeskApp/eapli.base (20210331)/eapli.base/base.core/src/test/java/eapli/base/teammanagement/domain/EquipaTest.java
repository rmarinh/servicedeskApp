package eapli.base.teammanagement.domain;

import eapli.base.colaboradormanagement.domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquipaTest{

    private static final Acronimo ACRONIMO_EQUIPA = Acronimo.valueOf("Equipa 1");
    private static final Designacao DESIGNACAO_EQUIPA = Designacao.valueOf("Equipa Teste 1");
    private static final Designacao DESIGNACAO_TIPO_EQUIPA = Designacao.valueOf("Tipo de Equipa 1");

    private static final Cor AZUL = Cor.valueOf("Azul");

    private static final EquipaType TIPO_EQUIPA_UM = new EquipaType(DESIGNACAO_TIPO_EQUIPA, AZUL);

    private static final Equipa EQUIPA_UM = new Equipa(ACRONIMO_EQUIPA, DESIGNACAO_EQUIPA, AZUL, TIPO_EQUIPA_UM);
    private static final Equipa EQUIPA_DOIS = new Equipa(ACRONIMO_EQUIPA, DESIGNACAO_EQUIPA, AZUL, TIPO_EQUIPA_UM);

    /**
     * Test of Equals, of class Equipa.
     */
    @Test
    public void testEquals() {
        assertEquals(EQUIPA_UM, EQUIPA_DOIS);
    }

    /**
     * Test of Designacao, of class Equipa.
     */
    @Test
    public void ensureEquipaHasDesignacao() {
        assertTrue(EQUIPA_UM.designacao()!= null);
    }

    /**
     * Test of Acronimo, of class Equipa.
     */
    @Test
    public void ensureEquipaHasAcronimo() {
        assertTrue(EQUIPA_UM.acronimo()!= null);
    }

    /**
     * Test of Cor, of class Equipa.
     */
    @Test
    public void ensureEquipaHasCor() {
        assertTrue(EQUIPA_UM.cor()!= null);
    }

    /**
     * Test of Identity, of class Equipa.
     */
    @Test
    public void ensureEquipaHasIdentity() {
        assert(EQUIPA_UM.identity() == null);
    }

    /**
     * Test of Tipo Equipa, of class Equipa.
     */
    @Test
    public void ensureEquipaHasTipoEquipa() {
        assertEquals(EQUIPA_UM.tipoEquipa(), TIPO_EQUIPA_UM);
    }
}