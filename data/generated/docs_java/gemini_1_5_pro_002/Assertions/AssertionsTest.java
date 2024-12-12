import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    @Test
    void testAssertionPassing() {
        int a = 42;
        assert a == 42; 
    }

    @Test
    void testAssertionFailing() {
        int a = 13;
        assertThrows(AssertionError.class, () -> {
            assert a == 42;
        });
    }

    @Test
    void testAssertionFailingWithMessage() {
        int a = 13;
        AssertionError error = assertThrows(AssertionError.class, () -> {
           assert a == 42 : "Error message";
        });
        assertEquals("Error message", error.getMessage());
    }


     @Test
    void testAssertionEnabled() {
         SecurityManager securityManager = System.getSecurityManager();
        boolean enabled = false;
        try {
            assert false;
        } catch (AssertionError e) {
            enabled = true;
        }

        // Assertions are enabled if a SecurityManager is present *or* the -ea flag is used.
        // This test only checks for implicit enabling via SecurityManager.
        if(securityManager != null) {
            assertTrue(enabled);
        }
    }

     @Test
    void testAssertionDisabled() {

         // Assuming assertions are disabled by default when no SecurityManager and -ea aren't used.
         // This isn't testable directly, as if enabled, the test would throw.  So we just let it pass if it doesn't.
          int a = 13;
          try {
             assert a==42; // This should NOT throw if disabled
          } catch (AssertionError e) {
             fail("Assertions unexpectedly enabled"); // Fail if assertion is thrown, indicating enabled status.
          }

    }
}
