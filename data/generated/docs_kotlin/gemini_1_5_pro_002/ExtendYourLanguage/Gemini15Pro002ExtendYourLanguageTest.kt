import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IfBothTest {

    @Test
    fun testBothTrue() {
        var result = ""
        ifBoth(true, true) {
            result = "both"
        }.elseFirst {
            result = "first"
        }.elseSecond {
            result = "second"
        }.elseNeither {
            result = "neither"
        }
        assertEquals("both", result)
    }

    @Test
    fun testFirstTrue() {
        var result = ""
        ifBoth(true, false) {
            result = "both"
        }.elseFirst {
            result = "first"
        }.elseSecond {
            result = "second"
        }.elseNeither {
            result = "neither"
        }
        assertEquals("first", result)
    }

    @Test
    fun testSecondTrue() {
        var result = ""
        ifBoth(false, true) {
            result = "both"
        }.elseFirst {
            result = "first"
        }.elseSecond {
            result = "second"
        }.elseNeither {
            result = "neither"
        }
        assertEquals("second", result)
    }

    @Test
    fun testNeitherTrue() {
        var result = ""
        ifBoth(false, false) {
            result = "both"
        }.elseFirst {
            result = "first"
        }.elseSecond {
            result = "second"
        }.elseNeither {
            result = "neither"
        }
        assertEquals("neither", result)
    }

    @Test
    fun testOutOfOrder() {
        var result = ""
        ifBoth(true, false) {
            result = "both"
        }.elseNeither {
            result = "neither"
        }.elseFirst {
            result = "first"
        }.elseSecond {
            result = "second"
        }

        assertEquals("first", result)
    }

    @Test
    fun testOmittingClauses() {
        var result = ""
        ifBoth(true, true) {
            result = "both"
        }.elseSecond{
            result = "second"
        }
        assertEquals("both", result)
    }



}


