import org.junit.jupiter.api.Test
import java.math.BigInteger
import java.util.*
import kotlin.test.assertEquals

class HammingTest {

    private val Three = BigInteger.valueOf(3)
    private val Five = BigInteger.valueOf(5)

    private fun updateFrontier(x: BigInteger, pq: PriorityQueue<BigInteger>) {
        pq.add(x.shiftLeft(1))
        pq.add(x.multiply(Three))
        pq.add(x.multiply(Five))
    }

    private fun hamming(n: Int): BigInteger {
        val frontier = PriorityQueue<BigInteger>()
        updateFrontier(BigInteger.ONE, frontier)
        var lowest = BigInteger.ONE
        for (i in 1..n - 1) {
            lowest = frontier.poll() ?: lowest
            while (frontier.peek() == lowest)
                frontier.poll()
            updateFrontier(lowest, frontier)
        }
        return lowest
    }


    @Test
    fun testFirstTwentyHammingNumbers() {
        val expected = listOf<BigInteger>(
            BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4),
            BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(9),
            BigInteger.valueOf(10), BigInteger.valueOf(12), BigInteger.valueOf(15), BigInteger.valueOf(16),
            BigInteger.valueOf(18), BigInteger.valueOf(20), BigInteger.valueOf(24), BigInteger.valueOf(25),
            BigInteger.valueOf(27), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(36)
        )
        for (i in 1..20) {
            assertEquals(expected[i - 1], hamming(i))
        }
    }

    @Test
    fun test1691stHammingNumber() {
        assertEquals(BigInteger.valueOf(231), hamming(1691))
    }


    @Test
    fun testMillionthHammingNumber() {
       assertEquals(BigInteger("5105109830789532384592893836920274809593121278837381328060216531220663763831089926749699407119627341489486978475820567611369198265801509784165550009193942471651007775098830475532606453955470426195762883972498238103931529760965237447374901"), hamming(1000000))
    }



}
