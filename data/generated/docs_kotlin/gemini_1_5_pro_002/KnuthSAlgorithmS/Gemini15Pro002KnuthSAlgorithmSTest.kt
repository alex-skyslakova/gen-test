import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.Random
import kotlin.collections.ArrayList

class KnuthSAlgorithmSTest {

    private fun <T> s_of_n_creator(n: Int): (T) -> List<T> {
        val sample = ArrayList<T>(n)
        var i = 0
        val rand = Random()

        return { item: T ->
            if (++i <= n) {
                sample.add(item)
            } else if (rand.nextInt(i) < n) {
                sample[rand.nextInt(n)] = item
            }
            sample
        }
    }


    @Test
    fun test_s_of_n_creator_empty() {
        val s_of_n = s_of_n_creator<Int>(3)
        assertTrue(s_of_n(1).contains(1))
    }

    @Test
    fun test_s_of_n_creator_full() {
        val s_of_n = s_of_n_creator<Int>(3)
        s_of_n(1)
        s_of_n(2)
        s_of_n(3)
        assertEquals(3, s_of_n(4).size)
    }

    @Test
    fun test_s_of_n_creator_replacement() {
        val s_of_n = s_of_n_creator<Int>(1)
        s_of_n(1)
        val result = s_of_n(2)
        assertTrue(result.contains(1) || result.contains(2))
        assertEquals(1, result.size)

    }


    @Test
    fun test_s_of_n_creator_distribution() {
        val n = 3
        val repetitions = 100_000
        val bin = IntArray(10)

        repeat(repetitions) {
            val s_of_n = s_of_n_creator<Int>(n)
            for (d in 0..8) s_of_n(d)
            for (s in s_of_n(9)) bin[s]++
        }


        // Check distribution.  Loose bounds are used because it's a random test, but it should still highlight significant errors.
        val expectedFrequency = repetitions * n.toDouble() / 10
        for (i in 0..9) {
            assertTrue(bin[i] > expectedFrequency * 0.8 , "Value at index $i is significantly lower than expected.")
            assertTrue(bin[i] < expectedFrequency * 1.2,  "Value at index $i is significantly higher than expected.")

        }
    }
}


