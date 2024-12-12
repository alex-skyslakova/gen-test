import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LinearCombinationTest {

    @Test
    fun testLinearCombo1() {
        assertEquals("e(1) + 2*e(2) + 3*e(3)", linearCombo(intArrayOf(1, 2, 3)))
    }

    @Test
    fun testLinearCombo2() {
        assertEquals("e(2) + 2*e(3) + 3*e(4)", linearCombo(intArrayOf(0, 1, 2, 3)))
    }

    @Test
    fun testLinearCombo3() {
        assertEquals("e(1) + 3*e(3) + 4*e(4)", linearCombo(intArrayOf(1, 0, 3, 4)))
    }

    @Test
    fun testLinearCombo4() {
        assertEquals("e(1) + 2*e(2)", linearCombo(intArrayOf(1, 2, 0)))
    }

    @Test
    fun testLinearCombo5() {
        assertEquals("0", linearCombo(intArrayOf(0, 0, 0)))
    }

    @Test
    fun testLinearCombo6() {
        assertEquals("0", linearCombo(intArrayOf(0)))
    }

    @Test
    fun testLinearCombo7() {
        assertEquals("e(1) + e(2) + e(3)", linearCombo(intArrayOf(1, 1, 1)))
    }

    @Test
    fun testLinearCombo8() {
        assertEquals("-e(1) - e(2) - e(3)", linearCombo(intArrayOf(-1, -1, -1)))
    }

    @Test
    fun testLinearCombo9() {
        assertEquals("-e(1) - 2*e(2) - 3*e(4)", linearCombo(intArrayOf(-1, -2, 0, -3)))
    }

    @Test
    fun testLinearCombo10() {
        assertEquals("-e(1)", linearCombo(intArrayOf(-1)))
    }

    @Test
    fun testEmptyArray(){
        assertEquals("0", linearCombo(intArrayOf()))
    }

    @Test
    fun testAllZerosExceptLast(){
        assertEquals("3*e(4)", linearCombo(intArrayOf(0,0,0,3)))
    }

    @Test
    fun testOneNegativeCoefficient(){
        assertEquals("e(1) - e(3)", linearCombo(intArrayOf(1,0,-1)))
    }


}
