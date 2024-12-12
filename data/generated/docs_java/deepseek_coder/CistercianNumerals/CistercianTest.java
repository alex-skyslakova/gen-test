import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CistercianTest {

    @Test
    public void testCistercianZero() {
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
    public void testCistercianOne() {
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
    public void testCistercianTwenty() {
        Cistercian cistercian = new Cistercian(20);
        String expected =
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
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
                "     x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testCistercianThreeHundred() {
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
    public void testCistercianFourThousand() {
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
                "     x     \n" +
                "xxxxx x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testCistercianFiveThousandFiveHundredFiftyFive() {
        Cistercian cistercian = new Cistercian(5555);
        String expected =
                "xxxxx xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx \n" +
                "xxxxx x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testCistercianSixThousandSevenHundredEightyNine() {
        Cistercian cistercian = new Cistercian(6789);
        String expected =
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx \n" +
                "xxxxx x     \n";
        assertEquals(expected, cistercian.toString());
    }

    @Test
    public void testCistercianNineThousandNineHundredNinetyNine() {
        Cistercian cistercian = new Cistercian(9999);
        String expected =
                "xxxxx xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "     x     \n" +
                "xxxxx xxxxx \n" +
                "xxxxx x     \n";
        assertEquals(expected, cistercian.toString());
    }
}
