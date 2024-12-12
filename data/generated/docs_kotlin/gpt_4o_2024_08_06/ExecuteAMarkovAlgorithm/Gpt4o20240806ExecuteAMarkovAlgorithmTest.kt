import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MarkovAlgorithmTest {

    @Test
    fun testRuleset1() {
        val rules = listOf(
            "A -> apple",
            "B -> bag",
            "S -> shop",
            "T -> the",
            "the shop -> my brother",
            "a never used -> .terminating rule"
        )
        val input = "I bought a B of As from T S."
        val expectedOutput = "I bought a bag of apples from my brother."
        assertEquals(expectedOutput, executeMarkovAlgorithm(rules, input))
    }

    @Test
    fun testRuleset2() {
        val rules = listOf(
            "A -> apple",
            "B -> bag",
            "S -> .shop",
            "T -> the",
            "the shop -> my brother",
            "a never used -> .terminating rule"
        )
        val input = "I bought a B of As from T S."
        val expectedOutput = "I bought a bag of apples from T shop."
        assertEquals(expectedOutput, executeMarkovAlgorithm(rules, input))
    }

    @Test
    fun testRuleset3() {
        val rules = listOf(
            "A -> apple",
            "WWWW -> with",
            "Bgage -> ->.*",
            "B -> bag",
            "->.* -> money",
            "W -> WW",
            "S -> .shop",
            "T -> the",
            "the shop -> my brother",
            "a never used -> .terminating rule"
        )
        val input = "I bought a B of As W my Bgage from T S."
        val expectedOutput = "I bought a bag of apples with my money from T shop."
        assertEquals(expectedOutput, executeMarkovAlgorithm(rules, input))
    }

    @Test
    fun testRuleset4() {
        val rules = listOf(
            "_+1 -> _1+",
            "1+1 -> 11+",
            "1! -> !1",
            ",! -> !+",
            "_! -> _",
            "1*1 -> x,@y",
            "1x -> xX",
            "X, -> 1,1",
            "X1 -> 1X",
            "_x -> _X",
            ",x -> ,X",
            "y1 -> 1y",
            "y_ -> _",
            "1@1 -> x,@y",
            "1@_ -> @_",
            ",@_ -> !_",
            "++ -> +",
            "_1 -> 1",
            "1+_ -> 1",
            "_+_ -> "
        )
        val input = "_1111*11111_"
        val expectedOutput = "11111111111111111111"
        assertEquals(expectedOutput, executeMarkovAlgorithm(rules, input))
    }

    @Test
    fun testRuleset5() {
        val rules = listOf(
            "A0 -> 1B",
            "0A1 -> C01",
            "1A1 -> C11",
            "0B0 -> A01",
            "1B0 -> A11",
            "B1 -> 1B",
            "0C0 -> B01",
            "1C0 -> B11",
            "0C1 -> H01",
            "1C1 -> H11"
        )
        val input = "000000A000000"
        val expectedOutput = "00011H1111000"
        assertEquals(expectedOutput, executeMarkovAlgorithm(rules, input))
    }
}
