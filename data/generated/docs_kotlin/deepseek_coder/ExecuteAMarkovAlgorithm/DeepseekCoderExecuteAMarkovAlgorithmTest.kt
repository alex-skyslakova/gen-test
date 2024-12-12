import org.junit.Test
import org.junit.Assert.*
import java.io.File
import java.util.regex.Pattern

class MarkovAlgorithmTest {

    private fun readRules(path: String): List<List<String>> {
        val ls = System.lineSeparator()
        return File(path).readText().split("$ls$ls").map { it.split(ls) }
    }

    private fun readTests(path: String) = File(path).readLines()

    private fun executeMarkovAlgorithm(rules: List<List<String>>, tests: List<String>): List<String> {
        val pattern = Pattern.compile("^([^#]*?)\\s+->\\s+(\\.?)(.*)")
        val results = mutableListOf<String>()

        for ((i, origTest) in tests.withIndex()) {
            val captures = mutableListOf<List<String>>()
            for (rule in rules[i]) {
                val m = pattern.matcher(rule)
                if (m.find()) {
                    val groups = List<String>(m.groupCount()) { m.group(it + 1) }
                    captures.add(groups)
                }
            }
            var test = origTest

            do {
                val copy = test
                var redo = false
                for (c in captures) {
                    test = test.replace(c[0], c[2])
                    if (c[1] == ".") break
                    if (test != copy) { redo = true; break }
                }
            } while (redo)

            results.add(test)
        }

        return results
    }

    @Test
    fun testRuleset1() {
        val rules = readRules("markov_rules.txt")
        val tests = readTests("markov_tests.txt")
        val results = executeMarkovAlgorithm(rules, tests)

        assertEquals("I bought a bag of apples from my brother.", results[0])
    }

    @Test
    fun testRuleset2() {
        val rules = readRules("markov_rules.txt")
        val tests = readTests("markov_tests.txt")
        val results = executeMarkovAlgorithm(rules, tests)

        assertEquals("I bought a bag of apples from T shop.", results[1])
    }

    @Test
    fun testRuleset3() {
        val rules = readRules("markov_rules.txt")
        val tests = readTests("markov_tests.txt")
        val results = executeMarkovAlgorithm(rules, tests)

        assertEquals("I bought a bag of apples with my money from T shop.", results[2])
    }

    @Test
    fun testRuleset4() {
        val rules = readRules("markov_rules.txt")
        val tests = readTests("markov_tests.txt")
        val results = executeMarkovAlgorithm(rules, tests)

        assertEquals("11111111111111111111", results[3])
    }

    @Test
    fun testRuleset5() {
        val rules = readRules("markov_rules.txt")
        val tests = readTests("markov_tests.txt")
        val results = executeMarkovAlgorithm(rules, tests)

        assertEquals("00011H1111000", results[4])
    }
}
