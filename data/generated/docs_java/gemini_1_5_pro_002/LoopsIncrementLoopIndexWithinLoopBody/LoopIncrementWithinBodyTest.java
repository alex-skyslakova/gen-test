import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopIncrementWithinBodyTest {

    @Test
    public void testMain() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        LoopIncrementWithinBody.main(new String[]{});

        NumberFormat nf = new DecimalFormat("#,###");
        StringBuilder expectedOutput = new StringBuilder();
        long i;
        int n;
        for (i = 42, n = 0; n < 42; i++) {
            if (LoopIncrementWithinBody.isPrime(i)) {
                n++;
                expectedOutput.append(String.format("n = %-2d  %,19d\n", n, i));
                i += i - 1;
            }
        }


        assertEquals(expectedOutput.toString(), outContent.toString());

        System.setOut(System.out); // Restore original System.out
    }


    @Test
    public void testIsPrime() {
        assertEquals(true, LoopIncrementWithinBody.isPrime(2));
        assertEquals(true, LoopIncrementWithinBody.isPrime(3));
        assertEquals(true, LoopIncrementWithinBody.isPrime(5));
        assertEquals(true, LoopIncrementWithinBody.isPrime(7));
        assertEquals(true, LoopIncrementWithinBody.isPrime(11));
        assertEquals(true, LoopIncrementWithinBody.isPrime(13));
        assertEquals(false, LoopIncrementWithinBody.isPrime(4));
        assertEquals(false, LoopIncrementWithinBody.isPrime(6));
        assertEquals(false, LoopIncrementWithinBody.isPrime(8));
        assertEquals(false, LoopIncrementWithinBody.isPrime(9));
        assertEquals(false, LoopIncrementWithinBody.isPrime(10));
        assertEquals(false, LoopIncrementWithinBody.isPrime(12));
        assertEquals(false, LoopIncrementWithinBody.isPrime(1));
        assertEquals(false, LoopIncrementWithinBody.isPrime(0));
        assertEquals(false, LoopIncrementWithinBody.isPrime(-1));
        assertEquals(false, LoopIncrementWithinBody.isPrime(-2));

    }
}
