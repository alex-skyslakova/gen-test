import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoopIncrementWithinBodyTest {

    @Test
    public void testIsPrime() {
        assertTrue(LoopIncrementWithinBody.isPrime(2));
        assertTrue(LoopIncrementWithinBody.isPrime(3));
        assertFalse(LoopIncrementWithinBody.isPrime(4));
        assertTrue(LoopIncrementWithinBody.isPrime(5));
        assertFalse(LoopIncrementWithinBody.isPrime(6));
        assertTrue(LoopIncrementWithinBody.isPrime(7));
        assertFalse(LoopIncrementWithinBody.isPrime(8));
        assertFalse(LoopIncrementWithinBody.isPrime(9));
        assertFalse(LoopIncrementWithinBody.isPrime(10));
        assertTrue(LoopIncrementWithinBody.isPrime(11));
        assertFalse(LoopIncrementWithinBody.isPrime(12));
        assertTrue(LoopIncrementWithinBody.isPrime(13));
    }

    @Test
    public void testMainOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        LoopIncrementWithinBody.main(new String[]{});

        String expectedOutput = "n = 1                  43\n" +
                                "n = 2                 131\n" +
                                "n = 3                 379\n" +
                                "n = 4               1,061\n" +
                                "n = 5               3,119\n" +
                                "n = 6               9,341\n" +
                                "n = 7              28,019\n" +
                                "n = 8              84,071\n" +
                                "n = 9             252,209\n" +
                                "n = 10            756,641\n" +
                                "n = 11          2,269,939\n" +
                                "n = 12          6,809,831\n" +
                                "n = 13         20,429,509\n" +
                                "n = 14         61,288,541\n" +
                                "n = 15        183,865,639\n" +
                                "n = 16        551,596,931\n" +
                                "n = 17      1,654,790,809\n" +
                                "n = 18      4,964,372,441\n" +
                                "n = 19     14,893,117,339\n" +
                                "n = 20     44,679,352,031\n" +
                                "n = 21    134,038,056,109\n" +
                                "n = 22    402,114,168,341\n" +
                                "n = 23  1,206,342,505,039\n" +
                                "n = 24  3,619,027,515,131\n" +
                                "n = 25 10,857,082,545,409\n" +
                                "n = 26 32,571,247,636,241\n" +
                                "n = 27 97,713,742,908,739\n" +
                                "n = 28 293,141,228,726,231\n" +
                                "n = 29 879,423,686,178,709\n" +
                                "n = 30 2,638,271,058,536,141\n" +
                                "n = 31 7,914,813,175,608,439\n" +
                                "n = 32 23,744,439,526,825,331\n" +
                                "n = 33 71,233,318,580,476,009\n" +
                                "n = 34 213,699,955,741,428,041\n" +
                                "n = 35 641,099,867,224,284,139\n" +
                                "n = 36 1,923,299,601,672,852,431\n" +
                                "n = 37 5,769,898,805,018,557,309\n" +
                                "n = 38 17,309,696,415,055,671,941\n" +
                                "n = 39 51,929,089,245,167,015,839\n" +
                                "n = 40 155,787,267,735,501,047,531\n" +
                                "n = 41 467,361,803,206,503,142,609\n" +
                                "n = 42 1,402,085,409,619,509,427,841\n";

        assertEquals(expectedOutput, outContent.toString());
    }
}
