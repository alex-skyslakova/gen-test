import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class If2Test {

    @Test
    public void testBothConditionsTrue() {
        Runnable bothTrue = mock(Runnable.class);
        Runnable firstTrue = mock(Runnable.class);
        Runnable secondTrue = mock(Runnable.class);
        Runnable noneTrue = mock(Runnable.class);

        If2.if2(true, true, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(bothTrue, times(1)).run();
        verify(firstTrue, never()).run();
        verify(secondTrue, never()).run();
        verify(noneTrue, never()).run();
    }

    @Test
    public void testFirstConditionTrueOnly() {
        Runnable bothTrue = mock(Runnable.class);
        Runnable firstTrue = mock(Runnable.class);
        Runnable secondTrue = mock(Runnable.class);
        Runnable noneTrue = mock(Runnable.class);

        If2.if2(true, false, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(bothTrue, never()).run();
        verify(firstTrue, times(1)).run();
        verify(secondTrue, never()).run();
        verify(noneTrue, never()).run();
    }

    @Test
    public void testSecondConditionTrueOnly() {
        Runnable bothTrue = mock(Runnable.class);
        Runnable firstTrue = mock(Runnable.class);
        Runnable secondTrue = mock(Runnable.class);
        Runnable noneTrue = mock(Runnable.class);

        If2.if2(false, true, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(bothTrue, never()).run();
        verify(firstTrue, never()).run();
        verify(secondTrue, times(1)).run();
        verify(noneTrue, never()).run();
    }

    @Test
    public void testNoConditionTrue() {
        Runnable bothTrue = mock(Runnable.class);
        Runnable firstTrue = mock(Runnable.class);
        Runnable secondTrue = mock(Runnable.class);
        Runnable noneTrue = mock(Runnable.class);

        If2.if2(false, false, bothTrue, firstTrue, secondTrue, noneTrue);

        verify(bothTrue, never()).run();
        verify(firstTrue, never()).run();
        verify(secondTrue, never()).run();
        verify(noneTrue, times(1)).run();
    }
}
