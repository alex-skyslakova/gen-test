import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach


class GaltonBoxTest {

    @BeforeEach
    fun setup() {
        initializeBox()
    }

    @Test
    fun testInitializeBox_checkWalls() {
        for (r in 1 until boxH - 1) {
            assertEquals(Cell.WALL, box[r][0])
            assertEquals(Cell.WALL, box[r][boxW - 1])
        }
    }

    @Test
    fun testInitializeBox_checkFloorCeiling() {
        assertEquals(Cell.CORNER, box[0][0])
        assertEquals(Cell.CORNER, box[0][boxW - 1])
        for (i in 1 until boxW - 1) {
            assertEquals(Cell.FLOOR, box[0][i])
            assertEquals(Cell.FLOOR, box[boxH - 1][i])
        }
    }

    @Test
    fun testInitializeBox_checkPins() {
        for (nPins in 1..pinsBaseW) {
            for (pin in 0 until nPins) {
                assertEquals(Cell.PIN, box[boxH - 2 - nPins][centerH + 1 - nPins + pin * 2])
            }
        }
    }


    @Test
    fun testBall_doStep_emptyCell() {
        val ball = Ball(centerH, boxH - 2)
        ball.doStep()
        assertEquals(Cell.BALL, box[boxH - 3][centerH])
        assertEquals(Cell.EMPTY, box[boxH - 2][centerH])
    }

    @Test
    fun testBall_doStep_pinCell_bothSidesEmpty() {
        val ball = Ball(centerH, boxH - 3) // Positioned above a pin

        // Mock random to force left and right movement
        val mockRandom = object : Random() {
            override fun nextInt(bound: Int): Int = if (bound == 2) 0 else super.nextInt(bound) //Force Left
        }
        rand = mockRandom
        ball.doStep()
        assertEquals(Cell.BALL, box[boxH - 4][centerH -1])
        assertEquals(Cell.EMPTY, box[boxH - 3][centerH])

        box[boxH-4][centerH-1] = Cell.EMPTY //Reset

        mockRandom = object : Random() {
            override fun nextInt(bound: Int): Int = if (bound == 2) 1 else super.nextInt(bound) //Force Right
        }

        rand = mockRandom
        ball = Ball(centerH, boxH - 3)
        ball.doStep()
        assertEquals(Cell.BALL, box[boxH - 4][centerH + 1])
        assertEquals(Cell.EMPTY, box[boxH - 3][centerH])
    }


    @Test
    fun testBall_doStep_pinCell_oneSideEmpty_Left() {
        val ball = Ball(centerH, boxH - 3) // Positioned above a pin
        box[boxH-4][centerH+1] = Cell.BALL // Block right side

        ball.doStep()
        assertEquals(Cell.BALL, box[boxH - 4][centerH - 1])
        assertEquals(Cell.EMPTY, box[boxH - 3][centerH])
    }

    @Test
    fun testBall_doStep_pinCell_oneSideEmpty_Right() {
        val ball = Ball(centerH, boxH - 3) // Positioned above a pin
        box[boxH-4][centerH-1] = Cell.BALL // Block left side

        ball.doStep()
        assertEquals(Cell.BALL, box[boxH - 4][centerH+1])
        assertEquals(Cell.EMPTY, box[boxH - 3][centerH])
    }


    @Test
    fun testBall_doStep_ballAtBottom() {
        val ball = Ball(centerH, 1) // Ball near the bottom
        ball.doStep()

        assertEquals(Cell.BALL, box[0][centerH]) // Should stack on the bottom
        assertEquals(Cell.EMPTY, box[1][centerH]) 
    }

    @Test
    fun testBall_doStep_reachedBottom() {
        val ball = Ball(centerH, 0) // Ball at the bottom
        ball.doStep()
        assertEquals(Cell.BALL, box[0][centerH]) // Stays at the bottom
    }

}


