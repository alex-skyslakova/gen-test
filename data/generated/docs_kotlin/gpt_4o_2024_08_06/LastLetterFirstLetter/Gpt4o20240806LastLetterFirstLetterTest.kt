import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LastLetterFirstLetterTest {

    @Test
    fun testMaxPathLength() {
        // Reset global variables before test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.setLength(0)

        // Run the main function to perform the search
        main(arrayOf())

        // Check if the maximum path length is as expected
        assertEquals(31, maxPathLength, "The maximum path length should be 31.")
    }

    @Test
    fun testMaxPathLengthCount() {
        // Reset global variables before test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.setLength(0)

        // Run the main function to perform the search
        main(arrayOf())

        // Check if the number of paths of maximum length is as expected
        assertEquals(1, maxPathLengthCount, "There should be 1 path of maximum length.")
    }

    @Test
    fun testMaxPathExample() {
        // Reset global variables before test
        maxPathLength = 0
        maxPathLengthCount = 0
        maxPathExample.setLength(0)

        // Run the main function to perform the search
        main(arrayOf())

        // Check if the example path is as expected
        val expectedExample = """
          audino
          octillery
          yanma
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
          kingler
          rattata
          arbok
        """.trimIndent()

        assertEquals(expectedExample, maxPathExample.toString().trim(), "The example path should match the expected path.")
    }
}
