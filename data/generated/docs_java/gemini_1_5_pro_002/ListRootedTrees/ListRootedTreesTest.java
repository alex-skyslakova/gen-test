import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListRootedTreesTest {

    @Test
    void test1() {
        testOutput(1, "Number of 1-trees: 1\n()\n");
    }

    @Test
    void test2() {
        testOutput(2, "Number of 2-trees: 1\n(())\n");

    }
    @Test
    void test3() {
        testOutput(3, "Number of 3-trees: 2\n((()))\n(()())\n");
    }

    @Test
    void test4() {
        testOutput(4, "Number of 4-trees: 4\n(((())))\n((()()))\n((())())\n(()()())\n");
    }

    @Test
    void test5() {
        testOutput(5, "Number of 5-trees: 9\n((((()))))\n(((()())))\n(((())()))\n((()()()))\n((())()())\n(()(()()))\n(()(())())\n(()()()())\n(()()())()\n");
    }


    @Test
    void testInvalidInputLow() {
        assertThrows(IllegalArgumentException.class, () -> ListRootedTrees.test(0));
    }

    @Test
    void testInvalidInputHigh() {
        assertThrows(IllegalArgumentException.class, () -> ListRootedTrees.test(13));
    }


    private void testOutput(int n, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ListRootedTrees.test(n);

        assertEquals(expectedOutput, outputStream.toString());

        // Resetting the System.out for other tests.
        System.setOut(System.out); 
    }

    @Test
    void makeTreesTest() {
        ListRootedTrees.makeTrees(5);
        assertEquals(14, ListRootedTrees.OFFSET.get(6)); // Verify offset is correctly set for n+1 after making trees
    }

    @Test
    void appendTest(){
        List<Long> originalTreeList = new ArrayList<>(ListRootedTrees.TREE_LIST); // Back up the original list
        ListRootedTrees.append(12345L);
        assertEquals((1L | (12345L << 1)), ListRootedTrees.TREE_LIST.get(ListRootedTrees.TREE_LIST.size() - 1));

        ListRootedTrees.TREE_LIST.clear();
        ListRootedTrees.TREE_LIST.addAll(originalTreeList); // Restore original list for other tests
    }



    @Test
    void assembleTest(){
         List<Long> originalTreeList = new ArrayList<>(ListRootedTrees.TREE_LIST); // Back up the original list

         // Setup some initial OFFSET values for the test.
         ListRootedTrees.OFFSET.set(1,1);
         ListRootedTrees.OFFSET.set(2,2);
         ListRootedTrees.TREE_LIST.add(0L);
         ListRootedTrees.TREE_LIST.add(1L);


         ListRootedTrees.assemble(2, 0, 1, 1, 1);
         assertEquals(3, ListRootedTrees.TREE_LIST.size()); // Check if a tree was appended

        ListRootedTrees.TREE_LIST.clear();
        ListRootedTrees.OFFSET.clear();
        for (int i = 0; i < 32; i++) {
            if (i == 1) {
                ListRootedTrees.OFFSET.add(1);
            } else {
                ListRootedTrees.OFFSET.add(0);
            }
        }
        ListRootedTrees.TREE_LIST.addAll(originalTreeList); // Restore original list for other tests
    }



}
