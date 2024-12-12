import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class MarkovAlgorithmTest {

    private fun executeMarkov(rules: List<String>, input: String): String {
        val pattern = Regex("^([^#]*?)\\s+->\\s+(\\.?)(.*)")

        val captures = mutableListOf<List<String>>()
        for (rule in rules) {
            val match = pattern.find(rule)
            if (match != null) {
                val groups = match.groupValues.drop(1)
                captures.add(groups)
            }
        }
        var text = input

        do {
            val copy = text
            var redo = false
            for (c in captures) {
                text = text.replaceFirst(c[0], c[2])
                if (c[1] == ".") break
                if (text != copy) {
                    redo = true; break
                }
            }
        } while (redo)

        return text
    }


    @Test
    fun testRuleset1() {
        val rules = """
            A -> apple
            B -> bag
            S -> shop
            T -> the
            the shop -> my brother
            a never used -> .terminating rule
        """.trimIndent().lines()
        val input = "I bought a B of As from T S."
        val expected = "I bought a bag of apples from my brother."
        assertEquals(expected, executeMarkov(rules, input))
    }

    @Test
    fun testRuleset2() {
        val rules = """
            A -> apple
            B -> bag
            S -> .shop
            T -> the
            the shop -> my brother
            a never used -> .terminating rule
        """.trimIndent().lines()
        val input = "I bought a B of As from T S."
        val expected = "I bought a bag of apples from T shop."
        assertEquals(expected, executeMarkov(rules, input))
    }

    @Test
    fun testRuleset3() {
        val rules = """
            A -> apple
            WWWW -> with
            Bgage -> ->.*
            B -> bag
            ->.* -> money
            W -> WW
            S -> .shop
            T -> the
            the shop -> my brother
            a never used -> .terminating rule
        """.trimIndent().lines()
        val input = "I bought a B of As W my Bgage from T S."
        val expected = "I bought a bag of apples with my money from T shop."
        assertEquals(expected, executeMarkov(rules, input))
    }

    @Test
    fun testRuleset4() {
        val rules = """
            _+1 -> _1+
            1+1 -> 11+
            1! -> !1
            ,! -> !+
            _! -> _
            1*1 -> x,@y
            1x -> xX
            X, -> 1,1
            X1 -> 1X
            _x -> _X
            ,x -> ,X
            y1 -> 1y
            y_ -> _
            1@1 -> x,@y
            1@_ -> @_
            ,@_ -> !_
            ++ -> +
            _1 -> 1
            1+_ -> 1
            _+_ -> 
        """.trimIndent().lines()
        val input = "_1111*11111_"
        val expected = "11111111111111111111"
        assertEquals(expected, executeMarkov(rules, input))

    }


    @Test
    fun testRuleset5() {
        val rules = """
            A0 -> 1B
            0A1 -> C01
            1A1 -> C11
            0B0 -> A01
            1B0 -> A11
            B1 -> 1B
            0C0 -> B01
            1C0 -> B11
            0C1 -> H01
            1C1 -> H11
        """.trimIndent().lines()

        val input = "000000A000000"
        val expected = "00011H1111000"
        assertEquals(expected, executeMarkov(rules, input))
    }
}
