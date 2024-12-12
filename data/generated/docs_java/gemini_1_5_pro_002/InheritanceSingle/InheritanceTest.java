import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InheritanceTest {

    @Test
    public void testInheritance() {
        assertTrue(new Dog() instanceof Animal);
        assertTrue(new Cat() instanceof Animal);
        assertTrue(new Lab() instanceof Dog);
        assertTrue(new Lab() instanceof Animal);
        assertTrue(new Collie() instanceof Dog);
        assertTrue(new Collie() instanceof Animal);
    }

}
