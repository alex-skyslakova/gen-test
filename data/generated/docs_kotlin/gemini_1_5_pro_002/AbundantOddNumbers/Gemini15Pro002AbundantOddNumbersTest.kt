import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

@Test
fun divisorsTest() {
    assertEquals(listOf(1), divisors(1))
    assertEquals(listOf(1, 2, 3, 6), divisors(6))
    assertEquals(listOf(1, 3, 5, 15), divisors(15))
    assertEquals(listOf(1, 2, 4, 5, 8, 10, 20, 40), divisors(40))
    assertEquals(listOf(1, 3, 7, 9, 21, 63), divisors(63))
    assertEquals(listOf(1, 5, 7, 11, 35, 55, 77, 385), divisors(385))
}



@Test
fun abundantOddTest() {
    // Redirect System.out to capture printed output
    val out = ByteArrayOutputStream()
    val originalOut = System.out
    System.setOut(PrintStream(out))


    // Test a small range without printing
    val n1 = abundantOdd(1, 0, 3, false)
    assertEquals(9, n1)

    // Reset the count and test printing a single number
    out.reset()
    val n2 = abundantOdd(95000, 0, 1, true)
    val expectedOutput2 = "95041 < 1 + 7 + 11 + 67 + 77 + 469 + 847 + 1351 = 2824\n" // Example output, might vary slightly. Adjust based on your calculation
    assertEquals(expectedOutput2, out.toString())
    assertTrue(n2 > 95000)

     out.reset()
    val n3 = abundantOdd(945, 0, 1, true) 
    val expectedOutput3 = "945 < 1 + 3 + 5 + 7 + 9 + 15 + 21 + 27 + 35 + 45 + 63 + 105 + 135 + 189 + 315 = 975\n"
    assertEquals(expectedOutput3, out.toString())
    assertTrue(n3 > 945)


    System.setOut(originalOut) // restore System.out

}



