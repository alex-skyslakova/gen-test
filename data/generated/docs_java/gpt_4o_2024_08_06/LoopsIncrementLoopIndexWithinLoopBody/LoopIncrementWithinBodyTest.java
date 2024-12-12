import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoopIncrementWithinBodyTest {

    @Test
    public void testIsPrime() {
        assertTrue(LoopIncrementWithinBody.isPrime(2), "2 should be prime");
        assertTrue(LoopIncrementWithinBody.isPrime(3), "3 should be prime");
        assertTrue(LoopIncrementWithinBody.isPrime(5), "5 should be prime");
        assertTrue(LoopIncrementWithinBody.isPrime(7), "7 should be prime");
        assertTrue(LoopIncrementWithinBody.isPrime(11), "11 should be prime");
        assertFalse(LoopIncrementWithinBody.isPrime(4), "4 should not be prime");
        assertFalse(LoopIncrementWithinBody.isPrime(6), "6 should not be prime");
        assertFalse(LoopIncrementWithinBody.isPrime(9), "9 should not be prime");
        assertFalse(LoopIncrementWithinBody.isPrime(15), "15 should not be prime");
    }

    @Test
    public void testLoopOutput() {
        // Capture the output of the main method
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        LoopIncrementWithinBody.main(new String[]{});

        String[] outputLines = outContent.toString().split("\n");
        assertEquals(42, outputLines.length, "There should be 42 lines of output");

        int expectedPrimeCount = 0;
        long lastPrime = 0;
        for (String line : outputLines) {
            expectedPrimeCount++;
            assertTrue(line.startsWith("n = " + expectedPrimeCount), "Line should start with correct prime count");
            String[] parts = line.split("\\s+");
            long prime = Long.parseLong(parts[2].replace(",", ""));
            assertTrue(LoopIncrementWithinBody.isPrime(prime), prime + " should be prime");
            assertTrue(prime > lastPrime, "Primes should be in increasing order");
            lastPrime = prime;
        }
    }
}
