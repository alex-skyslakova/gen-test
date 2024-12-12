import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class MultTest {

    @Test
    void testDoubleInt() {
        assertEquals(4, Mult.doubleInt(2));
        assertEquals(10, Mult.doubleInt(5));
        assertEquals(0, Mult.doubleInt(0));
        assertEquals(-4, Mult.doubleInt(-2));
    }

    @Test
    void testHalveInt() {
        assertEquals(1, Mult.halveInt(2));
        assertEquals(2, Mult.halveInt(5));
        assertEquals(0, Mult.halveInt(0));
        assertEquals(2, Mult.halveInt(5)); // Test for odd number handling
        assertEquals(Integer.MAX_VALUE /2, Mult.halveInt(Integer.MAX_VALUE));
        //Note: Testing negative number handling for unsigned right shift(>>>)
        assertEquals(2147483647, Mult.halveInt(-2));

    }

    @Test
    void testIsEven() {
        assertTrue(Mult.isEven(2));
        assertFalse(Mult.isEven(5));
        assertTrue(Mult.isEven(0));
        assertFalse(Mult.isEven(-1));
        assertTrue(Mult.isEven(-2));

    }


    @Test
    void testEthiopianMultiplication() {
        // Recreate main logic for testing
         testEthiopianMultiplicationInner(17, 34, 578);
         testEthiopianMultiplicationInner(2, 5, 10);
         testEthiopianMultiplicationInner(11, 7, 77);
         testEthiopianMultiplicationInner(0, 5, 0);
         testEthiopianMultiplicationInner(5, 0, 0);
         testEthiopianMultiplicationInner(0, 0, 0);
         testEthiopianMultiplicationInner(-5, 7, -35);
         testEthiopianMultiplicationInner(5, -7, -35);
         testEthiopianMultiplicationInner(-5, -7, 35);
    }


      void testEthiopianMultiplicationInner(int first, int second, int expected) {


        if(first < 0){
            first = -first;
            second = -second;
            expected = -expected; // adjust expected if negative input
        }
    
        Map<Integer, Integer> columns = new HashMap<Integer, Integer>();
            columns.put(first, second);
        int sum = Mult.isEven(first)? 0 : second;
        do{
          first = Mult.halveInt(first);
          second = Mult.doubleInt(second);
          columns.put(first, second);
          if(!Mult.isEven(first)){
              sum += second;
          }
        }while(first > 1);
    
        assertEquals(expected, sum);
    }
}
