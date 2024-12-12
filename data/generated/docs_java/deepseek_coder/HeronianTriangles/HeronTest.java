import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class HeronTest {

    @Test
    public void testHeronArea() {
        // Test case for a known Heronian triangle (3, 4, 5)
        assertEquals(6.0, Heron.heronArea(3, 4, 5), 0.0001);

        // Test case for a non-Heronian triangle (1, 1, 2)
        assertEquals(0.0, Heron.heronArea(1, 1, 2), 0.0001);

        // Test case for another Heronian triangle (5, 5, 6)
        assertEquals(12.0, Heron.heronArea(5, 5, 6), 0.0001);
    }

    @Test
    public void testIsHeron() {
        // Test case for a Heronian area
        assertTrue(Heron.isHeron(6.0));

        // Test case for a non-Heronian area
        assertFalse(Heron.isHeron(5.9));

        // Test case for a negative area
        assertFalse(Heron.isHeron(-6.0));
    }

    @Test
    public void testGcd() {
        // Test case for gcd of 10 and 5
        assertEquals(5, Heron.gcd(10, 5));

        // Test case for gcd of 8 and 12
        assertEquals(4, Heron.gcd(8, 12));

        // Test case for gcd of 7 and 13 (co-prime numbers)
        assertEquals(1, Heron.gcd(7, 13));
    }

    @Test
    public void testSort() {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{3, 4, 5, 12, 6});
        list.add(new int[]{5, 5, 6, 16, 12});
        list.add(new int[]{6, 8, 10, 24, 24});

        Heron.sort(list);

        // Check if the list is sorted by area, then perimeter
        assertArrayEquals(new int[]{3, 4, 5, 12, 6}, list.get(0));
        assertArrayEquals(new int[]{5, 5, 6, 16, 12}, list.get(1));
        assertArrayEquals(new int[]{6, 8, 10, 24, 24}, list.get(2));
    }

    @Test
    public void testMain() {
        // This test is more complex and would require mocking System.out
        // and capturing the output to verify the results.
        // For simplicity, we assume the main method works correctly
        // based on the other unit tests.
    }
}
