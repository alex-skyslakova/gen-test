import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HeronTest {

    @Test
    void testHeronArea() {
        assertEquals(6.0, Heron.heronArea(3, 4, 5));
        assertEquals(96.0, Heron.heronArea(14,15,13));
        assertEquals(0.0, Heron.heronArea(1,2,3)); //Degenerate triangle
        assertEquals(24.0, Heron.heronArea(7,8,9));


    }

    @Test
    void testIsHeron() {
        assertTrue(Heron.isHeron(6.0));
        assertFalse(Heron.isHeron(6.1));
        assertFalse(Heron.isHeron(0));
        assertFalse(Heron.isHeron(-1));


    }

    @Test
    void testGcd() {
        assertEquals(1, Heron.gcd(3, 5));
        assertEquals(5, Heron.gcd(5, 10));
        assertEquals(3, Heron.gcd(6,9));
        assertEquals(2, Heron.gcd(4,6));
        assertEquals(1, Heron.gcd(7, 1));
    }


    @Test
    void testSort() {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{5, 5, 6, 16, 12});
        list.add(new int[]{3, 4, 5, 12, 6});
        list.add(new int[]{4,5,6,15, 12});

        Heron.sort(list);

        assertEquals(6, list.get(0)[4]);
        assertEquals(12, list.get(1)[4]);
        assertEquals(12, list.get(2)[4]);

        assertEquals(12, list.get(1)[3]);
        assertEquals(15, list.get(2)[3]);


    }


}
