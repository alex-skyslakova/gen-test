import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommatizingNumbersTest {

    @Test
    public void testDefaultCommatize() {
        String input = "pi=3.14159265358979323846264338327950288419716939937510582097494459231";
        String expected = "pi=3.14159265358979323846264338327950288419716939937510582097494459231";
        CommatizingNumbers.commatize(input);
        assertEquals(expected, input);
    }

    @Test
    public void testCustomCommatize() {
        String input = "The author has two Z$100000000000000 Zimbabwe notes (100 trillion).";
        String expected = "The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).";
        CommatizingNumbers.commatize(input, 0, 3, ".");
        assertEquals(expected, input);
    }

    @Test
    public void testNoNumberCommatize() {
        String input = "Ain't no numbers in this here words, nohow, no way, Jose.";
        String expected = "Ain't no numbers in this here words, nohow, no way, Jose.";
        CommatizingNumbers.commatize(input);
        assertEquals(expected, input);
    }

    @Test
    public void testLeadingZeroesCommatize() {
        String input = "James was never known as 0000000007";
        String expected = "James was never known as 0000000007";
        CommatizingNumbers.commatize(input);
        assertEquals(expected, input);
    }

    @Test
    public void testExponentCommatize() {
        String input = "123.e8000 is pretty big.";
        String expected = "123.e8000 is pretty big.";
        CommatizingNumbers.commatize(input);
        assertEquals(expected, input);
    }

    @Test
    public void testLargeNumberCommatize() {
        String input = "Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.";
        String expected = "Arthur Eddington wrote: I believe there are 157,477,241,362,750,025,776,056,539,611,815,554,680,447,179,145,271,167,093,662,314,250,761,856,310,312,96 protons in the universe.";
        CommatizingNumbers.commatize(input);
        assertEquals(expected, input);
    }

    @Test
    public void testNegativeNumberCommatize() {
        String input = "$-140000±100 millions.";
        String expected = "$-140,000±100 millions.";
        CommatizingNumbers.commatize(input);
        assertEquals(expected, input);
    }

    @Test
    public void testInvalidStartCommatize() {
        String input = "The land area of the earth is 57268900(29% of the surface) square miles.";
        String expected = "The land area of the earth is 57268900(29% of the surface) square miles.";
        CommatizingNumbers.commatize(input, -1, 3, ",");
        assertEquals(expected, input);
    }

    @Test
    public void testInvalidStepCommatize() {
        String input = "The land area of the earth is 57268900(29% of the surface) square miles.";
        String expected = "The land area of the earth is 57268900(29% of the surface) square miles.";
        CommatizingNumbers.commatize(input, 0, 0, ",");
        assertEquals(expected, input);
    }

    @Test
    public void testCustomSeparatorCommatize() {
        String input = "The land area of the earth is 57268900(29% of the surface) square miles.";
        String expected = "The land area of the earth is 57268900(29% of the surface) square miles.";
        CommatizingNumbers.commatize(input, 0, 3, " ");
        assertEquals(expected, input);
    }
}
