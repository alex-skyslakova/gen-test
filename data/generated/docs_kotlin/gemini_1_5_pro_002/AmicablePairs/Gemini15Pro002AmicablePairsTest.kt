import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AmicablePairsTest {

    @Test
    fun testSumProperDivisors() {
        assertEquals(0, sumProperDivisors(1))
        assertEquals(1, sumProperDivisors(2))
        assertEquals(2, sumProperDivisors(3))
        assertEquals(3, sumProperDivisors(4))
        assertEquals(1, sumProperDivisors(5))
        assertEquals(6, sumProperDivisors(6))
        assertEquals(1, sumProperDivisors(7))
        assertEquals(7, sumProperDivisors(8))
        assertEquals(4, sumProperDivisors(9))
        assertEquals(7, sumProperDivisors(10))
        assertEquals(1, sumProperDivisors(11))
        assertEquals(16, sumProperDivisors(12))
        assertEquals(1210, sumProperDivisors(1184))
        assertEquals(1184, sumProperDivisors(1210))

    }


    fun sumProperDivisors(n: Int): Int {
        if (n < 2) return 0
        return (1..n / 2).filter { (n % it) == 0 }.sum()
    }

    @Test
    fun testAmicablePairs() {
      // Testing the main logic indirectly by checking known amicable pairs
      val sum = IntArray(20000, { sumProperDivisors(it) })
      
      // Verify that known amicable pairs are identified by the logic
      
      //220 and 284
      assertEquals(284, sum[220])
      assertEquals(220, sum[284])
      
      //1184 and 1210
      assertEquals(1210, sum[1184])
      assertEquals(1184, sum[1210])
      
      //2620 and 2924
       assertEquals(2924, sum[2620])
      assertEquals(2620, sum[2924])
      
      //5020 and 5564
      assertEquals(5564, sum[5020])
      assertEquals(5020, sum[5564])
      
       //6232 and 6368
      assertEquals(6368, sum[6232])
      assertEquals(6232, sum[6368])
      
      //10744 and 10856
      assertEquals(10856, sum[10744])
      assertEquals(10744, sum[10856])
      
       //12285 and 14595
      assertEquals(14595, sum[12285])
      assertEquals(12285, sum[14595])
      
      //17296 and 18416
      assertEquals(18416, sum[17296])
      assertEquals(17296, sum[18416])


      // Verify that non-amicable pairs are not falsely identified
      assertEquals(1, sum[2]) // prime number - non amicable

      
    }
}

