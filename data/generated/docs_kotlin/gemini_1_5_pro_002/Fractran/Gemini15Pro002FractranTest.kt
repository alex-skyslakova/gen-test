import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class FractranTest {

    @Test
    fun testEmptyProgram() {
        assertEquals(emptyList(), fractran("", 2, 20, false))
    }

    @Test
    fun testSimpleProgramNoPrimes() {
        assertEquals(listOf(2, 3, 9, 27, 81), fractran("3/2", 2, 5, false))
    }

    @Test
    fun testSimpleProgramPrimes() {
        assertEquals(emptyList(), fractran("3/2", 2, 5, true))
    }

    @Test
    fun testConwayPrimeProgramFirst20NoPrimes() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val expected = listOf(2, 15, 825, 725, 1925, 2275, 425, 390, 330, 290, 770, 910, 230, 210, 550, 275, 1375, 165, 495, 585)
        assertEquals(expected, fractran(program, 2, 20, false))
    }


    @Test
    fun testConwayPrimeProgramFirst20Primes() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val expected = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71)
        assertEquals(expected, fractran(program, 2, 20, true))
    }

    @Test
    fun testLimitReachedEarlyNoPrimes() {
        val program = "3/2"
        assertEquals(listOf(2, 3), fractran(program, 2, 2, false))
    }


    @Test
    fun testLimitReachedEarlyPrimes() {
        val program = "2/1" // Generates powers of 2
        assertEquals(listOf(2, 3), fractran(program, 2, 2, true))
    }

    @Test
    fun testInvalidFractionFormat(){
        val program = "17a/91 78/85"
        assertEquals(emptyList(), fractran(program, 2, 20, false))
    }



    @Test
    fun testZeroDenominatorFraction(){
        val program = "17/0 78/85"
        assertEquals(emptyList(), fractran(program, 2, 20, false))
    }


}


