import org.junit.Test
import kotlin.test.assertEquals

class MeanTimeOfDayTest {

    @Test
    fun testTimeToSecs() {
        assertEquals(82817, timeToSecs("23:00:17"))
        assertEquals(85220, timeToSecs("23:40:20"))
        assertEquals(765, timeToSecs("00:12:45"))
        assertEquals(1039, timeToSecs("00:17:19"))
    }

    @Test
    fun testTimeToDegrees() {
        assertEquals(345.0708333333333, timeToDegrees("23:00:17"), 0.0000000001)
        assertEquals(355.0833333333333, timeToDegrees("23:40:20"), 0.0000000001)
        assertEquals(3.1875, timeToDegrees("00:12:45"), 0.0000000001)
        assertEquals(4.329166666666667, timeToDegrees("00:17:19"), 0.0000000001)
    }

    @Test
    fun testDegreesToTime() {
        assertEquals("23:00:17", degreesToTime(345.0708333333333))
        assertEquals("23:40:20", degreesToTime(355.0833333333333))
        assertEquals("00:12:45", degreesToTime(3.1875))
        assertEquals("00:17:19", degreesToTime(4.329166666666667))
    }

    @Test
    fun testMeanAngle() {
        val angles = doubleArrayOf(345.0708333333333, 355.0833333333333, 3.1875, 4.329166666666667)
        val mean = meanAngle(angles)
        assertEquals(359.99916666666664, mean, 0.0000000001)
    }

    @Test
    fun testMain() {
        val tm = arrayOf("23:00:17", "23:40:20", "00:12:45", "00:17:19")
        val angles = DoubleArray(4) { timeToDegrees(tm[it]) }
        val mean = meanAngle(angles)
        assertEquals("23:59:59", degreesToTime(mean))
    }
}
