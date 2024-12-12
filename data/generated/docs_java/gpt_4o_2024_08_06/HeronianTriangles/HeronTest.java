import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class HeronTest {

    @Test
    public void testHeronArea() {
        assertEquals(6.0, Heron.heronArea(3, 4, 5), 0.001);
        assertEquals(0.0, Heron.heronArea(1, 1, 2), 0.001); // Not a valid triangle
        assertEquals(84.0, Heron.heronArea(13, 14, 15), 0.001);
    }

    @Test
    public void testIsHeron() {
        assertTrue(Heron.isHeron(6.0));
        assertFalse(Heron.isHeron(6.1));
        assertFalse(Heron.isHeron(0.0)); // Area cannot be zero for a valid triangle
    }

    @Test
    public void testGcd() {
        assertEquals(1, Heron.gcd(3, 4));
        assertEquals(2, Heron.gcd(4, 6));
        assertEquals(5, Heron.gcd(15, 20));
        assertEquals(1, Heron.gcd(17, 31)); // Prime numbers
    }

    @Test
    public void testSort() {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{3, 4, 5, 12, 6});
        list.add(new int[]{5, 12, 13, 30, 30});
        list.add(new int[]{6, 8, 10, 24, 24});
        
        Heron.sort(list);

        assertArrayEquals(new int[]{3, 4, 5, 12, 6}, list.get(0));
        assertArrayEquals(new int[]{6, 8, 10, 24, 24}, list.get(1));
        assertArrayEquals(new int[]{5, 12, 13, 30, 30}, list.get(2));
    }

    @Test
    public void testPrimitiveHeronianTriangles() {
        ArrayList<int[]> list = new ArrayList<>();
        
        for (int c = 1; c <= 200; c++) {
            for (int b = 1; b <= c; b++) {
                for (int a = 1; a <= b; a++) {
                    if (Heron.gcd(Heron.gcd(a, b), c) == 1 && Heron.isHeron(Heron.heronArea(a, b, c))){
                        int area = (int) Heron.heronArea(a, b, c);
                        list.add(new int[]{a, b, c, a + b + c, area});
                    }
                }
            }
        }
        
        assertEquals(487, list.size()); // Expected number of primitive Heronian triangles
    }
}
