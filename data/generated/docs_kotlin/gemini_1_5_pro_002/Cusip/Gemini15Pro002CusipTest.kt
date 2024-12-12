import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CusipTest {

    @Test
    fun testValidCusips() {
        assertTrue(isCusip("037833100")) // Apple Incorporated
        assertTrue(isCusip("17275R102")) // Cisco Systems
        assertTrue(isCusip("38259P508")) // Google Incorporated
        assertTrue(isCusip("594918104")) // Microsoft Corporation
        assertTrue(isCusip("68389X105")) // Oracle Corporation (correct)
    }

    @Test
    fun testInvalidCusips() {
        assertFalse(isCusip("68389X106")) // Oracle Corporation (incorrect)
        assertFalse(isCusip("12345678"))  // Too short
        assertFalse(isCusip("1234567890")) // Too long
        assertFalse(isCusip("12345678A")) // Invalid check digit
        assertFalse(isCusip("12345678a")) // Lowercase letter
        assertFalse(isCusip("12345-789")) // Invalid character
    }

    @Test
    fun testSpecialCharacters() {
        assertTrue(isCusip("12345*103")) // Test * character
        assertTrue(isCusip("12345@100")) // Test @ character
        assertTrue(isCusip("12345#106")) // Test # character


        // Examples with special characters and incorrect check digits
         assertFalse(isCusip("12345*102")) // incorrect
         assertFalse(isCusip("12345@101")) // incorrect
         assertFalse(isCusip("12345#105")) // incorrect
    }



    // The isCusip function needs to be defined within the same file for testing 
    // or imported appropriately if it's in a different file.
     fun isCusip(s: String): Boolean {
        if (s.length != 9) return false
        var sum = 0
        for (i in 0..7) {
            val c = s[i]
            var v = when (c) {
                in '0'..'9'  -> c.toInt() - 48
                in 'A'..'Z'  -> c.toInt() - 55  // lower case letters apparently invalid
                '*'          -> 36
                '@'          -> 37
                '#'          -> 38
                else         -> return false
            }
            if (i % 2 == 1) v *= 2  // check if odd as using 0-based indexing
            sum += v / 10 + v % 10
        }
        return s[8].toInt() - 48  == (10 - (sum % 10)) % 10
    }
}



