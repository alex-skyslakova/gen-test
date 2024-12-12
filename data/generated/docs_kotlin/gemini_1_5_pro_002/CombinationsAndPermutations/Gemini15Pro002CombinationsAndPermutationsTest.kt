import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger
import kotlin.test.assertEquals

class CombinationsAndPermutationsTest {

    @Test
    fun testPerm() {
        assertEquals(BigInteger.valueOf(1), perm(1, 0))
        assertEquals(BigInteger.valueOf(2), perm(2, 1))
        assertEquals(BigInteger.valueOf(6), perm(3, 2))
        assertEquals(BigInteger.valueOf(24), perm(4, 3))
        assertEquals(BigInteger.valueOf(120), perm(5, 5))
        assertEquals(BigInteger.valueOf(2004189184), perm(20,10))


        assertThrows<IllegalArgumentException> { perm(0, 0) }
        assertThrows<IllegalArgumentException> { perm(-1, 0) }
        assertThrows<IllegalArgumentException> { perm(1, -1) }
    }

    @Test
    fun testComb() {
        assertEquals(BigInteger.valueOf(1), comb(1, 0))
        assertEquals(BigInteger.valueOf(2), comb(2, 1))
        assertEquals(BigInteger.valueOf(3), comb(3, 2))
        assertEquals(BigInteger.valueOf(4), comb(4, 3))
        assertEquals(BigInteger.valueOf(1), comb(5, 5))
        assertEquals(BigInteger.valueOf(10), comb(5, 2))
        assertEquals(BigInteger.valueOf(184756), comb(20, 10))

        assertThrows<IllegalArgumentException> { comb(0, 0) }
        assertThrows<IllegalArgumentException> { comb(-1, 0) }
        assertThrows<IllegalArgumentException> { comb(1, -1) }
    }


}
