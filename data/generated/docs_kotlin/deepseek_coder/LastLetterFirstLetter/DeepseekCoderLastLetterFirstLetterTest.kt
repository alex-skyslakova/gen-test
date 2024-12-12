import org.junit.Assert.*
import org.junit.Test

class LastLetterFirstLetterTest {

    @Test
    fun testSearch() {
        val names = arrayOf(
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

        // Reset global variables before each test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.setLength(0)

        // Test with a small subset of names
        val smallNames = arrayOf("audino", "bagon", "nosepass", "sableye")
        search(smallNames, 1)
        assertEquals(3, maxPathLength)
        assertEquals(1, maxPathLengthCount)
        assertTrue(maxPathExample.toString().contains("audino"))
        assertTrue(maxPathExample.toString().contains("nosepass"))
        assertTrue(maxPathExample.toString().contains("sableye"))

        // Test with the full list of names
        search(names, 1)
        assertTrue(maxPathLength > 0)
        assertTrue(maxPathLengthCount > 0)
        assertTrue(maxPathExample.toString().contains("audino"))
        assertTrue(maxPathExample.toString().contains("bagon"))
        assertTrue(maxPathExample.toString().contains("nosepass"))
        assertTrue(maxPathExample.toString().contains("sableye"))
    }

    @Test
    fun testMain() {
        // Capture the output of the main function
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        // Run the main function
        main(arrayOf())

        // Restore the original output stream
        System.setOut(System.out)

        // Verify the output
        val output = outputStream.toString()
        assertTrue(output.contains("Maximum path length"))
        assertTrue(output.contains("Paths of that length"))
        assertTrue(output.contains("Example path of that length"))
    }
}
