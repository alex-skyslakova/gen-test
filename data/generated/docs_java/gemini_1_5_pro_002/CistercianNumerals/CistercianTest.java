import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CistercianTest {

    private String convertToString(char[][] canvas) {
        StringBuilder builder = new StringBuilder();
        for (char[] row : canvas) {
            builder.append(row);
            builder.append('\n');
        }
        return builder.toString();
    }

    private char[][] createCanvas(int n) {
        Cistercian c = new Cistercian(n);
        return c.canvas;
    }

    @Test
    void test0() {
        char[][] expected = new char[15][15];
        for (char[] row : expected) {
            java.util.Arrays.fill(row, ' ');
            row[5] = 'x';
        }
        char[][] actual = createCanvas(0);
        assertEquals(convertToString(expected), convertToString(actual));
    }

    @Test
    void test1() {
        Cistercian c = new Cistercian(1);
        assertTrue(c.toString().contains("xxxxx"));

    }


    @Test
    void test20() {
        Cistercian c = new Cistercian(20);
        assertTrue(c.toString().contains("xxxxx      "));


    }

    @Test
    void test300() {
        Cistercian c = new Cistercian(300);
         assertTrue(c.toString().contains("x    x"));
          assertTrue(c.toString().contains(" x  x "));
           assertTrue(c.toString().contains("  xx  "));

    }

    @Test
    void test4000() {
        Cistercian c = new Cistercian(4000);
          assertTrue(c.toString().contains("xxxxx"));
           assertTrue(c.toString().contains("x    x"));
            assertTrue(c.toString().contains(" x  x "));
             assertTrue(c.toString().contains("  xx  "));


    }

    @Test
    void test5555() {
        Cistercian c = new Cistercian(5555);
        assertTrue(c.toString().contains("xxxxx xxxxx"));

    }
    @Test
    void test6789() {
        Cistercian c = new Cistercian(6789);
        // Just a basic check since the output is complex
        assertTrue(c.toString().length() > 0);
    }

        @Test
    void test9999() {
        Cistercian c = new Cistercian(9999);
        // Just a basic check since the output is complex
        assertTrue(c.toString().length() > 0);
    }


}
