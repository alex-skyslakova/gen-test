import com.jamesdonnell.MACVendor.Lookup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class LookupTest {

    @Test
    void testValidMACAddress() throws IOException, InterruptedException {
        // Using a known MAC address with a valid vendor
        String macAddress = "F0:DE:F1:B2:95:94"; // Adjust this MAC as needed.  May require a registry modification to allow testing with API limit if using the example URL.
        String expectedVendor = getRealVendor(macAddress); // Fetching actual value to compare 
        String result = Lookup.get(macAddress);
        if (expectedVendor.contains("Too Many Requests")) {
           // This assumes the test fails if throttled (consider upgrading account for testing or use other API).
           fail("API returned 'Too Many Requests'. Consider increasing the delay between tests or changing the test MAC address."); 
        }

        assertEquals(expectedVendor, result); // Comparison

        // Add a delay to avoid throttling. Adjust as necessary.
        Thread.sleep(1000); 
    }

    @Test
    void testInvalidMACAddress() throws InterruptedException {
        String macAddress = "InvalidMAC";
        String expectedResult = "N/A";
        String result = Lookup.get(macAddress);
        assertEquals(expectedResult, result);

        Thread.sleep(1000); // Delay

    }


    @Test
    void testNonExistentMACAddress() throws InterruptedException {
      // It's difficult to guarantee a truly non-existent MAC address.
      // This test relies on the API responding with "N/A" for an unknown MAC.
        String macAddress = "00:00:00:00:00:01";  // Highly unlikely MAC
        String expectedResult = "N/A";
        String result = Lookup.get(macAddress);
        assertEquals(expectedResult, result);

         Thread.sleep(1000); // Delay

    }
    
     @Test
    void testNetworkError() throws InterruptedException {
        // Simulate a network error by using an invalid base URL
        // Using reflection to temporarily modify the private static field for testing purposes.  This is not always ideal, but it allows unit testing the handling of a network error without actually causing it or mocking the dependencies.
        try {
            java.lang.reflect.Field field = Lookup.class.getDeclaredField("baseURL");
            field.setAccessible(true);
            field.set(null, "http://invalid.url/");  // Set an invalid base URL

            String macAddress = "F0:DE:F1:B2:95:94";
            String result = Lookup.get(macAddress);
            assertNull(result);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Error accessing or modifying baseURL field: " + e.getMessage());
        } finally {
            // Reset baseURL to original after the test if necessary. It is reset below because the baseURL field is static.
            try {
                java.lang.reflect.Field field = Lookup.class.getDeclaredField("baseURL");
                field.setAccessible(true);
                field.set(null, "http://api.macvendors.com/");
            } catch(NoSuchFieldException | IllegalAccessException ex){
                 fail("Error resetting baseURL field: " + ex.getMessage());
            }
        }
        Thread.sleep(1000); // Delay

    }

    // Helper function to get the actual vendor from the API (used for comparison)
    private String getRealVendor(String macAddress) throws IOException {
        URL url = new URL("http://api.macvendors.com/" + macAddress); // Replace with a stable test API URL if the test one is unreliable
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}
