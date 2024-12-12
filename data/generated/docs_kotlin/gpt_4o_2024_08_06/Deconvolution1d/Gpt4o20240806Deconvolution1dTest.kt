import kotlin.test.assertEquals

fun testDeconv() {
    val h = doubleArrayOf(-8.0, -9.0, -3.0, -1.0, -6.0, 7.0)
    val f = doubleArrayOf(-3.0, -6.0, -1.0, 8.0, -6.0, 3.0, -1.0, -9.0, 
                          -9.0, 3.0, -2.0, 5.0, 2.0, -2.0, -7.0, -1.0)
    val g = doubleArrayOf(24.0, 75.0, 71.0, -34.0, 3.0, 22.0, -45.0, 
                          23.0, 245.0, 25.0, 52.0, 25.0, -67.0, -96.0,
                          96.0, 31.0, 55.0, 36.0, 29.0, -43.0, -7.0)

    // Test deconv(g, f) = h
    val computedH = deconv(g, f)
    assertEquals(h.size, computedH.size, "Size of computed h does not match expected size.")
    for (i in h.indices) {
        assertEquals(h[i], computedH[i], 1e-9, "Mismatch at index $i for deconv(g, f)")
    }

    // Test deconv(g, h) = f
    val computedF = deconv(g, h)
    assertEquals(f.size, computedF.size, "Size of computed f does not match expected size.")
    for (i in f.indices) {
        assertEquals(f[i], computedF[i], 1e-9, "Mismatch at index $i for deconv(g, h)")
    }

    println("All tests passed.")
}

fun main() {
    testDeconv()
}
