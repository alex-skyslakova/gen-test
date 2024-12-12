import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LuckyNumbersTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void testSingleLuckyNumber() {
        runTest(new String[]{"1"}, "LuckyNumber(1) = 1\n");
        runTest(new String[]{"2", "lucky"}, "LuckyNumber(2) = 3\n");
    }

    @Test
    void testSingleEvenLuckyNumber() {
        runTest(new String[]{"1", "evenLucky"}, "EvenLuckyNumber(1) = 2\n");
        runTest(new String[]{"2", "evenLucky"}, "EvenLuckyNumber(2) = 4\n");

    }

    @Test
    void testLuckyNumberRangeByIndex() {
        runTest(new String[]{"1", "3"}, "LuckyNumber(1) through LuckyNumber(3) = [1, 3, 7]\n");
        runTest(new String[]{"1", "3", "lucky"}, "LuckyNumber(1) through LuckyNumber(3) = [1, 3, 7]\n");

    }


    @Test
    void testEvenLuckyNumberRangeByIndex() {
        runTest(new String[]{"1", "3", "evenLucky"}, "EvenLuckyNumber(1) through EvenLuckyNumber(3) = [2, 4, 6]\n");
    }


    @Test
    void testLuckyNumberRangeByValue() {
        runTest(new String[]{"5", "-15"},"Lucky Numbers in the range 5 to 15 inclusive = [7, 9, 13, 15]\n");
        runTest(new String[]{"5", "-15", "lucky"},"Lucky Numbers in the range 5 to 15 inclusive = [7, 9, 13, 15]\n");
    }
    
    @Test
    void testEvenLuckyNumberRangeByValue() {
         runTest(new String[]{"6", "-18", "evenLucky"}, "Even Lucky Numbers in the range 6 to 18 inclusive = [6, 10, 12, 14, 18]\n");
    }
    

    @Test
    void testInvalidInput() {
        runTest(new String[]{}, ""); // No arguments
        runTest(new String[]{"a"}, ""); // Invalid number format
        runTest(new String[]{"1", "2", "3", "4"},""); // Too many arguments
        runTest(new String[]{"1", "b"}, ""); // Invalid second argument
        runTest(new String[]{"1", "-2", "c"},""); // Invalid third argument
    }


    @Test
    void testCaseInsensitivity(){
        runTest(new String[]{"1", "LuCkY"}, "LuckyNumber(1) = 1\n");
        runTest(new String[]{"1", "EvEnLuCkY"}, "EvenLuckyNumber(1) = 2\n");
    }

     private void runTest(String[] args, String expectedOutput) {
        System.setOut(new PrintStream(outContent));
        LuckyNumbers.main(args);
        System.setOut(originalOut);
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();
    }




}
