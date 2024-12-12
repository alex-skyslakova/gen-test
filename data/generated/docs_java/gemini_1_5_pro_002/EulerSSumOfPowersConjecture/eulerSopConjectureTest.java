import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class eulerSopConjectureTest {

    @Test
    public void testMain() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method
        eulerSopConjecture.main(null);

        // Check if the expected output is present
        String expectedOutput = "27^5 + 84^5 + 110^5 + 144^5 = 133^5";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Restore System.out
        System.setOut(System.out); 
    }


    @Test
    public void testFifthPowerCalculation() {
        long[] fifth = new long[eulerSopConjecture.MAX_NUMBER];
        for (int i = 1; i <= eulerSopConjecture.MAX_NUMBER; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }

        assertEquals(1, fifth[0]);
        assertEquals(32, fifth[1]);
        assertEquals(243, fifth[2]);
        assertEquals(1024, fifth[3]);
        assertEquals(3125, fifth[4]);
         // Add more assertions for other values as needed, especially boundary cases
        assertEquals(24876584375L, fifth[249]); // Test the last element
    }


     @Test
    public void testBinarySearchFound(){
         long[] fifth = new long[eulerSopConjecture.MAX_NUMBER];
        for (int i = 1; i <= eulerSopConjecture.MAX_NUMBER; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }
        int index = java.util.Arrays.binarySearch(fifth, 3125);
        assertEquals(4, index); // 3125 is 5^5, at index 4 (since arrays are 0-indexed)
    }

    @Test
    public void testBinarySearchNotFound(){
         long[] fifth = new long[eulerSopConjecture.MAX_NUMBER];
        for (int i = 1; i <= eulerSopConjecture.MAX_NUMBER; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }
        int index = java.util.Arrays.binarySearch(fifth, 12345); // Not a fifth power in the range
        assertTrue(index < 0); // binarySearch returns a negative value if not found.
    }


}
