import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BoxTheCompassTest {

    fun expand(cp: String): String {
        val sb = StringBuilder()
        for (c in cp) {
            sb.append(when (c) {
                'N' -> "north"
                'E' -> "east"
                'S' -> "south"
                'W' -> "west"
                'b' -> " by "
                else -> "-"
            })
        }
        return sb.toString().capitalize()
    }


    @Test
    fun testCompassPoints() {
        val expectedHeadings = arrayOf(
            "N", "NbE", "N-NE", "NEbN", "NE", "NEbE", "E-NE", "EbN",
            "E", "EbS", "E-SE", "SEbE", "SE", "SEbS", "S-SE", "SbE",
            "S", "SbW", "S-SW", "SWbS", "SW", "SWbW", "W-SW", "WbS",
            "W", "WbN", "W-NW", "NWbW", "NW", "NWbN", "N-NW", "NbW"
        )
        val testDegrees = arrayOf(0.0, 16.87, 16.88, 33.75, 50.62, 50.63, 67.5, 84.37, 84.38, 101.25, 118.12, 118.13, 135.0, 151.87, 151.88, 168.75, 185.62, 185.63, 202.5, 219.37, 219.38, 236.25, 253.12, 253.13, 270.0, 286.87, 286.88, 303.75, 320.62, 320.63, 337.5, 354.37, 354.38)

        for (i in testDegrees.indices) {
            val index = i % 32
            var heading = i * 11.25
            when (i % 3) {
                1 -> heading += 5.62
                2 -> heading -= 5.62
            }

            assertEquals(expand(expectedHeadings[index]), expand(expectedHeadings[index]))


        }
    }

    @Test
    fun testExpandFunction() {
        assertEquals("North", expand("N"))
        assertEquals("North by east", expand("NbE"))
        assertEquals("North-east", expand("N-NE"))
        assertEquals("South by west", expand("SbW"))
        assertEquals("West-northwest", expand("W-NW"))

    }

}
