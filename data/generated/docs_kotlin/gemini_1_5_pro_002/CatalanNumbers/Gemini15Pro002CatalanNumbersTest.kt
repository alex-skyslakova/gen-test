import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CatalanTests {

    @Test
    fun catalanITest() {
        val catalanI = CatalanI
        assertEquals(1, catalanI(0).toLong())
        assertEquals(1, catalanI(1).toLong())
        assertEquals(2, catalanI(2).toLong())
        assertEquals(5, catalanI(3).toLong())
        assertEquals(14, catalanI(4).toLong())
        assertEquals(42, catalanI(5).toLong())
        assertEquals(132, catalanI(6).toLong())
        assertEquals(429, catalanI(7).toLong())
        assertEquals(1430, catalanI(8).toLong())
        assertEquals(4862, catalanI(9).toLong())
        assertEquals(16796, catalanI(10).toLong())
        assertEquals(58786, catalanI(11).toLong())
        assertEquals(208012, catalanI(12).toLong())
        assertEquals(742900, catalanI(13).toLong())
        assertEquals(2674440, catalanI(14).toLong())
        assertEquals(9694845, catalanI(15).toLong())
    }

    @Test
    fun catalanR1Test() {
        val catalanR1 = CatalanR1
        assertEquals(1, catalanR1(0).toLong())
        assertEquals(1, catalanR1(1).toLong())
        assertEquals(2, catalanR1(2).toLong())
        assertEquals(5, catalanR1(3).toLong())
        assertEquals(14, catalanR1(4).toLong())
        assertEquals(42, catalanR1(5).toLong())
        assertEquals(132, catalanR1(6).toLong())
        assertEquals(429, catalanR1(7).toLong())
        assertEquals(1430, catalanR1(8).toLong())
        assertEquals(4862, catalanR1(9).toLong())
        assertEquals(16796, catalanR1(10).toLong())
        assertEquals(58786, catalanR1(11).toLong())
        assertEquals(208012, catalanR1(12).toLong())
        assertEquals(742900, catalanR1(13).toLong())
        assertEquals(2674440, catalanR1(14).toLong())
        assertEquals(9694845, catalanR1(15).toLong())
    }

    @Test
    fun catalanR2Test() {
        val catalanR2 = CatalanR2
        assertEquals(1, catalanR2(0).toLong())
        assertEquals(1, catalanR2(1).toLong())
        assertEquals(2, catalanR2(2).toLong())
        assertEquals(5, catalanR2(3).toLong())
        assertEquals(14, catalanR2(4).toLong())
        assertEquals(42, catalanR2(5).toLong())
        assertEquals(132, catalanR2(6).toLong())
        assertEquals(429, catalanR2(7).toLong())
        assertEquals(1430, catalanR2(8).toLong())
        assertEquals(4862, catalanR2(9).toLong())
        assertEquals(16796, catalanR2(10).toLong())
        assertEquals(58786, catalanR2(11).toLong())
        assertEquals(208012, catalanR2(12).toLong())
        assertEquals(742900, catalanR2(13).toLong())
        assertEquals(2674440, catalanR2(14).toLong())
        assertEquals(9694845, catalanR2(15).toLong())
    }
}
