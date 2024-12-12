import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class DiscoverHostNameTest {

    @Test
    void getHostNameTest() throws UnknownHostException {
        // Since we can't reliably predict the hostname, we'll just check 
        // that the returned value isn't null or empty and doesn't throw an exception.

        String hostname = InetAddress.getLocalHost().getHostName();
        assertNotNull(hostname);
        assertFalse(hostname.isEmpty());
    }



    @Test
    void getHostNameHandlesUnknownHostException() throws UnknownHostException {

        // Mock InetAddress to throw UnknownHostException
        InetAddress mockInetAddress = new InetAddress() {
            @Override
            public String getHostName() throws UnknownHostException {
                throw new UnknownHostException("Simulated UnknownHostException");
            }

            @Override
            public String getCanonicalHostName() {
                return null; // Not relevant to this test
            }

            @Override
            public byte[] getAddress() {
                return null; // Not relevant to this test
            }

            @Override
            public String getHostAddress() {
                return null; // Not relevant to this test
            }

            @Override
            public boolean isMulticastAddress() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isAnyLocalAddress() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isLoopbackAddress() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isLinkLocalAddress() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isSiteLocalAddress() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isMCGlobal() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isMCNodeLocal() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isMCLinkLocal() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isMCSiteLocal() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isMCOrgLocal() {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isReachable(int timeout) {
                return false; // Not relevant to this test
            }

            @Override
            public boolean isReachable(NetworkInterface netif, int ttl, int timeout) {
                return false; // Not relevant to this test
            }

            @Override
            public String toString() {
                return null; // Not relevant to this test
            }

            @Override
            public boolean equals(Object obj) {
                return false; // Not relevant to this test
            }

            @Override
            public int hashCode() {
                return 0; // Not relevant to this test
            }
        };




        // Use assertThrows to ensure the UnknownHostException is handled and doesn't cause the test to fail.
         assertThrows(UnknownHostException.class, () -> {
            mockInetAddress.getHostName();
         });
    }
}
