import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.lang.StringBuilder

class LastLetterFirstLetterTest {

    private val names = arrayOf(
        "audino", "bagon", "baltoy", "banette", "bidoof",
        "braviary", "bronzor", "carracosta", "charmeleon", "cresselia",
        "croagunk", "darmanitan", "deino", "emboar", "emolga",
        "exeggcute", "gabite", "girafarig", "gulpin", "haxorus",
        "heatmor", "heatran", "ivysaur", "jellicent", "jumpluff",
        "kangaskhan", "kricketune", "landorus", "ledyba", "loudred",
        "lumineon", "lunatone", "machamp", "magnezone", "mamoswine",
        "nosepass", "petilil", "pidgeotto", "pikachu", "pinsir",
        "poliwrath", "poochyena", "porygon2", "porygonz", "registeel",
        "relicanth", "remoraid", "rufflet", "sableye", "scolipede",
        "scrafty", "seaking", "sealeo", "silcoon", "simisear",
        "snivy", "snorlax", "spoink", "starly", "tirtouga",
        "trapinch", "treecko", "tyrogue", "vigoroth", "vulpix",
        "wailord", "wartortle", "whismur", "wingull", "yamask"
    )

    private var maxPathLength = 0
    private var maxPathLengthCount = 0
    private val maxPathExample = StringBuilder(500)

    private fun search(part: Array<String>, offset: Int) {
        if (offset > maxPathLength) {
            maxPathLength = offset
            maxPathLengthCount = 1
        } else if (offset == maxPathLength) {
            maxPathLengthCount++
            maxPathExample.setLength(0)
            for (i in 0 until offset) {
                maxPathExample.append(if (i % 5 == 0) "\n  " else " ")
                maxPathExample.append(part[i])
            }
        }
        val lastChar = part[offset - 1].last()
        for (i in offset until part.size) {
            if (part[i][0] == lastChar) {
                val tmp = names[offset]
                names[offset] = names[i]
                names[i] = tmp
                search(names, offset + 1)
                names[i] = names[offset]
                names[offset] = tmp
            }
        }
    }


    @Test
    fun testPokemonChain() {
        // Reset global variables before each test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.clear()

        for (i in 0 until names.size) {
            val tmp = names[0]
            names[0] = names[i]
            names[i] = tmp
            search(names, 1)
            names[i] = names[0]
            names[0] = tmp
        }

        assertEquals(10, maxPathLength)
        assertTrue(maxPathLengthCount > 0 ) // Checking if there's at least one path
        assertTrue(maxPathExample.isNotEmpty()) // Example path should exist.
    }


    @Test
    fun testSmallSubset() {
        // Reset global variables before each test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.clear()

        val subset = arrayOf("dog", "giraffe", "elephant", "tiger", "rat")
       
        for (i in 0 until subset.size) {
            val tmp = subset[0]
            subset[0] = subset[i]
            subset[i] = tmp
            search(subset, 1)
            subset[i] = subset[0]
            subset[0] = tmp
        }
        assertEquals(5, maxPathLength)
    }


    @Test
    fun testNoValidChain() {
        // Reset global variables before each test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.clear()

        val noChain = arrayOf("abc", "def", "ghi")
        for (i in 0 until noChain.size) {
            val tmp = noChain[0]
            noChain[0] = noChain[i]
            noChain[i] = tmp
            search(noChain, 1)
            noChain[i] = noChain[0]
            noChain[0] = tmp
        }

        assertEquals(1, maxPathLength) // Should only be able to use one word as no chains exist.


    }

}

