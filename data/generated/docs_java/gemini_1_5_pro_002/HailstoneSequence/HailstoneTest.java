import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HailstoneTest {

    @Test
    void testGetHailstoneSequence_validInput() {
        List<Long> sequence = Hailstone.getHailstoneSequence(27);
        assertEquals(112, sequence.size());
        assertEquals(27, sequence.get(0));
        assertEquals(82, sequence.get(1));
        assertEquals(41, sequence.get(2));
        assertEquals(124, sequence.get(3));
        assertEquals(1, sequence.get(sequence.size() - 1));

        sequence = Hailstone.getHailstoneSequence(1);
        assertEquals(1, sequence.size());
        assertEquals(1, sequence.get(0));

        sequence = Hailstone.getHailstoneSequence(2);
        assertEquals(2, sequence.size());
        assertEquals(2, sequence.get(0));
        assertEquals(1, sequence.get(1));
        
        sequence = Hailstone.getHailstoneSequence(4);
        assertEquals(3, sequence.size());
        assertEquals(4, sequence.get(0));
        assertEquals(2, sequence.get(1));
        assertEquals(1, sequence.get(2));
    }

    @Test
    void testGetHailstoneSequence_invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> Hailstone.getHailstoneSequence(0));
        assertThrows(IllegalArgumentException.class, () -> Hailstone.getHailstoneSequence(-1));
        assertThrows(IllegalArgumentException.class, () -> Hailstone.getHailstoneSequence(-27));
    }


}
