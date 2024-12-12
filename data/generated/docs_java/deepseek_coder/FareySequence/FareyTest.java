import org.junit.jupiter.api.Test;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;

public class FareyTest {

    @Test
    public void testGenFareyOrder1() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(1);
        assertEquals(2, farey.size());
        assertTrue(farey.contains(new Farey.Frac(0, 1)));
        assertTrue(farey.contains(new Farey.Frac(1, 1)));
    }

    @Test
    public void testGenFareyOrder2() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(2);
        assertEquals(3, farey.size());
        assertTrue(farey.contains(new Farey.Frac(0, 1)));
        assertTrue(farey.contains(new Farey.Frac(1, 2)));
        assertTrue(farey.contains(new Farey.Frac(1, 1)));
    }

    @Test
    public void testGenFareyOrder3() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(3);
        assertEquals(5, farey.size());
        assertTrue(farey.contains(new Farey.Frac(0, 1)));
        assertTrue(farey.contains(new Farey.Frac(1, 3)));
        assertTrue(farey.contains(new Farey.Frac(1, 2)));
        assertTrue(farey.contains(new Farey.Frac(2, 3)));
        assertTrue(farey.contains(new Farey.Frac(1, 1)));
    }

    @Test
    public void testGenFareyOrder4() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(4);
        assertEquals(7, farey.size());
        assertTrue(farey.contains(new Farey.Frac(0, 1)));
        assertTrue(farey.contains(new Farey.Frac(1, 4)));
        assertTrue(farey.contains(new Farey.Frac(1, 3)));
        assertTrue(farey.contains(new Farey.Frac(1, 2)));
        assertTrue(farey.contains(new Farey.Frac(2, 3)));
        assertTrue(farey.contains(new Farey.Frac(3, 4)));
        assertTrue(farey.contains(new Farey.Frac(1, 1)));
    }

    @Test
    public void testGenFareyOrder5() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(5);
        assertEquals(11, farey.size());
        assertTrue(farey.contains(new Farey.Frac(0, 1)));
        assertTrue(farey.contains(new Farey.Frac(1, 5)));
        assertTrue(farey.contains(new Farey.Frac(1, 4)));
        assertTrue(farey.contains(new Farey.Frac(1, 3)));
        assertTrue(farey.contains(new Farey.Frac(2, 5)));
        assertTrue(farey.contains(new Farey.Frac(1, 2)));
        assertTrue(farey.contains(new Farey.Frac(3, 5)));
        assertTrue(farey.contains(new Farey.Frac(2, 3)));
        assertTrue(farey.contains(new Farey.Frac(3, 4)));
        assertTrue(farey.contains(new Farey.Frac(4, 5)));
        assertTrue(farey.contains(new Farey.Frac(1, 1)));
    }

    @Test
    public void testGenFareyOrder100() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(100);
        assertEquals(3045, farey.size());
    }

    @Test
    public void testGenFareyOrder200() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(200);
        assertEquals(12233, farey.size());
    }

    @Test
    public void testGenFareyOrder300() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(300);
        assertEquals(27399, farey.size());
    }

    @Test
    public void testGenFareyOrder400() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(400);
        assertEquals(48679, farey.size());
    }

    @Test
    public void testGenFareyOrder500() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(500);
        assertEquals(76117, farey.size());
    }

    @Test
    public void testGenFareyOrder600() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(600);
        assertEquals(109501, farey.size());
    }

    @Test
    public void testGenFareyOrder700() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(700);
        assertEquals(148877, farey.size());
    }

    @Test
    public void testGenFareyOrder800() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(800);
        assertEquals(194305, farey.size());
    }

    @Test
    public void testGenFareyOrder900() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(900);
        assertEquals(245757, farey.size());
    }

    @Test
    public void testGenFareyOrder1000() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(1000);
        assertEquals(304193, farey.size());
    }
}
