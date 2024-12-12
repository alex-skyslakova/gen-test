import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EulerSopConjectureTest {

    @Test
    public void testFifthPowersArray() {
        int maxNumber = 250;
        long[] fifth = new long[maxNumber];
        
        for (int i = 1; i <= maxNumber; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }
        
        // Check the first few calculated fifth powers
        assertEquals(1, fifth[0]); // 1^5
        assertEquals(32, fifth[1]); // 2^5
        assertEquals(243, fifth[2]); // 3^5
        assertEquals(1024, fifth[3]); // 4^5
        assertEquals(3125, fifth[4]); // 5^5
    }

    @Test
    public void testSearchForSolution() {
        int maxNumber = 250;
        long[] fifth = new long[maxNumber];
        
        for (int i = 1; i <= maxNumber; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }
        
        boolean found = false;
        int[] solution = new int[5];
        
        for (int a = 0; a < maxNumber && !found; a++) {
            for (int b = a; b < maxNumber && !found; b++) {
                for (int c = b; c < maxNumber && !found; c++) {
                    for (int d = c; d < maxNumber && !found; d++) {
                        long sum = fifth[a] + fifth[b] + fifth[c] + fifth[d];
                        int e = java.util.Arrays.binarySearch(fifth, sum);
                        found = (e >= 0);
                        if (found) {
                            solution[0] = a + 1;
                            solution[1] = b + 1;
                            solution[2] = c + 1;
                            solution[3] = d + 1;
                            solution[4] = e + 1;
                        }
                    }
                }
            }
        }
        
        assertTrue(found, "A solution should be found.");
        assertEquals(144, solution[0]);
        assertEquals(144, solution[1]);
        assertEquals(144, solution[2]);
        assertEquals(144, solution[3]);
        assertEquals(144, solution[4]);
    }
}
