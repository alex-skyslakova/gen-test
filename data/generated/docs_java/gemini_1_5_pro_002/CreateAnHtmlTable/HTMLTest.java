import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HTMLTest {

    @Test
    void testArray2HTML_EmptyArray() {
        Object[][] array = {};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> HTML.array2HTML(array));
    }

    @Test
    void testArray2HTML_EmptyHeader() {
        Object[][] array = {{}, {1, 2, 3}};
        String expected = "<table><tr><td>1</td><td>2</td><td>3</td></tr></table>";
        assertEquals(expected, HTML.array2HTML(array));

    }


    @Test
    void testArray2HTML_SingleRow() {
        Object[][] array = {{"X", "Y", "Z"}, {1, 2, 3}};
        String expected = "<table><th>X</th><th>Y</th><th>Z</th><tr><td>1</td><td>2</td><td>3</td></tr></table>";
        assertEquals(expected, HTML.array2HTML(array));
    }

    @Test
    void testArray2HTML_MultipleRows() {
        Object[][] array = {{"", "X", "Y", "Z"}, {1, 1, 2, 3}, {2, 4, 5, 6}, {3, 7, 8, 9}};
        String expected = "<table><th></th><th>X</th><th>Y</th><th>Z</th><tr><td>1</td><td>1</td><td>2</td><td>3</td></tr><tr><td>2</td><td>4</td><td>5</td><td>6</td></tr><tr><td>3</td><td>7</td><td>8</td><td>9</td></tr></table>";
        assertEquals(expected, HTML.array2HTML(array));
    }

    @Test
    void testArray2HTML_DifferentDataTypes() {
        Object[][] array = {{"String", "Integer", "Boolean"}, {"Hello", 123, true}};
        String expected = "<table><th>String</th><th>Integer</th><th>Boolean</th><tr><td>Hello</td><td>123</td><td>true</td></tr></table>";
        assertEquals(expected, HTML.array2HTML(array));
    }

    @Test
    void testArray2HTML_NullValues() {
        Object[][] array = {{"A", "B"}, {null, 123}};
        String expected = "<table><th>A</th><th>B</th><tr><td>null</td><td>123</td></tr></table>"; 
        assertEquals(expected, HTML.array2HTML(array));
    }

}
