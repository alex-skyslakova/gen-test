import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class If2Test {

    @Test
    void testBothConditionsTrue() {
        final boolean[] executed = new boolean[4];

        If2.if2(true, true,
                () -> executed[0] = true,
                () -> executed[1] = true,
                () -> executed[2] = true,
                () -> executed[3] = true);

        assertTrue(executed[0]);
        assertFalse(executed[1]);
        assertFalse(executed[2]);
        assertFalse(executed[3]);
    }

    @Test
    void testFirstConditionTrue() {
        final boolean[] executed = new boolean[4];

        If2.if2(true, false,
                () -> executed[0] = true,
                () -> executed[1] = true,
                () -> executed[2] = true,
                () -> executed[3] = true);

        assertFalse(executed[0]);
        assertTrue(executed[1]);
        assertFalse(executed[2]);
        assertFalse(executed[3]);
    }

    @Test
    void testSecondConditionTrue() {
        final boolean[] executed = new boolean[4];

        If2.if2(false, true,
                () -> executed[0] = true,
                () -> executed[1] = true,
                () -> executed[2] = true,
                () -> executed[3] = true);

        assertFalse(executed[0]);
        assertFalse(executed[1]);
        assertTrue(executed[2]);
        assertFalse(executed[3]);
    }

    @Test
    void testNoConditionTrue() {
        final boolean[] executed = new boolean[4];

        If2.if2(false, false,
                () -> executed[0] = true,
                () -> executed[1] = true,
                () -> executed[2] = true,
                () -> executed[3] = true);

        assertFalse(executed[0]);
        assertFalse(executed[1]);
        assertFalse(executed[2]);
        assertTrue(executed[3]);
    }
}
