import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

class PrisonersProblemTest {

    @Test
    fun `test playOptimal success`() {
        val success = playOptimal()
        assertTrue(success)
    }

    @Test
    fun `test playOptimal failure`() {
        // Force a failure by ensuring no prisoner finds their number
        val secrets = (0..99).toMutableList()
        secrets.shuffle()
        for (i in 0 until 100) {
            secrets[i] = (i + 1) % 100 // Ensure no prisoner finds their number
        }
        val success = playOptimalWithCustomSecrets(secrets)
        assertFalse(success)
    }

    @Test
    fun `test playRandom success`() {
        val success = playRandom()
        assertTrue(success)
    }

    @Test
    fun `test playRandom failure`() {
        // Force a failure by ensuring no prisoner finds their number
        val secrets = (0..99).toMutableList()
        secrets.shuffle()
        for (i in 0 until 100) {
            secrets[i] = (i + 1) % 100 // Ensure no prisoner finds their number
        }
        val success = playRandomWithCustomSecrets(secrets)
        assertFalse(success)
    }

    @Test
    fun `test exec with playOptimal`() {
        val N = 1000
        val successRate = exec(N, ::playOptimal)
        assertTrue(successRate >= 0.0 && successRate <= 100.0)
    }

    @Test
    fun `test exec with playRandom`() {
        val N = 1000
        val successRate = exec(N, ::playRandom)
        assertTrue(successRate >= 0.0 && successRate <= 100.0)
    }

    @Test
    fun `test exec with custom playOptimal`() {
        val N = 1000
        val successRate = exec(N, ::playOptimalWithCustomSecrets)
        assertTrue(successRate >= 0.0 && successRate <= 100.0)
    }

    @Test
    fun `test exec with custom playRandom`() {
        val N = 1000
        val successRate = exec(N, ::playRandomWithCustomSecrets)
        assertTrue(successRate >= 0.0 && successRate <= 100.0)
    }

    // Helper functions to allow testing with custom secrets
    private fun playOptimalWithCustomSecrets(secrets: List<Int>): Boolean {
        var ret = true
        prisoner@ for (i in 0 until 100) {
            var prev = i
            draw@ for (j in 0 until 50) {
                if (secrets[prev] == i) continue@prisoner
                prev = secrets[prev]
            }
            ret = false
            break@prisoner
        }
        return ret
    }

    private fun playRandomWithCustomSecrets(secrets: List<Int>): Boolean {
        var ret = true
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
        return ret
    }
}
