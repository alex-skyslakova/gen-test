import kotlin.test.Test
import kotlin.test.assertEquals

class AngleTest {

    @Test
    fun testAngleDifference() {
        // Test cases with expected results
        val testCases = listOf(
            Pair(20.0, 45.0) to 25.0,
            Pair(-45.0, 45.0) to 90.0,
            Pair(-85.0, 90.0) to 175.0,
            Pair(-95.0, 90.0) to 175.0,
            Pair(-45.0, 125.0) to 170.0,
            Pair(-45.0, 145.0) to 190.0,
            Pair(29.4803, -88.6381) to -118.1184,
            Pair(-78.3251, -159.036) to -80.7109,
            Pair(-70099.74233810938, 29840.67437876723) to 179.9327,
            Pair(-165313.6666297357, 33693.9894517456) to 179.6761,
            Pair(1174.8380510598456, -154146.66490124757) to -179.5029,
            Pair(60175.77306795546, 42213.07192354373) to -179.2980
        )

        for ((input, expected) in testCases) {
            val (b1, b2) = input
            val angle1 = Angle(b1)
            val angle2 = Angle(b2)
            val result = angle2 - angle1
            assertEquals(expected, result.value, 0.0001, "Failed for input: $input")
        }
    }
}
