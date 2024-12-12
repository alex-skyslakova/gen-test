import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LinePlaneIntersectionTest {

    @Test
    public void testIntersectPoint() {
        // Given
        LinePlaneIntersection.Vector3D rayVector = new LinePlaneIntersection.Vector3D(0.0, -1.0, -1.0);
        LinePlaneIntersection.Vector3D rayPoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 10.0);
        LinePlaneIntersection.Vector3D planeNormal = new LinePlaneIntersection.Vector3D(0.0, 0.0, 1.0);
        LinePlaneIntersection.Vector3D planePoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 5.0);

        // When
        LinePlaneIntersection.Vector3D intersectionPoint = LinePlaneIntersection.intersectPoint(rayVector, rayPoint, planeNormal, planePoint);

        // Then
        assertEquals(0.0, intersectionPoint.x, 0.0001);
        assertEquals(5.0, intersectionPoint.y, 0.0001);
        assertEquals(5.0, intersectionPoint.z, 0.0001);
    }

    @Test
    public void testIntersectPointParallelRay() {
        // Given
        LinePlaneIntersection.Vector3D rayVector = new LinePlaneIntersection.Vector3D(1.0, 0.0, 0.0);
        LinePlaneIntersection.Vector3D rayPoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 10.0);
        LinePlaneIntersection.Vector3D planeNormal = new LinePlaneIntersection.Vector3D(0.0, 0.0, 1.0);
        LinePlaneIntersection.Vector3D planePoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 5.0);

        // When
        LinePlaneIntersection.Vector3D intersectionPoint = LinePlaneIntersection.intersectPoint(rayVector, rayPoint, planeNormal, planePoint);

        // Then
        // Since the ray is parallel to the plane, the intersection point should be at infinity
        // We can't directly test for infinity, but we can check if the result is consistent with the expected behavior
        assertTrue(Double.isNaN(intersectionPoint.x) || Double.isNaN(intersectionPoint.y) || Double.isNaN(intersectionPoint.z));
    }

    @Test
    public void testIntersectPointCoincidentRay() {
        // Given
        LinePlaneIntersection.Vector3D rayVector = new LinePlaneIntersection.Vector3D(0.0, -1.0, -1.0);
        LinePlaneIntersection.Vector3D rayPoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 5.0);
        LinePlaneIntersection.Vector3D planeNormal = new LinePlaneIntersection.Vector3D(0.0, 0.0, 1.0);
        LinePlaneIntersection.Vector3D planePoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 5.0);

        // When
        LinePlaneIntersection.Vector3D intersectionPoint = LinePlaneIntersection.intersectPoint(rayVector, rayPoint, planeNormal, planePoint);

        // Then
        // Since the ray starts on the plane, the intersection point should be the same as the ray point
        assertEquals(0.0, intersectionPoint.x, 0.0001);
        assertEquals(0.0, intersectionPoint.y, 0.0001);
        assertEquals(5.0, intersectionPoint.z, 0.0001);
    }
}
