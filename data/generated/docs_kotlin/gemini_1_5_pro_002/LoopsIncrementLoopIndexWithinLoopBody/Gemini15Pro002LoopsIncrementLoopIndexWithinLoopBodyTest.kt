import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class LoopsIncrementLoopIndexWithinLoopBodyTest {

    private fun isPrime(n: Long): Boolean {
        if (n % 2L == 0L) return n == 2L
        if (n % 3L == 0L) return n == 3L
        var d = 5L
        while (d * d <= n) {
            if (n % d == 0L) return false
            d += 2L
            if (n % d == 0L) return false
            d += 4L
        }
        return true
    }


    @Test
    fun testMain() {
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        val oldOut = System.out
        System.setOut(ps)

        main(arrayOf())

        System.out.flush()
        System.setOut(oldOut)

        val expectedOutput = """
n = 1                  42
n = 2                  85
n = 3                 171
n = 4                 343
n = 5                 687
n = 6                1,375
n = 7                2,751
n = 8                5,503
n = 9               11,007
n = 10               22,015
n = 11               44,031
n = 12               88,063
n = 13              176,127
n = 14              352,255
n = 15              704,511
n = 16            1,409,023
n = 17            2,818,047
n = 18            5,636,095
n = 19           11,272,191
n = 20           22,544,383
n = 21           45,088,767
n = 22           90,177,535
n = 23          180,355,071
n = 24          360,710,143
n = 25          721,420,287
n = 26        1,442,840,575
n = 27        2,885,681,151
n = 28        5,771,362,303
n = 29       11,542,724,607
n = 30       23,085,449,215
n = 31       46,170,898,431
n = 32       92,341,796,863
n = 33      184,683,593,727
n = 34      369,367,187,455
n = 35      738,734,374,911
n = 36    1,477,468,749,823
n = 37    2,954,937,499,647
n = 38    5,909,874,999,295
n = 39   11,819,749,998,591
n = 40   23,639,499,997,183
n = 41   47,278,999,994,367
n = 42   94,557,999,988,735
        """.trimIndent()


        assertEquals(expectedOutput, baos.toString().trimIndent())
    }

}
