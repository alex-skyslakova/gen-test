import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class If2Test {

    @Test
    public void testBothConditionsAreTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(true, true, bothTrue, firstTrue, secondTrue, noneTrue);

        assertTrue(bothTrue.isCalled());
        assertFalse(firstTrue.isCalled());
        assertFalse(secondTrue.isCalled());
        assertFalse(noneTrue.isCalled());
    }

    @Test
    public void testFirstConditionIsTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(true, false, bothTrue, firstTrue, secondTrue, noneTrue);

        assertFalse(bothTrue.isCalled());
        assertTrue(firstTrue.isCalled());
        assertFalse(secondTrue.isCalled());
        assertFalse(noneTrue.isCalled());
    }

    @Test
    public void testSecondConditionIsTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(false, true, bothTrue, firstTrue, secondTrue, noneTrue);

        assertFalse(bothTrue.isCalled());
        assertFalse(firstTrue.isCalled());
        assertTrue(secondTrue.isCalled());
        assertFalse(noneTrue.isCalled());
    }

    @Test
    public void testNoConditionIsTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(false, false, bothTrue, firstTrue, secondTrue, noneTrue);

        assertFalse(bothTrue.isCalled());
        assertFalse(firstTrue.isCalled());
        assertFalse(secondTrue.isCalled());
        assertTrue(noneTrue.isCalled());
    }

    private Runnable mockRunnable() {
        return new Runnable() {
            private boolean called = false;

            @Override
            public void run() {
                called = true;
            }

            public boolean isCalled() {
                return called;
            }
        };
    }
}
