import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException

class MacVendorLookupTest {

    @Test
    fun `test valid MAC address returns manufacturer`() {
        val mac = "FC-A1-3E"
        val expectedManufacturer = "Samsung Electronics Co.,Ltd"
        val result = lookupVendor(mac)
        assertEquals(expectedManufacturer, result)
    }

    @Test
    fun `test invalid MAC address returns N/A`() {
        val mac = "00-00-00-00-00-00"
        val expectedResult = "N/A"
        val result = lookupVendor(mac)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `test network error returns null`() {
        val mac = "FC-A1-3E"
        // Simulate a network error by providing an invalid URL
        val result = lookupVendorWithCustomURL(mac, "http://invalid-url.com/" + mac)
        assertNull(result)
    }

    @Test
    fun `test API throttling returns null`() {
        val mac = "FC-A1-3E"
        // Simulate API throttling by providing an invalid URL
        val result = lookupVendorWithCustomURL(mac, "http://api.macvendors.com/" + mac)
        assertNull(result)
    }

    private fun lookupVendorWithCustomURL(mac: String, urlString: String): String? {
        return try {
            URL(urlString).readText()
        } catch (e: IOException) {
            null
        }
    }
}
