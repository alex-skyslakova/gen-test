import kotlin.test.Test
import kotlin.test.assertEquals

class FindTheIntersectionOfALineWithAPlaneTest {

    @Test
    fun testIntersectionPoint() {
        val rayVector = Vector3D(0.0, -1.0, -1.0)
        val rayPoint = Vector3D(0.0, 0.0, 10.0)
        val planeNormal = Vector3D(0.0, 0.0, 1.0)
        val planePoint = Vector3D(0.0, 0.0, 5.0)

        val expectedIntersection = Vector3D(0.0, -5.0, 5.0)
        val actualIntersection = intersectPoint(rayVector, rayPoint, planeNormal, planePoint)

        assertEquals(expectedIntersection, actualIntersection, "The intersection point is incorrect.")
    }

    @Test
    fun testParallelRayAndPlane() {
        val rayVector = Vector3D(0.0, 0.0, 1.0)
        val rayPoint = Vector3D(0.0, 0.0, 10.0)
        val planeNormal = Vector3D(0.0, 0.0, 1.0)
        val planePoint = Vector3D(0.0, 0.0, 5.0)

        try {
            intersectPoint(rayVector, rayPoint, planeNormal, planePoint)
            assert(false) { "Expected an exception for parallel ray and plane." }
        } catch (e: ArithmeticException) {
            assert(true)
        }
    }

    @Test
    fun testRayOriginOnPlane() {
        val rayVector = Vector3D(0.0, -1.0, -1.0)
        val rayPoint = Vector3D(0.0, 0.0, 5.0)
        val planeNormal = Vector3D(0.0, 0.0, 1.0)
        val planePoint = Vector3D(0.0, 0.0, 5.0)

        val expectedIntersection = Vector3D(0.0, 0.0, 5.0)
        val actualIntersection = intersectPoint(rayVector, rayPoint, planeNormal, planePoint)

        assertEquals(expectedIntersection, actualIntersection, "The intersection point is incorrect.")
    }
}
