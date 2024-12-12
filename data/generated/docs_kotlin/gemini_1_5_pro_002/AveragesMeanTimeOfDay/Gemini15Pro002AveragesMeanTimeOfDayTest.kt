import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MeanTimeOfDayTest {

    @Test
    fun testMeanAngle() {
        val angles1 = doubleArrayOf(0.0, 90.0, 180.0)
        assertEquals(90.0, meanAngle(angles1), 0.0001)

        val angles2 = doubleArrayOf(0.0, 0.0, 90.0)
        assertEquals(26.565, meanAngle(angles2), 0.001)


        val angles3 = doubleArrayOf(350.0, 10.0) // Test wrapping around 360
        assertEquals(0.0, meanAngle(angles3), 0.0001)

         val angles4 = doubleArrayOf(170.0, 190.0) // Test average near 180
        assertEquals(180.0, meanAngle(angles4), 0.0001)
    }


    @Test
    fun testTimeToSecs() {
        assertEquals(82817, timeToSecs("23:00:17"))
        assertEquals(84020, timeToSecs("23:40:20"))
        assertEquals(765, timeToSecs("00:12:45"))
        assertEquals(1039, timeToSecs("00:17:19"))
        assertEquals(43200, timeToSecs("12:00:00")) // midday
        assertEquals(0, timeToSecs("00:00:00")) // midnight
    }

    @Test
    fun testTimeToDegrees() {
        assertEquals(345.07083333, timeToDegrees("23:00:17"), 0.0001)
        assertEquals(350.08333333, timeToDegrees("23:40:20"), 0.0001)
        assertEquals(3.1875, timeToDegrees("00:12:45"), 0.0001)
        assertEquals(4.329166666, timeToDegrees("00:17:19"), 0.0001)
    }

    @Test
    fun testDegreesToTime() {
        assertEquals("23:00:17", degreesToTime(345.07083333))
        assertEquals("23:40:20", degreesToTime(350.08333333))
        assertEquals("00:12:45", degreesToTime(3.1875))
        assertEquals("00:17:19", degreesToTime(4.329166666))
        assertEquals("12:00:00", degreesToTime(180.0))
        assertEquals("00:00:00", degreesToTime(0.0))
        assertEquals("00:00:00", degreesToTime(360.0))
        assertEquals("23:59:59", degreesToTime(359.99583333)) // Test near midnight
        assertEquals("00:00:00", degreesToTime(-0.00416667)) // Test negative wrapping
    }


    @Test
    fun testExampleCase() {
        val tm = arrayOf("23:00:17", "23:40:20", "00:12:45", "00:17:19")
        val angles = DoubleArray(4) { timeToDegrees(tm[it]) }
        val mean = meanAngle(angles)
        assertEquals("23:52:40", degreesToTime(mean))
    }


}
