import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AngleTest {

    @Test
    fun testAngleDifference() {
        val anglePairs = arrayOf(
            20.0 to 45.0,
            -45.0 to 45.0,
            -85.0 to 90.0,
            -95.0 to 90.0,
            -45.0 to 125.0,
            -45.0 to 145.0,
            29.4803 to -88.6381,
            -78.3251 to -159.036,
            -70099.74233810938 to 29840.67437876723,
            -165313.6666297357 to 33693.9894517456,
            1174.8380510598456 to -154146.66490124757,
            60175.77306795546 to 42213.07192354373
        )

        val expectedDiffs = arrayOf(
            25.0,
            90.0,
            175.0,
            -175.0,
            170.0,
            -180.0,
            -118.1184,
            80.7109,
            -140.5845,
            29.6487,
            22.9443,
            -179.6271
        )

        for (i in anglePairs.indices) {
            val diff = Angle(anglePairs[i].second) - Angle(anglePairs[i].first)
            assertEquals(expectedDiffs[i], diff.value, 0.0001)
        }
    }


    @Test
    fun testAngleNormalization(){
        assertEquals(170.0, Angle(530.0).value, 0.0001)
        assertEquals(-170.0, Angle(-530.0).value, 0.0001)
        assertEquals(0.0, Angle(720.0).value, 0.0001)
        assertEquals(20.0, Angle(20.0).value, 0.0001)


    }

}
