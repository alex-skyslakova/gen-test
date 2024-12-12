import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.abs

class GenerateRandomChessPositionTest {

    @Test
    fun testPlaceKings() {
        // Ensure that exactly one white king ('K') and one black king ('k') are placed
        placeKings()
        var whiteKingCount = 0
        var blackKingCount = 0
        for (row in grid) {
            for (cell in row) {
                if (cell == 'K') whiteKingCount++
                if (cell == 'k') blackKingCount++
            }
        }
        assertEquals(1, whiteKingCount, "There should be exactly one white king")
        assertEquals(1, blackKingCount, "There should be exactly one black king")

        // Ensure that the kings are not placed on adjacent squares
        var whiteKingPos: Pair<Int, Int>? = null
        var blackKingPos: Pair<Int, Int>? = null
        for (r in 0..7) {
            for (c in 0..7) {
                if (grid[r][c] == 'K') whiteKingPos = Pair(r, c)
                if (grid[r][c] == 'k') blackKingPos = Pair(r, c)
            }
        }
        assertNotNull(whiteKingPos, "White king position should not be null")
        assertNotNull(blackKingPos, "Black king position should not be null")
        val (r1, c1) = whiteKingPos!!
        val (r2, c2) = blackKingPos!!
        assertTrue(abs(r1 - r2) > 1 || abs(c1 - c2) > 1, "Kings should not be placed on adjacent squares")
    }

    @Test
    fun testPlacePieces() {
        // Ensure that no pawns are placed on the promotion squares (1st and 8th rank)
        placePieces("PPPPPPPP", true)
        placePieces("pppppppp", true)
        for (c in 0..7) {
            assertNotEquals('P', grid[7][c], "No white pawn should be on the 8th rank")
            assertNotEquals('p', grid[0][c], "No black pawn should be on the 1st rank")
        }

        // Ensure that the total number of pieces does not exceed 32
        var pieceCount = 0
        for (row in grid) {
            for (cell in row) {
                if (cell != NUL) pieceCount++
            }
        }
        assertTrue(pieceCount <= 32, "Total number of pieces should not exceed 32")
    }

    @Test
    fun testToFen() {
        // Ensure that the FEN string ends with "w - - 0 1"
        val fen = toFen()
        assertTrue(fen.endsWith(" w - - 0 1"), "FEN string should end with 'w - - 0 1'")

        // Ensure that the FEN string is correctly formatted
        val fenParts = fen.split(" ")
        assertEquals(6, fenParts.size, "FEN string should have 6 parts")
        assertTrue(fenParts[1] == "w", "FEN string should indicate white's turn")
        assertTrue(fenParts[2] == "-", "FEN string should indicate no castling rights")
        assertTrue(fenParts[3] == "-", "FEN string should indicate no en passant possibility")
        assertEquals("0", fenParts[4], "FEN string should indicate 0 half-moves")
        assertEquals("1", fenParts[5], "FEN string should indicate 1 full-move")
    }

    @Test
    fun testCreateFen() {
        // Ensure that the createFen function returns a valid FEN string
        val fen = createFen()
        assertNotNull(fen, "FEN string should not be null")
        assertTrue(fen.isNotEmpty(), "FEN string should not be empty")
    }
}
