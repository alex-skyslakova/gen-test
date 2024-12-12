import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.*
import java.net.URL
import java.io.IOException

class MacVendorLookupTest {

    @Test
    fun testValidMacAddress() {
        val mac = "FC:FB:FB:01:FA:21"
        val expectedVendor = "Apple, Inc."

        val url = mock(URL::class.java)
        `when`(url.readText()).thenReturn(expectedVendor)

        val result = lookupVendor(mac)
        assertEquals(expectedVendor, result)
    }

    @Test
    fun testInvalidMacAddress() {
        val mac = "00:00:00:00:00:00"
        val expectedVendor = "N/A"

        val url = mock(URL::class.java)
        `when`(url.readText()).thenReturn("")

        val result = lookupVendor(mac)
        assertEquals(expectedVendor, result)
    }

    @Test
    fun testNetworkError() {
        val mac = "88:53:2E:67:07:BE"

        val url = mock(URL::class.java)
        `when`(url.readText()).thenThrow(IOException::class.java)

        val result = lookupVendor(mac)
        assertNull(result)
    }

    @Test
    fun testApiThrottling() {
        val mac = "D4:F4:6F:C9:EF:8D"
        val throttlingResponse = "{\"errors\":{\"detail\":\"Too Many Requests\",\"message\":\"Please slow down your requests or upgrade your plan at https://macvendors.com\"}}"

        val url = mock(URL::class.java)
        `when`(url.readText()).thenReturn(throttlingResponse)

        val result = lookupVendor(mac)
        assertNull(result)
    }
}
