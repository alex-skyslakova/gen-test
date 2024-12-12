import kotlin.test.Test
import kotlin.test.assertEquals

class AveragesMeanTimeOfDayTest {

    @Test
    fun testTimeToSecs() {
        assertEquals(82817, timeToSecs("23:00:17"))
        assertEquals(85220, timeToSecs("23:40:20"))
        assertEquals(765, timeToSecs("00:12:45"))
        assertEquals(1039, timeToSecs("00:17:19"))
    }

    @Test
    fun testTimeToDegrees() {
        assertEquals(345.0708333333333, timeToDegrees("23:00:17"), 0.0001)
        assertEquals(355.0833333333333, timeToDegrees("23:40:20"), 0.0001)
        assertEquals(3.1875, timeToDegrees("00:12:45"), 0.0001)
        assertEquals(4.329166666666667, timeToDegrees("00:17:19"), 0.0001)
    }

    @Test
    fun testDegreesToTime() {
        assertEquals("23: 0:17", degreesToTime(345.0708333333333))
        assertEquals("23:40:20", degreesToTime(355.0833333333333))
        assertEquals(" 0:12:45", degreesToTime(3.1875))
        assertEquals(" 0:17:19", degreesToTime(4.329166666666667))
    }

    @Test
    fun testMeanAngle() {
        val angles = doubleArrayOf(345.0708333333333, 355.0833333333333, 3.1875, 4.329166666666667)
        val mean = meanAngle(angles)
        assertEquals(356.417, mean, 0.001)
    }

    @Test
    fun testAverageTime() {
        val tm = arrayOf("23:00:17", "23:40:20", "00:12:45", "00:17:19")
        val angles = DoubleArray(4) { timeToDegrees(tm[it]) }
        val mean = meanAngle(angles)
        val averageTime = degreesToTime(mean)
        assertEquals("23:44:30", averageTime)
    }
}
