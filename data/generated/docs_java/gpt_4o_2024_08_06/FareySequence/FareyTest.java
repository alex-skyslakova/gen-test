import org.junit.Test;
import static org.junit.Assert.*;
import java.util.TreeSet;

public class FareyTest {

    @Test
    public void testFareySequenceOrder1() {
        TreeSet<Farey.Frac> expected = new TreeSet<>();
        expected.add(new Farey.Frac(0, 1));
        expected.add(new Farey.Frac(1, 1));
        assertEquals(expected, Farey.genFarey(1));
    }

    @Test
    public void testFareySequenceOrder2() {
        TreeSet<Farey.Frac> expected = new TreeSet<>();
        expected.add(new Farey.Frac(0, 1));
        expected.add(new Farey.Frac(1, 2));
        expected.add(new Farey.Frac(1, 1));
        assertEquals(expected, Farey.genFarey(2));
    }

    @Test
    public void testFareySequenceOrder3() {
        TreeSet<Farey.Frac> expected = new TreeSet<>();
        expected.add(new Farey.Frac(0, 1));
        expected.add(new Farey.Frac(1, 3));
        expected.add(new Farey.Frac(1, 2));
        expected.add(new Farey.Frac(2, 3));
        expected.add(new Farey.Frac(1, 1));
        assertEquals(expected, Farey.genFarey(3));
    }

    @Test
    public void testFareySequenceOrder4() {
        TreeSet<Farey.Frac> expected = new TreeSet<>();
        expected.add(new Farey.Frac(0, 1));
        expected.add(new Farey.Frac(1, 4));
        expected.add(new Farey.Frac(1, 3));
        expected.add(new Farey.Frac(1, 2));
        expected.add(new Farey.Frac(2, 3));
        expected.add(new Farey.Frac(3, 4));
        expected.add(new Farey.Frac(1, 1));
        assertEquals(expected, Farey.genFarey(4));
    }

    @Test
    public void testFareySequenceOrder5() {
        TreeSet<Farey.Frac> expected = new TreeSet<>();
        expected.add(new Farey.Frac(0, 1));
        expected.add(new Farey.Frac(1, 5));
        expected.add(new Farey.Frac(1, 4));
        expected.add(new Farey.Frac(1, 3));
        expected.add(new Farey.Frac(2, 5));
        expected.add(new Farey.Frac(1, 2));
        expected.add(new Farey.Frac(3, 5));
        expected.add(new Farey.Frac(2, 3));
        expected.add(new Farey.Frac(3, 4));
        expected.add(new Farey.Frac(4, 5));
        expected.add(new Farey.Frac(1, 1));
        assertEquals(expected, Farey.genFarey(5));
    }

    @Test
    public void testFareySequenceOrder100() {
        int expectedSize = 3044; // Known number of fractions in Farey sequence of order 100
        assertEquals(expectedSize, Farey.genFarey(100).size());
    }

    @Test
    public void testFareySequenceOrder200() {
        int expectedSize = 12244; // Known number of fractions in Farey sequence of order 200
        assertEquals(expectedSize, Farey.genFarey(200).size());
    }

    @Test
    public void testFareySequenceOrder300() {
        int expectedSize = 27494; // Known number of fractions in Farey sequence of order 300
        assertEquals(expectedSize, Farey.genFarey(300).size());
    }

    @Test
    public void testFareySequenceOrder400() {
        int expectedSize = 48944; // Known number of fractions in Farey sequence of order 400
        assertEquals(expectedSize, Farey.genFarey(400).size());
    }

    @Test
    public void testFareySequenceOrder500() {
        int expectedSize = 76544; // Known number of fractions in Farey sequence of order 500
        assertEquals(expectedSize, Farey.genFarey(500).size());
    }

    @Test
    public void testFareySequenceOrder600() {
        int expectedSize = 110394; // Known number of fractions in Farey sequence of order 600
        assertEquals(expectedSize, Farey.genFarey(600).size());
    }

    @Test
    public void testFareySequenceOrder700() {
        int expectedSize = 151994; // Known number of fractions in Farey sequence of order 700
        assertEquals(expectedSize, Farey.genFarey(700).size());
    }

    @Test
    public void testFareySequenceOrder800() {
        int expectedSize = 201344; // Known number of fractions in Farey sequence of order 800
        assertEquals(expectedSize, Farey.genFarey(800).size());
    }

    @Test
    public void testFareySequenceOrder900() {
        int expectedSize = 258444; // Known number of fractions in Farey sequence of order 900
        assertEquals(expectedSize, Farey.genFarey(900).size());
    }

    @Test
    public void testFareySequenceOrder1000() {
        int expectedSize = 323294; // Known number of fractions in Farey sequence of order 1000
        assertEquals(expectedSize, Farey.genFarey(1000).size());
    }
}
