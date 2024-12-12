import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeepCopyTest {

    @Test
    public void testShallowCopy() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = p1;

        // Assert that p1 and p2 are the same object
        assertSame(p1, p2);

        // Modify p2 and check if p1 is also modified
        p2.getAddress().setCity("New York");
        assertEquals("New York", p1.getAddress().getCity());
    }

    @Test
    public void testCopyConstructor() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = new DeepCopy.Person(p1);

        // Assert that p1 and p2 are different objects
        assertNotSame(p1, p2);

        // Modify p2 and check if p1 remains unchanged
        p2.getAddress().setCity("New York");
        assertNotEquals("New York", p1.getAddress().getCity());
    }

    @Test
    public void testSerializationDeepCopy() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = (DeepCopy.Person) DeepCopy.deepCopy(p1);

        // Assert that p1 and p2 are different objects
        assertNotSame(p1, p2);

        // Modify p2 and check if p1 remains unchanged
        p2.getAddress().setCity("New York");
        assertNotEquals("New York", p1.getAddress().getCity());
    }

    @Test
    public void testCloning() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = (DeepCopy.Person) p1.clone();

        // Assert that p1 and p2 are different objects
        assertNotSame(p1, p2);

        // Modify p2 and check if p1 remains unchanged
        p2.getAddress().setCity("New York");
        assertNotEquals("New York", p1.getAddress().getCity());
    }
}
