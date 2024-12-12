import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CatalanNumbersTest {

    private val catalanValues = mapOf(
        0 to 1L,
        1 to 1L,
        2 to 2L,
        3 to 5L,
        4 to 14L,
        5 to 42L,
        6 to 132L,
        7 to 429L,
        8 to 1430L,
        9 to 4862L,
        10 to 16796L,
        11 to 58786L,
        12 to 208012L,
        13 to 742900L,
        14 to 2674440L,
        15 to 9694845L
    )

    @Test
    fun testCatalanI() {
        catalanValues.forEach { (n, expected) ->
            assertEquals(expected.toDouble(), CatalanI(n), "CatalanI($n) should be $expected")
        }
    }

    @Test
    fun testCatalanR1() {
        catalanValues.forEach { (n, expected) ->
            assertEquals(expected.toDouble(), CatalanR1(n), "CatalanR1($n) should be $expected")
        }
    }

    @Test
    fun testCatalanR2() {
        catalanValues.forEach { (n, expected) ->
            assertEquals(expected.toDouble(), CatalanR2(n), "CatalanR2($n) should be $expected")
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}
