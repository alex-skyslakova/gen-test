import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.Random

class AverageLoopLengthTest {

    @Test
    fun testAvg() {
        val rand = Random()
        val n = 5
        val tests = 100000
        var sum = 0

        for (t in 0 until tests) {
            val v = BooleanArray(n)
            var x = 0
            while (!v[x]) {
                v[x] = true
                sum++
                x = rand.nextInt(n)
            }
        }

        val average = sum.toDouble() / tests
        assertTrue(average > 0)
    }

    @Test
    fun testAna() {
        val n = 5
        val nn = n.toDouble()
        var term = 1.0
        var sum = 1.0

        for (i in n - 1 downTo 1) {
            term *= i / nn
            sum += term
        }

        val analytical = sum
        assertTrue(analytical > 0)
    }

    @Test
    fun testMain() {
        val nmax = 20
        val tests = 1000000
        val rand = Random()

        for (n in 1..nmax) {
            val a = avg(n, tests, rand)
            val b = ana(n)
            assertTrue(a > 0)
            assertTrue(b > 0)
            assertTrue(Math.abs(a - b) / b * 100.0 < 100) // Error should be less than 100%
        }
    }

    private fun avg(n: Int, tests: Int, rand: Random): Double {
        var sum = 0
        for (t in 0 until tests) {
            val v = BooleanArray(n)
            var x = 0
            while (!v[x]) {
                v[x] = true
                sum++
                x = rand.nextInt(n)
            }
        }
        return sum.toDouble() / tests
    }

    private fun ana(n: Int): Double {
        val nn = n.toDouble()
        var term = 1.0
        var sum = 1.0
        for (i in n - 1 downTo 1) {
            term *= i / nn
            sum += term
        }
        return sum
    }
}
