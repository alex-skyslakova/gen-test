import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static org.junit.jupiter.api.Assertions.*;

class DiscoverHostNameTest {

    @Test
    void testGetHostName() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            assertNotNull(hostName, "Host name should not be null");
            assertFalse(hostName.isEmpty(), "Host name should not be empty");
        } catch (UnknownHostException e) {
            fail("UnknownHostException should not be thrown");
        }
    }
}
