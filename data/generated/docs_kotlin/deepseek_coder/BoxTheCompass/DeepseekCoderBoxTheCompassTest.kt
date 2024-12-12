import org.junit.Test
import kotlin.test.assertEquals

class CompassTest {

    @Test
    fun testExpandFunction() {
        val testCases = mapOf(
            "N" to "North",
            "NbE" to "North by east",
            "N-NE" to "North-northeast",
            "NEbN" to "Northeast by north",
            "NE" to "Northeast",
            "NEbE" to "Northeast by east",
            "E-NE" to "East-northeast",
            "EbN" to "East by north",
            "E" to "East",
            "EbS" to "East by south",
            "E-SE" to "East-southeast",
            "SEbE" to "Southeast by east",
            "SE" to "Southeast",
            "SEbS" to "Southeast by south",
            "S-SE" to "South-southeast",
            "SbE" to "South by east",
            "S" to "South",
            "SbW" to "South by west",
            "S-SW" to "South-southwest",
            "SWbS" to "Southwest by south",
            "SW" to "Southwest",
            "SWbW" to "Southwest by west",
            "W-SW" to "West-southwest",
            "WbS" to "West by south",
            "W" to "West",
            "WbN" to "West by north",
            "W-NW" to "West-northwest",
            "NWbW" to "Northwest by west",
            "NW" to "Northwest",
            "NWbN" to "Northwest by north",
            "N-NW" to "North-northwest",
            "NbW" to "North by west"
        )

        for ((input, expected) in testCases) {
            assertEquals(expected, expand(input))
        }
    }

    @Test
    fun testMainFunctionOutput() {
        val expectedOutput = """
            Index  Degrees  Compass point
            -----  -------  -------------
             1       0.00   North
             2      16.87   North by east
             3      16.88   North-northeast
             4      33.75   Northeast by north
             5      50.62   Northeast
             6      50.63   Northeast by east
             7      67.50   East-northeast
             8      84.37   East by north
             9      84.38   East
            10     101.25   East by south
            11     118.12   East-southeast
            12     118.13   Southeast by east
            13     135.00   Southeast
            14     151.87   Southeast by south
            15     151.88   South-southeast
            16     168.75   South by east
            17     185.62   South
            18     185.63   South by west
            19     202.50   South-southwest
            20     219.37   Southwest by south
            21     219.38   Southwest
            22     236.25   Southwest by west
            23     253.12   West-southwest
            24     253.13   West by south
            25     270.00   West
            26     286.87   West by north
            27     286.88   West-northwest
            28     303.75   Northwest by west
            29     320.62   Northwest
            30     320.63   Northwest by north
            31     337.50   North-northwest
            32     354.37   North by west
             1     354.38   North
        """.trimIndent()

        val actualOutput = captureOutput { main(arrayOf()) }
        assertEquals(expectedOutput, actualOutput.trim())
    }

    private fun captureOutput(block: () -> Unit): String {
        val out = System.out
        val output = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(output))
        try {
            block()
        } finally {
            System.setOut(out)
        }
        return output.toString().trim()
    }
}
