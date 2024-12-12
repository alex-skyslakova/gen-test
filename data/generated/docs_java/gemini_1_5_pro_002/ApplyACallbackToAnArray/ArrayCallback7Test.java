import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;


public class ArrayCallback7Test {

    @Test
    void testForEach_emptyArray() {
        int[] arr = {};
        StringBuilder sb = new StringBuilder();
        ArrayCallback7.forEach(arr, x -> sb.append(x).append(" "));

        assertEquals("", sb.toString().trim());
    }

    @Test
    void testForEach_nonEmptyArray() {
        int[] arr = {1, 2, 3};
        StringBuilder sb = new StringBuilder();
        ArrayCallback7.forEach(arr, x -> sb.append(x).append(" "));

        assertEquals("1 2 3", sb.toString().trim());
    }

    @Test
    void testUpdate_emptyArray() {
        int[] arr = {};
        ArrayCallback7.update(arr, x -> x * 2);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testUpdate_nonEmptyArray() {
        int[] arr = {1, 2, 3};
        ArrayCallback7.update(arr, x -> x * 2);
        assertArrayEquals(new int[]{2, 4, 6}, arr);
    }

    @Test
    void testUpdate_withZero() {
        int[] arr = {0, 1, 2};
        ArrayCallback7.update(arr, x -> x * 2 );
        assertArrayEquals(new int[]{0, 2, 4}, arr);
    }

    @Test
    void testUpdate_withNegativeNumbers() {
        int[] arr = {-1, -2, -3};
        ArrayCallback7.update(arr, x -> x * 2 );
        assertArrayEquals(new int[]{-2, -4, -6}, arr);

    }

    @Test
    void testMain_output() {
        // Redirect System.out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Simulate main method execution
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayCallback7.main(new String[]{});


        String expectedOutput = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n1\n4\n9\n16\n25\n36\n49\n64\n81\n100\n";

        // Restore System.out
        System.setOut(System.out); 

        assertEquals(expectedOutput, out.toString());
    }
}
