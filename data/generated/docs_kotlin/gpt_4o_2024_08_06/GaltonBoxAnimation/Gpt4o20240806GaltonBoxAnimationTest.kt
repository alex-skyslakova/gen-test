import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GaltonBoxTest {

    @Test
    fun testInitializeBox() {
        initializeBox()
        
        // Check corners
        assertEquals(Cell.CORNER, box[0][0])
        assertEquals(Cell.CORNER, box[0][boxW - 1])
        assertEquals(Cell.CORNER, box[boxH - 1][0])
        assertEquals(Cell.CORNER, box[boxH - 1][boxW - 1])
        
        // Check floor
        for (i in 1 until boxW - 1) {
            assertEquals(Cell.FLOOR, box[0][i])
            assertEquals(Cell.FLOOR, box[boxH - 1][i])
        }
        
        // Check walls
        for (r in 1 until boxH - 1) {
            assertEquals(Cell.WALL, box[r][0])
            assertEquals(Cell.WALL, box[r][boxW - 1])
        }
        
        // Check pins
        for (nPins in 1..pinsBaseW) {
            for (pin in 0 until nPins) {
                assertEquals(Cell.PIN, box[boxH - 2 - nPins][centerH + 1 - nPins + pin * 2])
            }
        }
    }

    @Test
    fun testBallMovement() {
        initializeBox()
        val ball = Ball(centerH, boxH - 2)
        
        // Simulate a few steps
        repeat(5) {
            ball.doStep()
        }
        
        // Ensure the ball has moved downwards
        assertTrue(ball.y < boxH - 2)
    }

    @Test
    fun testBallDeflection() {
        initializeBox()
        val ball = Ball(centerH, boxH - 2)
        
        // Move the ball to a pin position
        while (box[ball.y - 1][ball.x] != Cell.PIN) {
            ball.doStep()
        }
        
        val initialX = ball.x
        ball.doStep()
        
        // Ensure the ball deflects left or right
        assertTrue(ball.x == initialX - 1 || ball.x == initialX + 1)
    }
}
