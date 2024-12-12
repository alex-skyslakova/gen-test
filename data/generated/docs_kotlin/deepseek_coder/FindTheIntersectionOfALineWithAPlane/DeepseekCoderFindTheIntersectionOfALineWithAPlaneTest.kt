import org.junit.Test
import kotlin.test.assertEquals

class Vector3DTest {

    @Test
    fun testVectorAddition() {
        val v1 = Vector3D(1.0, 2.0, 3.0)
        val v2 = Vector3D(4.0, 5.0, 6.0)
        val result = v1 + v2
        assertEquals(Vector3D(5.0, 7.0, 9.0), result)
    }

    @Test
    fun testVectorSubtraction() {
        val v1 = Vector3D(1.0, 2.0, 3.0)
        val v2 = Vector3D(4.0, 5.0, 6.0)
        val result = v1 - v2
        assertEquals(Vector3D(-3.0, -3.0, -3.0), result)
    }

    @Test
    fun testVectorScalarMultiplication() {
        val v = Vector3D(1.0, 2.0, 3.0)
        val scalar = 2.0
        val result = v * scalar
        assertEquals(Vector3D(2.0, 4.0, 6.0), result)
    }

    @Test
    fun testVectorDotProduct() {
        val v1 = Vector3D(1.0, 2.0, 3.0)
        val v2 = Vector3D(4.0, 5.0, 6.0)
        val result = v1 dot v2
        assertEquals(32.0, result)
    }
}

class IntersectPointTest {

    @Test
    fun testIntersectPoint() {
        val rv = Vector3D(0.0, -1.0, -1.0)
        val rp = Vector3D(0.0, 0.0, 10.0)
        val pn = Vector3D(0.0, 0.0, 1.0)
        val pp = Vector3D(0.0, 0.0, 5.0)
        val expected = Vector3D(0.0, -5.0, 5.0)
        val result = intersectPoint(rv, rp, pn, pp)
        assertEquals(expected, result)
    }

    @Test
    fun testIntersectPointParallelRay() {
        val rv = Vector3D(1.0, 0.0, 0.0)
        val rp = Vector3D(0.0, 0.0, 10.0)
        val pn = Vector3D(0.0, 0.0, 1.0)
        val pp = Vector3D(0.0, 0.0, 5.0)
        // The ray is parallel to the plane, so there should be no intersection
        // This test checks for a specific behavior, which might be an error or a special case
        val result = intersectPoint(rv, rp, pn, pp)
        // Assuming the function returns a point far away or null in this case
        // Here we check for a specific point far away
        assertEquals(Vector3D(0.0, 0.0, Double.POSITIVE_INFINITY), result)
    }

    @Test
    fun testIntersectPointSamePlane() {
        val rv = Vector3D(0.0, -1.0, -1.0)
        val rp = Vector3D(0.0, 0.0, 5.0)
        val pn = Vector3D(0.0, 0.0, 1.0)
        val pp = Vector3D(0.0, 0.0, 5.0)
        // The ray starts on the plane, so the intersection point should be the same as the ray point
        val result = intersectPoint(rv, rp, pn, pp)
        assertEquals(rp, result)
    }
}
