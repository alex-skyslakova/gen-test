import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EgyptianDivisionTest {

    @Test
    void testDivideExactDivision() {
        // Test case where dividend is exactly divisible by divisor
        int dividend = 68;
        int divisor = 34;
        String expectedOutput = "2, remainder 0";
        assertEquals(expectedOutput, getDivideOutput(dividend, divisor));
    }

    @Test
    void testDivideWithRemainder() {
        // Test case where dividend is not exactly divisible by divisor
        int dividend = 580;
        int divisor = 34;
        String expectedOutput = "17, remainder 2";
        assertEquals(expectedOutput, getDivideOutput(dividend, divisor));
    }

    @Test
    void testDivideByOne() {
        // Test case where divisor is 1
        int dividend = 123;
        int divisor = 1;
        String expectedOutput = "123, remainder 0";
        assertEquals(expectedOutput, getDivideOutput(dividend, divisor));
    }

    @Test
    void testDivideByItself() {
        // Test case where dividend is equal to divisor
        int dividend = 34;
        int divisor = 34;
        String expectedOutput = "1, remainder 0";
        assertEquals(expectedOutput, getDivideOutput(dividend, divisor));
    }

    @Test
    void testDivideZeroDividend() {
        // Test case where dividend is 0
        int dividend = 0;
        int divisor = 34;
        String expectedOutput = "0, remainder 0";
        assertEquals(expectedOutput, getDivideOutput(dividend, divisor));
    }

    @Test
    void testDivideLargerDivisor() {
        // Test case where divisor is larger than dividend
        int dividend = 34;
        int divisor = 68;
        String expectedOutput = "0, remainder 34";
        assertEquals(expectedOutput, getDivideOutput(dividend, divisor));
    }

    private String getDivideOutput(int dividend, int divisor) {
        // Capture the output of the divide method
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        EgyptianDivision.divide(dividend, divisor);
        return outContent.toString().trim();
    }
}
