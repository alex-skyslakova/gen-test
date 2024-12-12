import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FairshareTest {

    @Test
    fun testTurnBase2() {
        assertEquals(0, turn(2, 0))
        assertEquals(1, turn(2, 1))
        assertEquals(1, turn(2, 2))
        assertEquals(0, turn(2, 3))
        assertEquals(1, turn(2, 4))
        assertEquals(0, turn(2, 5))
        assertEquals(0, turn(2, 6))
        assertEquals(1, turn(2, 7))
    }

    @Test
    fun testTurnBase3() {
        assertEquals(0, turn(3, 0))
        assertEquals(1, turn(3, 1))
        assertEquals(2, turn(3, 2))
        assertEquals(2, turn(3, 3))
        assertEquals(0, turn(3, 4))
        assertEquals(1, turn(3, 5))
        assertEquals(1, turn(3, 6))
        assertEquals(2, turn(3, 7))
        assertEquals(0, turn(3, 8))

    }

    @Test
    fun testTurnBase5() {
        assertEquals(0, turn(5, 0))
        assertEquals(1, turn(5, 1))
        assertEquals(2, turn(5, 2))
        assertEquals(3, turn(5, 3))
        assertEquals(4, turn(5, 4))
        assertEquals(4, turn(5, 5))
        assertEquals(0, turn(5, 6))
        assertEquals(1, turn(5, 7))
    }


    @Test
    fun testTurnBase11() {
        assertEquals(0, turn(11, 0))
        assertEquals(1, turn(11, 1))
        assertEquals(2, turn(11, 2))
        assertEquals(3, turn(11, 3))
        assertEquals(4, turn(11, 4))
        assertEquals(5, turn(11, 5))
        assertEquals(6, turn(11, 6))
        assertEquals(7, turn(11, 7))
        assertEquals(8, turn(11, 8))
        assertEquals(9, turn(11, 9))
        assertEquals(10, turn(11, 10))
        assertEquals(10, turn(11, 11))
        assertEquals(0, turn(11, 12))

    }

    @Test
    fun testTurnLargeBaseAndNumber(){
        assertEquals(1, turn(12345, 12345))
        assertEquals(40478, turn(50001, 49999))

    }

     @Test
    fun testTurnCountAllTurnsEqual(){
        testTurnCountHelper(2, 4, 2) //All equal
        testTurnCountHelper(3, 27, 9) //All equal
    }

    @Test
    fun testTurnCountNotAllTurnsEqual(){
        testTurnCountHelper(2, 5, "2 or 3") //Not all equal
        testTurnCountHelper(3, 5, "1 or 2") //Not all equal
        testTurnCountHelper(191, 50000, "261 or 262") //Not all equal
    }
    @Test
    fun testTurnCountNotAllGetTurn(){
        testTurnCountHelper(50000, 50000, "Only 49999 have a turn") //Not all have a turn
        testTurnCountHelper(50001, 50000, 49999) //Only one turn
    }

    private fun testTurnCountHelper(base: Int, count: Int, expected: Any) {
        val outputStreamCaptor = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStreamCaptor))

        turnCount(base, count)

        System.setOut(System.out) // Reset System.out

        val output = outputStreamCaptor.toString().trim() // Trim whitespace
        if(expected is String){
            assertTrue(output.contains(expected))
        }else {
             assertTrue(output.contains(expected.toString()))
        }

    }



}
