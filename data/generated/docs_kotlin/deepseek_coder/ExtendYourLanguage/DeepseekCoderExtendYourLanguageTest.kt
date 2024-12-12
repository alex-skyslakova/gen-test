import org.junit.Test
import kotlin.test.assertEquals

class IfBothTest {

    @Test
    fun testBothConditionsTrue() {
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
    fun testFirstConditionTrue() {
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
    fun testSecondConditionTrue() {
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
    fun testNoConditionTrue() {
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
    fun testOmitElseClauses() {
        var result = ""
        ifBoth(true, false) {
            result = "both"
        }
        assertEquals("", result)
    }

    @Test
    fun testCallElseClausesOutOfOrder() {
        var result = ""
        ifBoth(false, false) {
            result = "both"
        }.elseNeither {
            result = "neither"
        }.elseFirst {
            result = "first"
        }.elseSecond {
            result = "second"
        }
        assertEquals("neither", result)
    }

    @Test
    fun testPartialElseClauses() {
        var result = ""
        ifBoth(true, false) {
            result = "both"
        }.elseFirst {
            result = "first"
        }
        assertEquals("first", result)
    }

    @Test
    fun testAllFalseWithPartialElseClauses() {
        var result = ""
        ifBoth(false, false) {
            result = "both"
        }.elseNeither {
            result = "neither"
        }
        assertEquals("neither", result)
    }
}
