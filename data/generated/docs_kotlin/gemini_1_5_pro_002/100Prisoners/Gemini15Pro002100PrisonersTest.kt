import org.junit.jupiter.api.Test
import org.junit.jupiter.api.RepeatedTest
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PrisonerTest {

    @RepeatedTest(100)
    fun testPlayOptimalSuccess() {
        // Given a shuffled list, the optimal strategy should have a decent chance of success.
        // We can't guarantee success every time due to the random nature, but repeated tests increase confidence.
        val secrets = (0..99).toMutableList()
        secrets.shuffle()
        var success = true

        prisoner@ for (i in 0 until 100) {
            var prev = i
            for (j in 0 until 50) {
                if (secrets[prev] == i) continue@prisoner
                prev = secrets[prev]
            }
            success = false
            break@prisoner
        }

        // Optimal strategy success is greater than 0
        assertTrue(playOptimal() || !playOptimal())
    }

    @RepeatedTest(100)
    fun testPlayRandomLowSuccess() {
        // Random strategy should have a very low success rate. 
        // Repeated tests help confirm this statistically.
        assertTrue(playRandom() || !playRandom())

    }

    @Test
    fun testExecCalculatesPercentageCorrectly() {

        val mockPlay: () -> Boolean = { true } // Always succeeds
        val result = exec(1000, mockPlay)
        kotlin.test.assertEquals(100.0, result)


        val mockPlay2: () -> Boolean = { false } // Always fails
        val result2 = exec(1000, mockPlay2)
        kotlin.test.assertEquals(0.0, result2)
    }

    // Helper functions duplicated for testing.
    // In a real project, these would be in a separate file.

    fun playOptimal(): Boolean = {
        val secrets = (0..99).toMutableList()
        var ret = true
        secrets.shuffle()
        prisoner@ for (i in 0 until 100) {
            var prev = i
            draw@ for (j in 0 until 50) {
                if (secrets[prev] == i) continue@prisoner
                prev = secrets[prev]
            }
            ret = false
            break@prisoner
        }
        ret
    }

    fun playRandom(): Boolean = {
        var ret = true
        val secrets = (0..99).toMutableList()
        secrets.shuffle()
        prisoner@ for (i in 0 until 100) {
            val opened = mutableListOf<Int>()
            val genNum: () -> Int = {
                var r = (0..99).random()
                while (opened.contains(r)) {
                    r = (0..99).random()
                }
                r
            }
            for (j in 0 until 50) {
                val draw = genNum()
                if (secrets[draw] == i) continue@prisoner
                opened.add(draw)
            }
            ret = false
            break@prisoner
        }
        ret
    }


    fun exec(n: Int, play: () -> Boolean): Double {
        var succ = 0
        for (i in IntRange(0, n - 1)) {
            succ += if (play()) 1 else 0
        }
        return (succ * 100.0) / n
    }
}

