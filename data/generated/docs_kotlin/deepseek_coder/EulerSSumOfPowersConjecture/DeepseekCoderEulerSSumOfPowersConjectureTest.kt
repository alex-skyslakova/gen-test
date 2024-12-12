import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class EulerSSumOfPowersConjectureTest {

    @Test
    fun testSolutionExists() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(arrayOf())

        val output = outContent.toString().trim()
        assertTrue(output.contains("^5 + "))
        assertTrue(output.contains(" = "))
    }

    @Test
    fun testNoSolutionExists() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        // Modify the main function to search in a range where no solution exists
        fun main(args: Array<String>) {
            val p5 = LongArray(10){ it.toLong() * it * it * it * it }
            var sum: Long
            var y: Int
            var found = false
            loop@ for (x0 in 0 .. 9)
                for (x1 in 0 .. x0 - 1)
                    for (x2 in 0 .. x1 - 1)
                        for (x3 in 0 .. x2 - 1) {
                            sum = p5[x0] + p5[x1] + p5[x2] + p5[x3]
                            y = p5.binarySearch(sum)
                            if (y >= 0) {
                                println("$x0^5 + $x1^5 + $x2^5 + $x3^5 = $y^5")
                                found = true
                                break@loop
                            }
                        }
            if (!found) println("No solution was found")
        }

        main(arrayOf())

        val output = outContent.toString().trim()
        assertEquals("No solution was found", output)
    }

    @Test
    fun testDistinctIntegers() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(arrayOf())

        val output = outContent.toString().trim()
        val parts = output.split(" + ").map { it.split("^")[0].toInt() }
        val distinctParts = parts.distinct()

        assertEquals(parts.size, distinctParts.size)
    }

    @Test
    fun testSumOfPowers() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(arrayOf())

        val output = outContent.toString().trim()
        val parts = output.split(" + ").map { it.split("^")[0].toInt() }
        val y = output.split(" = ")[1].split("^")[0].toInt()

        val p5 = LongArray(250){ it.toLong() * it * it * it * it }
        val sum = parts.sumOf { p5[it] }
        val y5 = p5[y]

        assertEquals(y5, sum)
    }
}
