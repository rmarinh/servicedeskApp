package eapli.base.teammanagement.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class EquipaTypeTest {

    private static final Designacao DESIGNACAO_TIPO_EQUIPA = Designacao.valueOf("Tipo de Equipa 1");
    private static final Cor AZUL = Cor.valueOf("Azul");

    private static final EquipaType TIPO_EQUIPA_UM = new EquipaType(DESIGNACAO_TIPO_EQUIPA, AZUL);
    private static final EquipaType TIPO_EQUIPA_DOIS = new EquipaType(DESIGNACAO_TIPO_EQUIPA, AZUL);

    /**
     * Test of Equals, of class EquipaType.
     */
    @Test
    public void testEquals() {
        assertEquals(TIPO_EQUIPA_UM, TIPO_EQUIPA_DOIS);
    }

    /**
     * Test of Designacao, of class EquipaType.
     */
    @Test
    public void ensureEquipaHasDesignacao() {
        assertTrue(TIPO_EQUIPA_UM.descricao()!= null);
    }

    /**
     * Test of Cor, of class EquipaType.
     */
    @Test
    public void ensureEquipaHasCor() {
        assertTrue(TIPO_EQUIPA_UM.cor()!= null);
    }

    /**
     * Test of Identity, of class EquipaType.
     */
    @Test
    public void ensureEquipaHasIdentity() {
        assert(TIPO_EQUIPA_UM.identity() == null);
    }

}