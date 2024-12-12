import org.junit.jupiter.api.Test;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;

public class FareyTest {

    @Test
    void testGenFarey1() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(1);
        assertEquals(2, farey.size());
        String expected = "[0/1, 1/1]";
        assertEquals(expected, farey.toString());
    }

    @Test
    void testGenFarey2() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(2);
        assertEquals(3, farey.size());
        String expected = "[0/1, 1/2, 1/1]";
        assertEquals(expected, farey.toString());
    }

    @Test
    void testGenFarey3() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(3);
        assertEquals(5, farey.size());
        String expected = "[0/1, 1/3, 1/2, 2/3, 1/1]";
        assertEquals(expected, farey.toString());
    }

    @Test
    void testGenFarey4() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(4);
        assertEquals(7, farey.size());
        String expected = "[0/1, 1/4, 1/3, 1/2, 2/3, 3/4, 1/1]";
        assertEquals(expected, farey.toString());
    }

    @Test
    void testGenFarey5() {
        TreeSet<Farey.Frac> farey = Farey.genFarey(5);
        assertEquals(11, farey.size());
        String expected = "[0/1, 1/5, 1/4, 1/3, 2/5, 1/2, 3/5, 2/3, 3/4, 4/5, 1/1]";
        assertEquals(expected, farey.toString());
    }
    
    @Test
    void testGenFareyLargeValues() {
        assertEquals(335, Farey.genFarey(100).size());
        assertEquals(31883, Farey.genFarey(1000).size());

    }

    //Tests for 6-11 can be generated similarly by following the pattern above.
    //This can be automated for more conciseness, but this approach demonstrates
    //the testing methodology more explicitly. For example:
    @Test
    void testGenFarey6() {
           TreeSet<Farey.Frac> farey = Farey.genFarey(6);
           assertEquals(13, farey.size());
           String expected = "[0/1, 1/6, 1/5, 1/4, 1/3, 2/5, 1/2, 3/5, 2/3, 3/4, 4/5, 5/6, 1/1]";
           assertEquals(expected, farey.toString());
       }
}
