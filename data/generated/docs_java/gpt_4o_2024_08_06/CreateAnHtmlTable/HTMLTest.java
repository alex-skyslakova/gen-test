import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HTMLTest {

    @Test
    public void testArray2HTMLWithSequentialIntegers() {
        Object[][] input = {
            {"", "X", "Y", "Z"},
            {1, 1000, 2000, 3000},
            {2, 4000, 5000, 6000},
            {3, 7000, 8000, 9000}
        };
        String expectedOutput = "<table><th></th><th>X</th><th>Y</th><th>Z</th>"
                + "<tr><td>1</td><td>1000</td><td>2000</td><td>3000</td></tr>"
                + "<tr><td>2</td><td>4000</td><td>5000</td><td>6000</td></tr>"
                + "<tr><td>3</td><td>7000</td><td>8000</td><td>9000</td></tr>"
                + "</table>";
        assertEquals(expectedOutput, HTML.array2HTML(input));
    }

    @Test
    public void testArray2HTMLWithRandomIntegers() {
        Object[][] input = {
            {"", "X", "Y", "Z"},
            {1, 1234, 5678, 9101},
            {2, 1122, 3344, 5566},
            {3, 7788, 9900, 1112}
        };
        String expectedOutput = "<table><th></th><th>X</th><th>Y</th><th>Z</th>"
                + "<tr><td>1</td><td>1234</td><td>5678</td><td>9101</td></tr>"
                + "<tr><td>2</td><td>1122</td><td>3344</td><td>5566</td></tr>"
                + "<tr><td>3</td><td>7788</td><td>9900</td><td>1112</td></tr>"
                + "</table>";
        assertEquals(expectedOutput, HTML.array2HTML(input));
    }

    @Test
    public void testArray2HTMLWithMinimumIntegers() {
        Object[][] input = {
            {"", "X", "Y", "Z"},
            {1, 1, 2, 3},
            {2, 4, 5, 6},
            {3, 7, 8, 9}
        };
        String expectedOutput = "<table><th></th><th>X</th><th>Y</th><th>Z</th>"
                + "<tr><td>1</td><td>1</td><td>2</td><td>3</td></tr>"
                + "<tr><td>2</td><td>4</td><td>5</td><td>6</td></tr>"
                + "<tr><td>3</td><td>7</td><td>8</td><td>9</td></tr>"
                + "</table>";
        assertEquals(expectedOutput, HTML.array2HTML(input));
    }
}
