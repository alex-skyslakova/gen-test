import org.junit.Test
import kotlin.test.assertEquals

class AngleDifferenceBetweenTwoBearingsTest {

    @Test
    fun testAngleDifference() {
        val testCases = listOf(
            Triple(20.0, 45.0, 25.0),
            Triple(-45.0, 45.0, 90.0),
            Triple(-85.0, 90.0, 175.0),
            Triple(-95.0, 90.0, 165.0),
            Triple(-45.0, 125.0, 170.0),
            Triple(-45.0, 145.0, 190.0),
            Triple(29.4803, -88.6381, -118.1184),
            Triple(-78.3251, -159.036, -80.7109),
            Triple(-70099.74233810938, 29840.67437876723, -139.5833),
            Triple(-165313.6666297357, 33693.9894517456, -72.3439),
            Triple(1174.8380510598456, -154146.66490124757, 23.4023),
            Triple(60175.77306795546, 42213.07192354373, 317.0894)
        )

        for (testCase in testCases) {
            val (b1, b2, expected) = testCase
            val diff = Angle(b2) - Angle(b1)
            assertEquals(expected, diff.value, 0.0001, "Difference between $b1 and $b2 should be $expected")
        }
    }
}
