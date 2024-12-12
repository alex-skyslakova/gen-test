import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeepCopyTest {

    @Test
    public void testShallowCopy() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = p1;

        assertSame(p1, p2, "Shallow copy should reference the same object");

        p2.getAddress().setCity("New York");
        assertEquals("New York", p1.getAddress().getCity(), "City should be changed for both references in shallow copy");
    }

    @Test
    public void testCopyConstructor() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = new DeepCopy.Person(p1);

        assertNotSame(p1, p2, "Copy constructor should create a different object");
        assertNotSame(p1.getAddress(), p2.getAddress(), "Address should be a different object in deep copy");

        p2.getAddress().setCity("New York");
        assertNotEquals(p1.getAddress().getCity(), p2.getAddress().getCity(), "City should be different after modification in deep copy");
    }

    @Test
    public void testSerializationDeepCopy() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = (DeepCopy.Person) DeepCopy.deepCopy(p1);

        assertNotSame(p1, p2, "Serialization should create a different object");
        assertNotSame(p1.getAddress(), p2.getAddress(), "Address should be a different object in deep copy");

        p2.getAddress().setCity("New York");
        assertNotEquals(p1.getAddress().getCity(), p2.getAddress().getCity(), "City should be different after modification in deep copy");
    }

    @Test
    public void testCloning() {
        DeepCopy.Person p1 = new DeepCopy.Person("Clark", "Kent", new DeepCopy.Address("1 World Center", "Metropolis", "NY", "010101"));
        DeepCopy.Person p2 = (DeepCopy.Person) p1.clone();

        assertNotSame(p1, p2, "Cloning should create a different object");
        assertNotSame(p1.getAddress(), p2.getAddress(), "Address should be a different object in deep copy");

        p2.getAddress().setCity("New York");
        assertNotEquals(p1.getAddress().getCity(), p2.getAddress().getCity(), "City should be different after modification in deep copy");
    }
}
