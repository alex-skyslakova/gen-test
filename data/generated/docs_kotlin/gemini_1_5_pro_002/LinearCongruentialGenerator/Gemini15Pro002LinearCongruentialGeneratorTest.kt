import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LcgTest {

    @Test
    fun testBsdFirstTen() {
        val bsd = Lcg(1103515245, 12345, 1 shl 31, 1, 0)
        val expected = listOf(12345L, 1406932606L, 1649763712L, 1237590838L, 822761994L, 1113075892L, 1630527030L, 2067675006L, 1953872432L, 1443929756L)
        for (i in 0..9) {
            assertEquals(expected[i], bsd.nextInt())
        }
    }

    @Test
    fun testMscFirstTen() {
        val msc = Lcg(214013, 2531011, 1 shl 31, 1 shl 16, 0)
        val expected = listOf(39321L, 16807L, 22243L, 22190L, 22680L, 29451L, 29656L, 27201L, 28453L,  6555L)
        for (i in 0..9) {
            assertEquals(expected[i], msc.nextInt())
        }
    }

    @Test
    fun testBsdDifferentSeed() {
        val bsd = Lcg(1103515245, 12345, 1 shl 31, 1, 123)
        assertEquals(1407056051L, bsd.nextInt())
    }

    @Test
    fun testMscDifferentSeed() {
        val msc = Lcg(214013, 2531011, 1 shl 31, 1 shl 16, 456)
        assertEquals(27549L, msc.nextInt())
    }

    @Test
    fun testBsdMax() {
        val bsd = Lcg(1103515245, 12345, 1 shl 31, 1, 2147483646) // Seed close to max
        assertEquals(2147483647L, bsd.nextInt())
    }


}
