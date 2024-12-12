import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DelegatorTest {

    @Test
    void testNoDelegate() {
        Delegator delegator = new Delegator();
        assertEquals("default implementation", delegator.operation());
    }

    @Test
    void testDelegateWithoutThing() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Thingable() {
            // Empty implementation - effectively doesn't implement thing()
        };

        // In Java, this will actually throw a NullPointerException at runtime 
        // because the default implementation of an interface method is to throw 
        // an AbstractMethodError if it is called directly when there is no default implementation.
        assertThrows(AbstractMethodError.class, delegator::operation);

    }

    @Test
    void testDelegateWithThing() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Delegate();
        assertEquals("delegate implementation", delegator.operation());
    }

    @Test
    void testAnonymousDelegateWithThing() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Thingable() {
            @Override
            public String thing() {
                return "anonymous delegate implementation";
            }
        };
        assertEquals("anonymous delegate implementation", delegator.operation());
    }


    interface Thingable {
        String thing();
    }

    class Delegator {
        public Thingable delegate;

        public String operation() {
            if (delegate == null)
                return "default implementation";
            else
                return delegate.thing();
        }
    }

    class Delegate implements Thingable {
        public String thing() {
            return "delegate implementation";
        }
    }
}
