import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayCallback7Test {

    @Test
    void testForEach() {
        int[] numbers = {1, 2, 3, 4, 5};
        StringBuilder result = new StringBuilder();
        
        ArrayCallback7.forEach(numbers, new ArrayCallback7.IntConsumer() {
            public void run(int x) {
                result.append(x).append(" ");
            }
        });
        
        assertEquals("1 2 3 4 5 ", result.toString());
    }

    @Test
    void testUpdate() {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] expected = {1, 4, 9, 16, 25};
        
        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });
        
        assertArrayEquals(expected, numbers);
    }

    @Test
    void testUpdateWithNegativeNumbers() {
        int[] numbers = {-1, -2, -3, -4, -5};
        int[] expected = {1, 4, 9, 16, 25};
        
        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });
        
        assertArrayEquals(expected, numbers);
    }

    @Test
    void testUpdateWithZero() {
        int[] numbers = {0, 1, 2, 3, 4};
        int[] expected = {0, 1, 4, 9, 16};
        
        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });
        
        assertArrayEquals(expected, numbers);
    }

    @Test
    void testForEachWithEmptyArray() {
        int[] numbers = {};
        StringBuilder result = new StringBuilder();
        
        ArrayCallback7.forEach(numbers, new ArrayCallback7.IntConsumer() {
            public void run(int x) {
                result.append(x).append(" ");
            }
        });
        
        assertEquals("", result.toString());
    }

    @Test
    void testUpdateWithEmptyArray() {
        int[] numbers = {};
        int[] expected = {};
        
        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });
        
        assertArrayEquals(expected, numbers);
    }
}
