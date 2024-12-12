import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HeronTest {

    @Test
    fun testGcd() {
        assertEquals(1, Heron.gcd(3, 4))
        assertEquals(1, Heron.gcd(5, 7))
        assertEquals(2, Heron.gcd(4, 6))
        assertEquals(5, Heron.gcd(10, 15))
    }

    @Test
    fun testIsHeron() {
        assertEquals(true, Heron.isHeron(6.0))
        assertEquals(false, Heron.isHeron(6.5))
        assertEquals(true, Heron.isHeron(210.0))
        assertEquals(false, Heron.isHeron(0.0))
    }

    @Test
    fun testSort() {
        val list = mutableListOf(
            intArrayOf(3, 4, 5, 12, 6),
            intArrayOf(5, 12, 13, 30, 30),
            intArrayOf(6, 8, 10, 24, 24)
        )
        Heron.sort(list)
        assertEquals(6, list[0][4])  // Area of the first triangle after sorting
        assertEquals(24, list[1][4]) // Area of the second triangle after sorting
        assertEquals(30, list[2][4]) // Area of the third triangle after sorting
    }

    @Test
    fun testPrimitiveHeronianTrianglesCount() {
        val heronianTriangles = mutableListOf<IntArray>()
        for (c in 1..200)
            for (b in 1..c)
                for (a in 1..b)
                    if (Heron.gcd(Heron.gcd(a, b), c) == 1) {
                        val p = a + b + c
                        val s = p / 2.0
                        val area = Math.sqrt(s * (s - a) * (s - b) * (s - c))
                        if (Heron.isHeron(area))
                            heronianTriangles.add(intArrayOf(a, b, c, p, area.toInt()))
                    }
        assertEquals(16, heronianTriangles.size) // Expected number of primitive Heronian triangles
    }

    @Test
    fun testFirstTenTriangles() {
        val heronianTriangles = mutableListOf<IntArray>()
        for (c in 1..200)
            for (b in 1..c)
                for (a in 1..b)
                    if (Heron.gcd(Heron.gcd(a, b), c) == 1) {
                        val p = a + b + c
                        val s = p / 2.0
                        val area = Math.sqrt(s * (s - a) * (s - b) * (s - c))
                        if (Heron.isHeron(area))
                            heronianTriangles.add(intArrayOf(a, b, c, p, area.toInt()))
                    }
        Heron.sort(heronianTriangles)
        val firstTen = heronianTriangles.take(10)
        assertEquals(10, firstTen.size)
        assertEquals(6, firstTen[0][4]) // Area of the first triangle
    }

    @Test
    fun testTrianglesWithArea210() {
        val heronianTriangles = mutableListOf<IntArray>()
        for (c in 1..200)
            for (b in 1..c)
                for (a in 1..b)
                    if (Heron.gcd(Heron.gcd(a, b), c) == 1) {
                        val p = a + b + c
                        val s = p / 2.0
                        val area = Math.sqrt(s * (s - a) * (s - b) * (s - c))
                        if (Heron.isHeron(area))
                            heronianTriangles.add(intArrayOf(a, b, c, p, area.toInt()))
                    }
        Heron.sort(heronianTriangles)
        val area210Triangles = heronianTriangles.filter { it[4] == 210 }
        assertEquals(1, area210Triangles.size) // Number of triangles with area 210
    }
}
