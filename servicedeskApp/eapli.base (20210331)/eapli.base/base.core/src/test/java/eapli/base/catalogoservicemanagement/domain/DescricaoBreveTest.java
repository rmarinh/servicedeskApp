package eapli.base.catalogoservicemanagement.domain;

import junit.framework.TestCase;
import org.junit.Test;

public class DescricaoBreveTest extends TestCase {

    private final String descricaoBreve = "descricaoBreve";

    public static DescricaoBreve dummyDescricaoBreve(final String descricaoBreve) {
        return new DescricaoBreve(descricaoBreve);
    }

    private DescricaoBreve getNewdummyDescricaoBreve() {
        return dummyDescricaoBreve("DescricaoBreve");
    }

    private DescricaoBreve getNewdummyDescricaoBreveTwo() {
        return dummyDescricaoBreve("DescricaoBreve");
    }

    private DescricaoBreve getNewdummyDescricaoBreveThree() {
        return dummyDescricaoBreve("DescricaoMesmoBreve");
    }

    @Test
    public void testValueOf() {
        final DescricaoBreve aDescricaoBreve = getNewdummyDescricaoBreve();

        final boolean expected = DescricaoBreve.valueOf("DescricaoBreve").equals(aDescricaoBreve);
        assertTrue(expected);
    }

    @Test
    public void testTestEquals() {
        final DescricaoBreve aDescricaoBreve = getNewdummyDescricaoBreve();
        final DescricaoBreve anotherDescricaoBreve = getNewdummyDescricaoBreveTwo();
        final DescricaoBreve anotherAnotherDescricaoBreve = getNewdummyDescricaoBreveThree();

        final boolean expectedOne = aDescricaoBreve.equals(anotherDescricaoBreve);
        final boolean expectedTwo = aDescricaoBreve.equals(anotherAnotherDescricaoBreve);
        assertTrue(expectedOne);
        assertFalse(expectedTwo);
    }

    @Test
    public void testTestHashCode() {
        final DescricaoBreve aDescricaoBreve = getNewdummyDescricaoBreve();
        final DescricaoBreve anotherDescricaoBreve = getNewdummyDescricaoBreveTwo();

        int aCatalogHash = aDescricaoBreve.hashCode();
        int anotherCatalogoHash = anotherDescricaoBreve.hashCode();

        final boolean expected = aDescricaoBreve.equals(anotherDescricaoBreve);
        assertTrue(expected);
    }

    @Test
    public void testTestToString() {
        final DescricaoBreve aDescricaoBreve = getNewdummyDescricaoBreve();
        final String expectedResult = "DescricaoBreve";
        final boolean expected = aDescricaoBreve.toString().equals(expectedResult);
        assertTrue(expected);
    }

    @Test
    public void testCompareTo() {
        final DescricaoBreve aDescricaoBreve = getNewdummyDescricaoBreve();
        final DescricaoBreve anotherDescricaoBreve = getNewdummyDescricaoBreveTwo();

        int aCatalogHash = aDescricaoBreve.hashCode();
        int anotherCatalogoHash = anotherDescricaoBreve.hashCode();

        final int expected = aDescricaoBreve.compareTo(anotherDescricaoBreve);
        assertTrue(expected == 0);
    }
}