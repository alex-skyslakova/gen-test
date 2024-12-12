import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DelegateExampleTest {

    @Test
    void testOperationWithoutDelegate() {
        Delegator delegator = new Delegator();
        String result = delegator.operation();
        assertEquals("default implementation", result);
    }

    @Test
    void testOperationWithDelegate() {
        Delegator delegator = new Delegator();
        Delegate delegate = new Delegate();
        delegator.delegate = delegate;
        String result = delegator.operation();
        assertEquals("delegate implementation", result);
    }

    @Test
    void testOperationWithAnonymousDelegate() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Thingable() {
            @Override
            public String thing() {
                return "anonymous delegate implementation";
            }
        };
        String result = delegator.operation();
        assertEquals("anonymous delegate implementation", result);
    }

    @Test
    void testOperationWithNullDelegate() {
        Delegator delegator = new Delegator();
        delegator.delegate = null;
        String result = delegator.operation();
        assertEquals("default implementation", result);
    }

    @Test
    void testOperationWithDelegateNotImplementingThing() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Thingable() {
            @Override
            public String thing() {
                return null; // Delegate does not implement "thing"
            }
        };
        String result = delegator.operation();
        assertNull(result); // Assuming the delegate returns null
    }
}
