import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EgyptianDivisionTest {

    @Test
    void testDivide_580_by_34() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(580, 34);

        assertEquals("17, remainder 2" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testDivide_SimpleCase() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(10, 2);

        assertEquals("5, remainder 0" + System.lineSeparator(), outContent.toString());
    }


    @Test
    void testDivide_ZeroDividend() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(0, 5);

        assertEquals("0, remainder 0" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testDivide_LargeDividend() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(12345, 67);

        assertEquals("184, remainder 17" + System.lineSeparator(), outContent.toString());

    }

    @Test
    void testDivide_SmallDivisor() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(100, 2);

        assertEquals("50, remainder 0" + System.lineSeparator(), outContent.toString());
    }


    @Test
    void testDivide_DividendEqualToDivisor(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(50, 50);

        assertEquals("1, remainder 0" + System.lineSeparator(), outContent.toString());
    }

        @Test
    void testDivide_DividendLessThanDivisor(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EgyptianDivision.divide(5, 50);

        assertEquals("0, remainder 5" + System.lineSeparator(), outContent.toString());
    }


}
