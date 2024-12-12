import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IntersectionTest {

    @Test
    fun testGivenExample() {
        val rv = Vector3D(0.0, -1.0, -1.0)
        val rp = Vector3D(0.0,  0.0, 10.0)
        val pn = Vector3D(0.0,  0.0,  1.0)
        val pp = Vector3D(0.0,  0.0,  5.0)
        val ip = intersectPoint(rv, rp, pn, pp)
        assertEquals(Vector3D(0.0, 5.0, 5.0), ip)
    }

    @Test
    fun testParallelRay() {
        val rv = Vector3D(0.0, 1.0, 0.0)
        val rp = Vector3D(0.0,  0.0, 10.0)
        val pn = Vector3D(0.0,  0.0,  1.0)
        val pp = Vector3D(0.0,  0.0,  5.0)
        val ip = intersectPoint(rv, rp, pn, pp)
        assertEquals(Vector3D(0.0, 0.0, 10.0), ip) // Expect ray origin if no intersection found.
    }


    @Test
    fun testRayOriginOnPlane() {
        val rv = Vector3D(0.0, -1.0, -1.0)
        val rp = Vector3D(0.0,  0.0, 5.0)
        val pn = Vector3D(0.0,  0.0,  1.0)
        val pp = Vector3D(0.0,  0.0,  5.0)
        val ip = intersectPoint(rv, rp, pn, pp)
        assertEquals(Vector3D(0.0, 0.0, 5.0), ip)
    }


    @Test
    fun testRayOppositeDirection() {
        val rv = Vector3D(0.0, 1.0, 1.0)
        val rp = Vector3D(0.0, 0.0, 0.0)
        val pn = Vector3D(0.0, 0.0, 1.0)
        val pp = Vector3D(0.0, 0.0, 5.0)
        val ip = intersectPoint(rv, rp, pn, pp)
        assertEquals(Vector3D(0.0,-5.0,-5.0), ip)

    }

    @Test
    fun testNonUnitNormal() {
        val rv = Vector3D(0.0, -1.0, -1.0)
        val rp = Vector3D(0.0,  0.0, 10.0)
        val pn = Vector3D(0.0,  0.0,  2.0)
        val pp = Vector3D(0.0,  0.0,  5.0)
        val ip = intersectPoint(rv, rp, pn, pp)
        assertEquals(Vector3D(0.0, 5.0, 5.0), ip)
    }
}


