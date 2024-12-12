import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DelegateExampleTest {

    @Test
    public void testOperationWithoutDelegate() {
        Delegator delegator = new Delegator();
        assertEquals("default implementation", delegator.operation());
    }

    @Test
    public void testOperationWithDelegate() {
        Delegator delegator = new Delegator();
        Delegate delegate = new Delegate();
        delegator.delegate = delegate;
        assertEquals("delegate implementation", delegator.operation());
    }

    @Test
    public void testOperationWithAnonymousDelegate() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Thingable() {
            @Override
            public String thing() {
                return "anonymous delegate implementation";
            }
        };
        assertEquals("anonymous delegate implementation", delegator.operation());
    }

    @Test
    public void testOperationWithNonThingableDelegate() {
        Delegator delegator = new Delegator();
        delegator.delegate = new Thingable() {
            // No implementation of thing() method
            @Override
            public String thing() {
                return "non-thingable delegate implementation";
            }
        };
        assertEquals("non-thingable delegate implementation", delegator.operation());
    }
}
