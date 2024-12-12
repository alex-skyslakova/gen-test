import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class ListRootedTreesTest {

    @Test
    public void testTreeEnumerationFor1Bag() {
        assertTreeEnumeration(1, Arrays.asList("()"));
    }

    @Test
    public void testTreeEnumerationFor2Bags() {
        assertTreeEnumeration(2, Arrays.asList("(())"));
    }

    @Test
    public void testTreeEnumerationFor3Bags() {
        assertTreeEnumeration(3, Arrays.asList("((()))", "(()())"));
    }

    @Test
    public void testTreeEnumerationFor4Bags() {
        assertTreeEnumeration(4, Arrays.asList("(()()())", "((())())", "((()()))", "(((())))"));
    }

    @Test
    public void testTreeEnumerationFor5Bags() {
        assertTreeEnumeration(5, Arrays.asList(
                "((()()()))", "(((()())))", "((((()))))", "(((())))()", "((()()))()",
                "((())(()))", "((())()())", "(()(()()))", "(()(())())", "(()()()())"
        ));
    }

    @Test
    public void testInvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> ListRootedTrees.test(0));
        assertThrows(IllegalArgumentException.class, () -> ListRootedTrees.test(13));
    }

    private void assertTreeEnumeration(int n, List<String> expectedTrees) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ListRootedTrees.test(n);

        String[] outputLines = outContent.toString().split(System.lineSeparator());
        int numberOfTrees = Integer.parseInt(outputLines[0].split(": ")[1]);
        assertEquals(expectedTrees.size(), numberOfTrees);

        for (int i = 1; i <= numberOfTrees; i++) {
            assertTrue(expectedTrees.contains(outputLines[i]));
        }

        System.setOut(System.out);
    }
}
