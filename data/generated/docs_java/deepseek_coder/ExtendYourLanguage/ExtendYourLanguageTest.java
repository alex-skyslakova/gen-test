import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class If2Test {

    @Test
    void testBothConditionsAreTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(true, true, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(bothTrue).run();
        verifyNoMoreInteractions(firstTrue, secondTrue, noneTrue);
    }

    @Test
    void testFirstConditionIsTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(true, false, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(firstTrue).run();
        verifyNoMoreInteractions(bothTrue, secondTrue, noneTrue);
    }

    @Test
    void testSecondConditionIsTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(false, true, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(secondTrue).run();
        verifyNoMoreInteractions(bothTrue, firstTrue, noneTrue);
    }

    @Test
    void testNoConditionIsTrue() {
        Runnable bothTrue = mockRunnable();
        Runnable firstTrue = mockRunnable();
        Runnable secondTrue = mockRunnable();
        Runnable noneTrue = mockRunnable();

        If2.if2(false, false, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(noneTrue).run();
        verifyNoMoreInteractions(bothTrue, firstTrue, secondTrue);
    }

    private Runnable mockRunnable() {
        return mock(Runnable.class);
    }

    private void verify(Runnable runnable) {
        verify(runnable).run();
    }

    private void verifyNoMoreInteractions(Runnable... runnables) {
        for (Runnable runnable : runnables) {
            verifyNoMoreInteractions(runnable);
        }
    }
}
