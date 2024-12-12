import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CistercianTest {

    @Test
    public void testZero() {
        Cistercian cistercian = new Cistercian(0);
        String expected = 
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testOne() {
        Cistercian cistercian = new Cistercian(1);
        String expected = 
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testTwenty() {
        Cistercian cistercian = new Cistercian(20);
        String expected = 
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testThreeHundred() {
        Cistercian cistercian = new Cistercian(300);
        String expected = 
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     xxxxx \n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testFourThousand() {
        Cistercian cistercian = new Cistercian(4000);
        String expected = 
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx x     \n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testFiveThousandFiveHundredFiftyFive() {
        Cistercian cistercian = new Cistercian(5555);
        String expected = 
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testSixThousandSevenHundredEightyNine() {
        Cistercian cistercian = new Cistercian(6789);
        String expected = 
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testNineThousandNineHundredNinetyNine() {
        Cistercian cistercian = new Cistercian(9999);
        String expected = 
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testCustomNumber() {
        Cistercian cistercian = new Cistercian(1234);
        String expected = 
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx\n" +
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }
}
