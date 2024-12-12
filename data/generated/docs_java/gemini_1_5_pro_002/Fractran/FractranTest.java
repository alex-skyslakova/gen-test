import org.junit.jupiter.api.Test;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

public class FractranTest {

    @Test
    void testCompile_validInput() {
        Fractran fractran = new Fractran("", 2);  // Empty program for initialization
        fractran.compile("17/91 78/85 19/51");

        assertEquals(3, fractran.num.size());
        assertEquals(3, fractran.den.size());
        assertEquals(17, fractran.num.get(0));
        assertEquals(91, fractran.den.get(0));
        assertEquals(78, fractran.num.get(1));
        assertEquals(85, fractran.den.get(1));
        assertEquals(19, fractran.num.get(2));
        assertEquals(51, fractran.den.get(2));
    }

    @Test
    void testCompile_invalidInput() {
        Fractran fractran = new Fractran("", 2);
        fractran.compile("17/91 abc 19/51"); // Invalid fraction "abc"

        assertEquals(2, fractran.num.size()); // Should only parse valid fractions
        assertEquals(2, fractran.den.size());
        assertEquals(17, fractran.num.get(0));
        assertEquals(91, fractran.den.get(0));
        assertEquals(19, fractran.num.get(1));
        assertEquals(51, fractran.den.get(1));

    }


    @Test
    void testStep_fractionApplies() {
        Fractran fractran = new Fractran("", 2);
        fractran.num.add(3);
        fractran.den.add(2);
        Integer result = fractran.step(2);
        assertEquals(3, result);
    }

    @Test
    void testStep_noFractionApplies() {
        Fractran fractran = new Fractran("", 2);
        fractran.num.add(3);
        fractran.den.add(5); // Won't divide 2
        Integer result = fractran.step(2);
        assertNull(result);
    }

    @Test
    void testExec_conwayPrimeProgram() {
        Fractran fractran = new Fractran("17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1", 2);
        fractran.limit = 2; // Limit steps for testing
        fractran.exec(2);
    }



    @Test
    void testExec_limitedSteps(){
        Fractran fractran = new Fractran("15/2 55/1", 2);
        fractran.limit = 2;
        fractran.exec(2);

    }

    @Test
    void testDump(){
        Fractran fractran = new Fractran("15/2 55/1", 2);
        fractran.dump();
    }

}
