import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GenerateRandomChessPositionTest {

    @Test
    fun `test only one king of each color`() {
        val fen = createFen()
        val whiteKingCount = fen.count { it == 'K' }
        val blackKingCount = fen.count { it == 'k' }
        assertEquals(1, whiteKingCount, "There should be exactly one white king")
        assertEquals(1, blackKingCount, "There should be exactly one black king")
    }

    @Test
    fun `test kings are not adjacent`() {
        val fen = createFen()
        val board = fen.split(" ")[0].split("/")
        var whiteKingPosition: Pair<Int, Int>? = null
        var blackKingPosition: Pair<Int, Int>? = null

        for (r in board.indices) {
            var c = 0
            for (char in board[r]) {
                if (char.isDigit()) {
                    c += char.toString().toInt()
                } else {
                    if (char == 'K') whiteKingPosition = Pair(r, c)
                    if (char == 'k') blackKingPosition = Pair(r, c)
                    c++
                }
            }
        }

        assertNotNull(whiteKingPosition)
        assertNotNull(blackKingPosition)

        val (wr, wc) = whiteKingPosition!!
        val (br, bc) = blackKingPosition!!

        assertFalse(abs(wr - br) <= 1 && abs(wc - bc) <= 1, "Kings should not be on adjacent squares")
    }

    @Test
    fun `test no pawns in promotion squares`() {
        val fen = createFen()
        val board = fen.split(" ")[0].split("/")

        // Check 8th rank for white pawns
        assertFalse(board[0].contains('P'), "No white pawn should be on the 8th rank")

        // Check 1st rank for black pawns
        assertFalse(board[7].contains('p'), "No black pawn should be on the 1st rank")
    }

    @Test
    fun `test no more than 32 pieces`() {
        val fen = createFen()
        val board = fen.split(" ")[0].replace("/", "")
        val pieceCount = board.count { it.isLetter() }
        assertTrue(pieceCount <= 32, "There should be no more than 32 pieces on the board")
    }

    @Test
    fun `test FEN ends with correct suffix`() {
        val fen = createFen()
        assertTrue(fen.endsWith(" w - - 0 1"), "FEN should end with ' w - - 0 1'")
    }
}
