import org.junit.Test
import kotlin.test.assertEquals

class LinearCombinationTest {

    @Test
    fun testLinearCombo() {
        // Test case 1: [1, 2, 3]
        assertEquals("e(1) + 2*e(2) + 3*e(3)", linearCombo(intArrayOf(1, 2, 3)))

        // Test case 2: [0, 1, 2, 3]
        assertEquals("e(2) + 2*e(3) + 3*e(4)", linearCombo(intArrayOf(0, 1, 2, 3)))

        // Test case 3: [1, 0, 3, 4]
        assertEquals("e(1) + 3*e(3) + 4*e(4)", linearCombo(intArrayOf(1, 0, 3, 4)))

        // Test case 4: [1, 2, 0]
        assertEquals("e(1) + 2*e(2)", linearCombo(intArrayOf(1, 2, 0)))

        // Test case 5: [0, 0, 0]
        assertEquals("0", linearCombo(intArrayOf(0, 0, 0)))

        // Test case 6: [0]
        assertEquals("0", linearCombo(intArrayOf(0)))

        // Test case 7: [1, 1, 1]
        assertEquals("e(1) + e(2) + e(3)", linearCombo(intArrayOf(1, 1, 1)))

        // Test case 8: [-1, -1, -1]
        assertEquals("-e(1) - e(2) - e(3)", linearCombo(intArrayOf(-1, -1, -1)))

        // Test case 9: [-1, -2, 0, -3]
        assertEquals("-e(1) - 2*e(2) - 3*e(4)", linearCombo(intArrayOf(-1, -2, 0, -3)))

        // Test case 10: [-1]
        assertEquals("-e(1)", linearCombo(intArrayOf(-1)))
    }
}
