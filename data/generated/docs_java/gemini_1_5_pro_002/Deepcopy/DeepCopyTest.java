import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepCopyTest {

    @Test
    void testDeepCopy() {
        Person p1 = new Person("Clark", "Kent", new Address("1 World Center", "Metropolis", "NY", "010101"));
        Person p2 = (Person) DeepCopy.deepCopy(p1);

        assertNotNull(p2);
        assertNotSame(p1, p2);
        assertEquals(p1.getFirstName(), p2.getFirstName());
        assertEquals(p1.getLastName(), p2.getLastName());
        assertEquals(p1.getAddress().getCity(), p2.getAddress().getCity());

        p2.getAddress().setCity("New York");
        assertNotEquals(p1.getAddress().getCity(), p2.getAddress().getCity());
    }

    @Test
    void testDeepCopy_null() {
        Person p1 = null;
        Person p2 = (Person) DeepCopy.deepCopy(p1);
        assertNull(p2);
    }

    @Test
    void testDeepCopy_NonSerializable() {
        NonSerializableClass nonSerializable = new NonSerializableClass();
        assertThrows(Exception.class, () -> DeepCopy.deepCopy(nonSerializable));

    }



    @Test
    void testCopyConstructor() {
        Person p1 = new Person("Clark", "Kent", new Address("1 World Center", "Metropolis", "NY", "010101"));
        Person p2 = new Person(p1);


        assertNotSame(p1, p2);
        assertEquals(p1.getFirstName(), p2.getFirstName());
        assertEquals(p1.getLastName(), p2.getLastName());
        assertNotSame(p1.getAddress(), p2.getAddress()); // Verify deep copy for nested object
        assertEquals(p1.getAddress().getCity(), p2.getAddress().getCity());


        p2.getAddress().setCity("Gotham");
        assertNotEquals(p1.getAddress().getCity(), p2.getAddress().getCity());


        p2.setFirstName("Bruce");
        assertNotEquals(p1.getFirstName(),p2.getFirstName());
    }
    @Test
    void testClone(){
        Person p1 = new Person("Clark", "Kent", new Address("1 World Center", "Metropolis", "NY", "010101"));
        Person p2 = (Person) p1.clone();

        assertNotSame(p1, p2);
        assertEquals(p1.getFirstName(), p2.getFirstName());
        assertEquals(p1.getLastName(), p2.getLastName());
        assertNotSame(p1.getAddress(), p2.getAddress()); // Verify deep copy for nested object
        assertEquals(p1.getAddress().getCity(), p2.getAddress().getCity());


        p2.getAddress().setCity("Gotham");
        assertNotEquals(p1.getAddress().getCity(), p2.getAddress().getCity());


        p2.setFirstName("Bruce");
        assertNotEquals(p1.getFirstName(),p2.getFirstName());
    }


    private static class NonSerializableClass { }

}
