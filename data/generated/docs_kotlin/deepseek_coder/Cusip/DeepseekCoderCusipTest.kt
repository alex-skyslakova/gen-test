import org.junit.Test
import kotlin.test.assertEquals

class CusipTest {

    @Test
    fun testValidCusip() {
        val validCusips = listOf(
            "037833100",
            "17275R102",
            "38259P508",
            "594918104",
            "68389X105"
        )
        for (cusip in validCusips) {
            assertEquals(true, isCusip(cusip), "Expected $cusip to be valid")
        }
    }

    @Test
    fun testInvalidCusip() {
        val invalidCusips = listOf(
            "68389X106",
            "123456789",
            "12345678",
            "1234567890"
        )
        for (cusip in invalidCusips) {
            assertEquals(false, isCusip(cusip), "Expected $cusip to be invalid")
        }
    }

    @Test
    fun testInvalidCharacters() {
        val invalidCharactersCusips = listOf(
            "03783310!",
            "17275R10@",
            "38259P50#",
            "59491810*",
            "68389X10a"
        )
        for (cusip in invalidCharactersCusips) {
            assertEquals(false, isCusip(cusip), "Expected $cusip to be invalid due to invalid characters")
        }
    }

    @Test
    fun testShortCusip() {
        val shortCusips = listOf(
            "03783310",
            "17275R10",
            "38259P50"
        )
        for (cusip in shortCusips) {
            assertEquals(false, isCusip(cusip), "Expected $cusip to be invalid due to being too short")
        }
    }

    @Test
    fun testLongCusip() {
        val longCusips = listOf(
            "0378331000",
            "17275R1022",
            "38259P5088"
        )
        for (cusip in longCusips) {
            assertEquals(false, isCusip(cusip), "Expected $cusip to be invalid due to being too long")
        }
    }

    @Test
    fun testMixedCaseCusip() {
        val mixedCaseCusips = listOf(
            "037833100",
            "17275r102",
            "38259p508",
            "594918104",
            "68389x105"
        )
        for (cusip in mixedCaseCusips) {
            assertEquals(false, isCusip(cusip), "Expected $cusip to be invalid due to mixed case")
        }
    }
}
