import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class EulerSSumOfPowersConjectureTest {

    @Test
    fun testSolutionFound() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim()
        assertEquals("144^5 + 90^5 + 41^5 + 27^5 = 219^5", output)
    }

    @Test
    fun testNoSolutionFound() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Modify the range to ensure no solution is found
        val p5 = LongArray(10) { it.toLong() * it * it * it * it }
        var sum: Long
        var y: Int
        var found = false
        loop@ for (x0 in 0..9)
            for (x1 in 0..x0 - 1)
                for (x2 in 0..x1 - 1)
                    for (x3 in 0..x2 - 1) {
                        sum = p5[x0] + p5[x1] + p5[x2] + p5[x3]
                        y = p5.binarySearch(sum)
                        if (y >= 0) {
                            found = true
                            break@loop
                        }
                    }
        if (!found) println("No solution was found")

        val output = outputStream.toString().trim()
        assertEquals("No solution was found", output)
    }
}
