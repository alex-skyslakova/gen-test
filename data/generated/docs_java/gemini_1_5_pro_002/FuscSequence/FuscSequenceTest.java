import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuscSequenceTest {

    @Test
    void testFuscCalculation() {
        assertEquals(0, FuscSequence.fusc[0]);
        assertEquals(1, FuscSequence.fusc[1]);
        assertEquals(1, FuscSequence.fusc[2]);
        assertEquals(2, FuscSequence.fusc[3]);
        assertEquals(1, FuscSequence.fusc[4]);
        assertEquals(3, FuscSequence.fusc[5]);
        assertEquals(2, FuscSequence.fusc[6]);
        assertEquals(3, FuscSequence.fusc[7]);
        assertEquals(1, FuscSequence.fusc[8]);
        assertEquals(4, FuscSequence.fusc[9]);
        assertEquals(3, FuscSequence.fusc[10]);
        assertEquals(5, FuscSequence.fusc[11]);
        assertEquals(2, FuscSequence.fusc[12]);
        assertEquals(5, FuscSequence.fusc[13]);
        assertEquals(3, FuscSequence.fusc[14]);
        assertEquals(4, FuscSequence.fusc[15]);

        // Test larger values
        assertEquals(832040, FuscSequence.fusc[1000000]);
        assertEquals(1186569, FuscSequence.fusc[1500000]);


        assertEquals(478068, FuscSequence.fusc[3056]);


    }

    @Test
    void testFuscReverseBinaryProperty() {

        // Examples
        int a1 = 10; // Binary 1010
        int b1 = Integer.parseInt(new StringBuilder(Integer.toBinaryString(a1)).reverse().toString(), 2); //Binary 0101 = 5
        assertEquals(FuscSequence.fusc[a1], FuscSequence.fusc[b1]);


        int a2 = 18; //Binary 10010
        int b2 = Integer.parseInt(new StringBuilder(Integer.toBinaryString(a2)).reverse().toString(), 2);  // Binary 01001 = 9
        assertEquals(FuscSequence.fusc[a2], FuscSequence.fusc[b2]);

        int a3 = 255;
        int b3 = Integer.parseInt(new StringBuilder(Integer.toBinaryString(a3)).reverse().toString(), 2);
        assertEquals(FuscSequence.fusc[a3], FuscSequence.fusc[b3]);



    }
}
