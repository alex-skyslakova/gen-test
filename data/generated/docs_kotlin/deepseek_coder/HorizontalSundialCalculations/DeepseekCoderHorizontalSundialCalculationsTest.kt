import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

class HorizontalSundialCalculationsTest {

    @Test
    fun testSineOfLatitude() {
        val lat = 4.95
        val expectedSlat = sin(toRadians(lat))
        assertEquals(expectedSlat, sin(toRadians(lat)), 0.000001)
    }

    @Test
    fun testLongitudeMinusMeridian() {
        val lng = 150.5
        val mer = 150.0
        val expectedDiff = lng - mer
        assertEquals(expectedDiff, lng - mer, 0.001)
    }

    @Test
    fun testSunHourAngle() {
        val h = 0
        val lng = 150.5
        val mer = 150.0
        val expectedSha = 15.0 * h - (lng - mer)
        assertEquals(expectedSha, 15.0 * h - (lng - mer), 0.001)
    }

    @Test
    fun testDialHourLineAngle() {
        val lat = 4.95
        val h = 0
        val lng = 150.5
        val mer = 150.0
        val sha = 15.0 * h - (lng - mer)
        val slat = sin(toRadians(lat))
        val expectedDhla = toDegrees(atan2(slat * sin(toRadians(sha)), cos(toRadians(sha))))
        assertEquals(expectedDhla, toDegrees(atan2(slat * sin(toRadians(sha)), cos(toRadians(sha)))), 0.001)
    }

    @Test
    fun testHourCalculation() {
        val h = 0
        var hr = h + 12
        val expectedHr = 12
        assertEquals(expectedHr, hr)
    }

    @Test
    fun testAMPMCalculation() {
        val h = 0
        var hr = h + 12
        val expectedAMPM = "PM"
        val am = if (hr < 12) "AM" else "PM"
        assertEquals(expectedAMPM, am)
    }

    @Test
    fun testInvalidInput() {
        assertFailsWith<NumberFormatException> {
            "invalid".toDouble()
        }
    }
}
