import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinePlaneIntersectionTest {

    private static class Vector3D {
        private double x, y, z;

        Vector3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        Vector3D plus(Vector3D v) {
            return new Vector3D(x + v.x, y + v.y, z + v.z);
        }

        Vector3D minus(Vector3D v) {
            return new Vector3D(x - v.x, y - v.y, z - v.z);
        }

        Vector3D times(double s) {
            return new Vector3D(s * x, s * y, s * z);
        }

        double dot(Vector3D v) {
            return x * v.x + y * v.y + z * v.z;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Vector3D other = (Vector3D) obj;
            return Math.abs(x - other.x) < 1e-6 && 
                   Math.abs(y - other.y) < 1e-6 &&
                   Math.abs(z - other.z) < 1e-6;
        }
    }

    private static Vector3D intersectPoint(Vector3D rayVector, Vector3D rayPoint, Vector3D planeNormal, Vector3D planePoint) {
        // Method under test, copied here for convenience in testing
        Vector3D diff = rayPoint.minus(planePoint);
        double prod1 = diff.dot(planeNormal);
        double prod2 = rayVector.dot(planeNormal);
        double prod3 = prod1 / prod2;
        return rayPoint.minus(rayVector.times(prod3));
    }


    @Test
    void testGivenExample() {
        Vector3D rv = new Vector3D(0.0, -1.0, -1.0);
        Vector3D rp = new Vector3D(0.0, 0.0, 10.0);
        Vector3D pn = new Vector3D(0.0, 0.0, 1.0);
        Vector3D pp = new Vector3D(0.0, 0.0, 5.0);
        Vector3D expected = new Vector3D(0.0, 5.0, 5.0);
        Vector3D ip = intersectPoint(rv, rp, pn, pp);
        assertEquals(expected, ip);
    }

    @Test
    void testParallelRay() {
        Vector3D rv = new Vector3D(0.0, 1.0, 0.0);
        Vector3D rp = new Vector3D(0.0, 0.0, 10.0);
        Vector3D pn = new Vector3D(0.0, 0.0, 1.0);
        Vector3D pp = new Vector3D(0.0, 0.0, 5.0);
        assertThrows(ArithmeticException.class, () -> intersectPoint(rv, rp, pn, pp)); // Expecting division by zero
    }

    @Test
    void testRayOriginOnPlane() {
        Vector3D rv = new Vector3D(0.0, -1.0, -1.0);
        Vector3D rp = new Vector3D(0.0, 0.0, 5.0);
        Vector3D pn = new Vector3D(0.0, 0.0, 1.0);
        Vector3D pp = new Vector3D(0.0, 0.0, 5.0);
        Vector3D expected = new Vector3D(0.0, 0.0, 5.0);
        Vector3D ip = intersectPoint(rv, rp, pn, pp);
        assertEquals(expected, ip);
    }


    @Test
    void testDifferentPlaneOrientation() {
        Vector3D rv = new Vector3D(1, 1, 1);
        Vector3D rp = new Vector3D(0, 0, 0);
        Vector3D pn = new Vector3D(1, 1, 1);
        Vector3D pp = new Vector3D(1, 0, 0);
        Vector3D expected = new Vector3D(2.0/3.0, 2.0/3.0, 2.0/3.0);
        Vector3D ip = intersectPoint(rv, rp, pn, pp);
        assertEquals(expected, ip);
    }


}
