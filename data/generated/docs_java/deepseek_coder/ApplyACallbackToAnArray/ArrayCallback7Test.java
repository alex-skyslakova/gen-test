import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayCallback7Test {

    @Test
    public void testForEach() {
        int[] numbers = {1, 2, 3, 4, 5};
        StringBuilder result = new StringBuilder();

        ArrayCallback7.forEach(numbers, new ArrayCallback7.IntConsumer() {
            @Override
            public void run(int x) {
                result.append(x).append(" ");
            }
        });

        assertEquals("1 2 3 4 5 ", result.toString());
    }

    @Test
    public void testUpdate() {
        int[] numbers = {1, 2, 3, 4, 5};

        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });

        assertArrayEquals(new int[]{1, 4, 9, 16, 25}, numbers);
    }

    @Test
    public void testForEachEmptyArray() {
        int[] numbers = {};
        StringBuilder result = new StringBuilder();

        ArrayCallback7.forEach(numbers, new ArrayCallback7.IntConsumer() {
            @Override
            public void run(int x) {
                result.append(x).append(" ");
            }
        });

        assertEquals("", result.toString());
    }

    @Test
    public void testUpdateEmptyArray() {
        int[] numbers = {};

        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });

        assertArrayEquals(new int[]{}, numbers);
    }

    @Test
    public void testForEachNegativeNumbers() {
        int[] numbers = {-1, -2, -3, -4, -5};
        StringBuilder result = new StringBuilder();

        ArrayCallback7.forEach(numbers, new ArrayCallback7.IntConsumer() {
            @Override
            public void run(int x) {
                result.append(x).append(" ");
            }
        });

        assertEquals("-1 -2 -3 -4 -5 ", result.toString());
    }

    @Test
    public void testUpdateNegativeNumbers() {
        int[] numbers = {-1, -2, -3, -4, -5};

        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });

        assertArrayEquals(new int[]{1, 4, 9, 16, 25}, numbers);
    }

    @Test
    public void testForEachMixedNumbers() {
        int[] numbers = {-1, 2, -3, 4, -5};
        StringBuilder result = new StringBuilder();

        ArrayCallback7.forEach(numbers, new ArrayCallback7.IntConsumer() {
            @Override
            public void run(int x) {
                result.append(x).append(" ");
            }
        });

        assertEquals("-1 2 -3 4 -5 ", result.toString());
    }

    @Test
    public void testUpdateMixedNumbers() {
        int[] numbers = {-1, 2, -3, 4, -5};

        ArrayCallback7.update(numbers, new ArrayCallback7.IntToInt() {
            @Override
            public int run(int x) {
                return x * x;
            }
        });

        assertArrayEquals(new int[]{1, 4, 9, 16, 25}, numbers);
    }
}
