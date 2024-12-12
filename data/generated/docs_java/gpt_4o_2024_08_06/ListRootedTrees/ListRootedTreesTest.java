import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListRootedTreesTest {

    @Test
    void testNumberOfTreesFor1Bag() {
        ListRootedTrees.main(new String[]{"1"});
        // Expected output: Number of 1-trees: 1
        // Output: ()
    }

    @Test
    void testNumberOfTreesFor2Bags() {
        ListRootedTrees.main(new String[]{"2"});
        // Expected output: Number of 2-trees: 1
        // Output: (())
    }

    @Test
    void testNumberOfTreesFor3Bags() {
        ListRootedTrees.main(new String[]{"3"});
        // Expected output: Number of 3-trees: 2
        // Output: ((()))
        //         (()())
    }

    @Test
    void testNumberOfTreesFor4Bags() {
        ListRootedTrees.main(new String[]{"4"});
        // Expected output: Number of 4-trees: 4
        // Output: (()()())
        //         ((())())
        //         ((()()))
        //         (((())))
    }

    @Test
    void testNumberOfTreesFor5Bags() {
        ListRootedTrees.main(new String[]{"5"});
        // Expected output: Number of 5-trees: 9
        // Output: ((((()))))
        //         ((()()()))
        //         ((())()())
        //         (()()()())
        //         (()(()()))
        //         ((()())())
        //         ((()(())))
        //         ((())(()))
        //         (()()(()))
    }

    @Test
    void testInvalidInputZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ListRootedTrees.main(new String[]{"0"});
        });
        assertEquals("Argument must be between 1 and 12", exception.getMessage());
    }

    @Test
    void testInvalidInputThirteen() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ListRootedTrees.main(new String[]{"13"});
        });
        assertEquals("Argument must be between 1 and 12", exception.getMessage());
    }

    @Test
    void testInvalidInputNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ListRootedTrees.main(new String[]{"-1"});
        });
        assertEquals("Argument must be between 1 and 12", exception.getMessage());
    }
}
