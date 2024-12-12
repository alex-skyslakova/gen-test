import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CatalanNumbersTest {

    @Test
    fun testCatalanI() {
        val expected = listOf(1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845)
        for (i in expected.indices) {
            assertEquals(expected[i].toDouble(), CatalanI(i))
        }
    }

    @Test
    fun testCatalanR1() {
        val expected = listOf(1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845)
        for (i in expected.indices) {
            assertEquals(expected[i].toDouble(), CatalanR1(i))
        }
    }

    @Test
    fun testCatalanR2() {
        val expected = listOf(1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845)
        for (i in expected.indices) {
            assertEquals(expected[i].toDouble(), CatalanR2(i))
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}
