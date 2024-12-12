import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HTMLTest {

    @Test
    public void testArray2HTML_BasicCase() {
        Object[][] ints = {
            {"", "X", "Y", "Z"},
            {1, 1, 2, 3},
            {2, 4, 5, 6},
            {3, 7, 8, 9},
            {4, 10, 11, 12}
        };

        String expectedHTML = "<table><th></th><th>X</th><th>Y</th><th>Z</th><tr><td>1</td><td>1</td><td>2</td><td>3</td></tr><tr><td>2</td><td>4</td><td>5</td><td>6</td></tr><tr><td>3</td><td>7</td><td>8</td><td>9</td></tr><tr><td>4</td><td>10</td><td>11</td><td>12</td></tr></table>";

        String result = HTML.array2HTML(ints);
        assertEquals(expectedHTML, result);
    }

    @Test
    public void testArray2HTML_EmptyArray() {
        Object[][] ints = {};

        String expectedHTML = "<table></table>";

        String result = HTML.array2HTML(ints);
        assertEquals(expectedHTML, result);
    }

    @Test
    public void testArray2HTML_SingleRow() {
        Object[][] ints = {
            {"", "X", "Y", "Z"}
        };

        String expectedHTML = "<table><th></th><th>X</th><th>Y</th><th>Z</th></table>";

        String result = HTML.array2HTML(ints);
        assertEquals(expectedHTML, result);
    }

    @Test
    public void testArray2HTML_SingleColumn() {
        Object[][] ints = {
            {"X"},
            {1},
            {2},
            {3}
        };

        String expectedHTML = "<table><th>X</th><tr><td>1</td></tr><tr><td>2</td></tr><tr><td>3</td></tr></table>";

        String result = HTML.array2HTML(ints);
        assertEquals(expectedHTML, result);
    }

    @Test
    public void testArray2HTML_DifferentDataTypes() {
        Object[][] ints = {
            {"", "X", "Y", "Z"},
            {1, "one", 2.5, true},
            {2, "two", 3.5, false},
            {3, "three", 4.5, true}
        };

        String expectedHTML = "<table><th></th><th>X</th><th>Y</th><th>Z</th><tr><td>1</td><td>one</td><td>2.5</td><td>true</td></tr><tr><td>2</td><td>two</td><td>3.5</td><td>false</td></tr><tr><td>3</td><td>three</td><td>4.5</td><td>true</td></tr></table>";

        String result = HTML.array2HTML(ints);
        assertEquals(expectedHTML, result);
    }
}
