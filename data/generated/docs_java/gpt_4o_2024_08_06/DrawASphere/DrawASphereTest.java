import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testNormalize() {
        double[] vector = {3, 4, 0};
        Sphere.normalize(vector);
        assertEquals(1.0, Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]), 0.0001);
    }

    @Test
    void testDotProductPositive() {
        double[] vector1 = {1, 0, 0};
        double[] vector2 = {1, 0, 0};
        assertEquals(1.0, Sphere.dot(vector1, vector2), 0.0001);
    }

    @Test
    void testDotProductNegative() {
        double[] vector1 = {1, 0, 0};
        double[] vector2 = {-1, 0, 0};
        assertEquals(0.0, Sphere.dot(vector1, vector2), 0.0001);
    }

    @Test
    void testDotProductZero() {
        double[] vector1 = {1, 0, 0};
        double[] vector2 = {0, 1, 0};
        assertEquals(0.0, Sphere.dot(vector1, vector2), 0.0001);
    }

    @Test
    void testDrawSphere() {
        // Since drawSphere prints to the console, we can't directly test the output.
        // However, we can test that the method runs without exceptions for valid inputs.
        assertDoesNotThrow(() -> Sphere.drawSphere(20, 4, 0.1));
        assertDoesNotThrow(() -> Sphere.drawSphere(10, 2, 0.4));
    }

    @Test
    void testDrawSphereInvalidRadius() {
        // Test with invalid radius
        assertThrows(IllegalArgumentException.class, () -> Sphere.drawSphere(-1, 4, 0.1));
    }

    @Test
    void testDrawSphereInvalidAmbient() {
        // Test with invalid ambient light
        assertThrows(IllegalArgumentException.class, () -> Sphere.drawSphere(10, 2, -0.1));
        assertThrows(IllegalArgumentException.class, () -> Sphere.drawSphere(10, 2, 1.1));
    }

    @Test
    void testDrawSphereInvalidK() {
        // Test with invalid k value
        assertThrows(IllegalArgumentException.class, () -> Sphere.drawSphere(10, -1, 0.1));
    }
}
