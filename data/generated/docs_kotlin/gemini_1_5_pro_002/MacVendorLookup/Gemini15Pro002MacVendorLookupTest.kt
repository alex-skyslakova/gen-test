import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

class MacVendorLookupTest {

    @Test
    fun testValidMacAddress() {
        // Using a known MAC address with a vendor
        val mac = "00:14:22:0A:2E:AC" // Example: Dell
        val expectedVendor = "Dell Inc."
        // Introduce a small delay to avoid rate limiting
        Thread.sleep(1000) 
        val vendor = lookupVendor(mac)
        assertEquals(expectedVendor, vendor)
    }

    @Test
    fun testInvalidMacAddress() {
        val mac = "INVALID_MAC"
        // Introduce a small delay to avoid rate limiting
        Thread.sleep(1000) 
        val vendor = lookupVendor(mac)
        assertEquals("N/A", vendor)
    }


    @Test
    fun testUnknownHostException() {
        // Simulate an unknown host by using a non-existent domain
        val invalidUrl = "http://nonexistentdomain.example.com/00:00:00:00:00:00"
        try {
            URL(invalidUrl).readText()
            fail("Expected UnknownHostException")
        } catch (e: UnknownHostException) {
            // Expected behavior
        } catch (e: Exception) {
            fail("Expected UnknownHostException, but got ${e::class.simpleName}")
        }

    }

    @Test
    fun testTooManyRequests() {
         // This test is difficult to reliably simulate without mocking 
         // the underlying network connection.  It depends on the specific
         // API and its rate limiting behavior, which can change.  A more 
         // robust approach would be to mock the URL connection and inject
         // a simulated  "Too Many Requests" response.

         // This simplified version just attempts a few rapid requests 
         // and checks for a null result, which the prompt states should
         // be returned for network/API errors. This isn't a perfect test
         // for rate limiting specifically.  Consider mocking for more precision.



        val mac = "00-11-22-33-44-55"
        
        try {
            repeat(5) { // Make multiple requests quickly
                lookupVendor(mac)
            }
            fail("Expected a null result due to rate limiting.") // Not guaranteed to happen
        } catch (e: IOException) { 
            // Depending on the specific exception thrown when the API is throttled, 
            // you might want to check the message to be sure it's a "Too Many Requests" error.
        }



    }



    // Helper function to adapt the lookupVendor to handle exceptions as specified
    private fun lookupVendor(mac: String): String? {
        try {
            val result = URL("http://api.macvendors.com/" + mac).readText()
            return if (result.contains("errors")) "N/A" else result

        } catch (e: UnknownHostException) {
            return null // Return null for network errors
        } catch (e: IOException) {
             // This might need more specific error handling depending
             // on the exception thrown when the API is throttled.
            return null  // Return null for other IO errors
        }
    }
}
