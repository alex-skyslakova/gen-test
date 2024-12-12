import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.applet.AppletStub;
import java.net.URL;


public class EmptyAppletTest {

    @Test
    public void testInit() {
        EmptyApplet applet = new EmptyApplet();
        TestAppletStub stub = new TestAppletStub();
        applet.setStub(stub);
        applet.init();
        // No specific assertions are needed.  The fact that init() 
        // completes without throwing an exception is sufficient for
        // this simple applet.  We could optionally assert that 
        // certain lifecycle methods on the stub were called, but
        // since there is no actual functionality implemented it
        // isn't strictly necessary for this case.
    }


    // Helper class for testing
    class TestAppletStub implements AppletStub {
        @Override
        public boolean isActive() {
            return false; 
        }

        @Override
        public URL getDocumentBase() {
            try {
                return new URL("http://example.com");
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public URL getCodeBase() {
            try {
                return new URL("http://example.com");
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public String getParameter(String name) {
            return null;
        }

        @Override
        public AppletContext getAppletContext() {
            return null;
        }

        @Override
        public void appletResize(int width, int height) {
        }
    }
}
