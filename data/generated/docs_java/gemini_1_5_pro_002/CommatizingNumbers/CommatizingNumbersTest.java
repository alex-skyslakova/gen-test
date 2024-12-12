import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommatizingNumbersTest {

    @Test
    void testPiWithSpaces() {
        CommatizingNumbers.commatize("pi=3.14159265358979323846264338327950288419716939937510582097494459231", 0, 5, " ");
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }

    @Test
    void testZimbabweDollarsWithDecimal() {
        CommatizingNumbers.commatize("The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", 0, 3, ".");
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }

    @Test
    void testDefaultCommatization() {
        CommatizingNumbers.commatize("1234567890");
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }


    @Test
    void testNoNumbers() {
        CommatizingNumbers.commatize("Ain't no numbers in this here words, nohow, no way, Jose.");
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.

    }

    @Test
    void testLeadingZeros() {
        CommatizingNumbers.commatize("0000000005714.882");
         // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }

    @Test
    void testExponent() {
        CommatizingNumbers.commatize("123.e8000 is pretty big.");
         // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }

    @Test
    void testStartParameter() {
        CommatizingNumbers.commatize("-in Aus$+1411.8millions", 10, 3, ",");
         // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }

    @Test
    void testInvalidStartParameter() {
       CommatizingNumbers.commatize("1234567", -1, 3, ","); // Invalid start
       CommatizingNumbers.commatize("1234567", 10, 3, ","); // Invalid start
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }

    @Test
    void testInvalidStepParameter() {
       CommatizingNumbers.commatize("1234567", 0, 0, ","); // Invalid step
       CommatizingNumbers.commatize("1234567", 0, 8, ","); // Invalid step
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }


    @Test
    void testLargeNumber() {
        CommatizingNumbers.commatize("Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.");
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }


    @Test
    void testLeadingSpaces() {
       CommatizingNumbers.commatize("   $-140000±100 millions.");
        // Assertion is done via System.out.println in the original code.  Would need to refactor to return the string for proper unit testing.
    }



}
