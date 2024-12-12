import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CompassTest {

    private fun getCompassPoint(degree: Double): String {
        val cp = arrayOf(
            "N", "NbE", "N-NE", "NEbN", "NE", "NEbE", "E-NE", "EbN",
            "E", "EbS", "E-SE", "SEbE", "SE", "SEbS", "S-SE", "SbE",
            "S", "SbW", "S-SW", "SWbS", "SW", "SWbW", "W-SW", "WbS",
            "W", "WbN", "W-NW", "NWbW", "NW", "NWbN", "N-NW", "NbW"
        )
        val index = ((degree + 5.62) / 11.25).toInt() % 32
        return cp[index]
    }

    @Test
    fun testCompassPoints() {
        val testCases = mapOf(
            0.0 to "N",
            16.87 to "NbE",
            16.88 to "N-NE",
            33.75 to "NEbN",
            50.62 to "NE",
            50.63 to "NEbE",
            67.5 to "E-NE",
            84.37 to "EbN",
            84.38 to "E",
            101.25 to "EbS",
            118.12 to "E-SE",
            118.13 to "SEbE",
            135.0 to "SE",
            151.87 to "SEbS",
            151.88 to "S-SE",
            168.75 to "SbE",
            185.62 to "S",
            185.63 to "SbW",
            202.5 to "S-SW",
            219.37 to "SWbS",
            219.38 to "SW",
            236.25 to "SWbW",
            253.12 to "W-SW",
            253.13 to "WbS",
            270.0 to "W",
            286.87 to "WbN",
            286.88 to "W-NW",
            303.75 to "NWbW",
            320.62 to "NW",
            320.63 to "NWbN",
            337.5 to "N-NW",
            354.37 to "NbW",
            354.38 to "N"
        )

        for ((degree, expectedCompassPoint) in testCases) {
            assertEquals(expectedCompassPoint, getCompassPoint(degree), "Failed for degree: $degree")
        }
    }
}
