import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinePlaneIntersectionTest {

    @Test
    public void testIntersection() {
        LinePlaneIntersection.Vector3D rayVector = new LinePlaneIntersection.Vector3D(0.0, -1.0, -1.0);
        LinePlaneIntersection.Vector3D rayPoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 10.0);
        LinePlaneIntersection.Vector3D planeNormal = new LinePlaneIntersection.Vector3D(0.0, 0.0, 1.0);
        LinePlaneIntersection.Vector3D planePoint = new LinePlaneIntersection.Vector3D(0.0, 0.0, 5.0);
        
        LinePlaneIntersection.Vector3D expectedIntersection = new LinePlaneIntersection.Vector3D(0.0, 0.0, 5.0);
        LinePlaneIntersection.Vector3D actualIntersection = LinePlaneIntersection.intersectPoint(rayVector, rayPoint, planeNormal, planePoint);
        
        assertEquals(expectedIntersection.toString(), actualIntersection.toString(), "The intersection point is incorrect.");
    }
}
