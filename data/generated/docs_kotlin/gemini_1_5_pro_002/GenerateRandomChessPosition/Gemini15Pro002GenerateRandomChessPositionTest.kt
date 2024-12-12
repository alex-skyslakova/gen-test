import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.regex.Pattern

class FenGeneratorTest {

    @Test
    fun testKingPlacement() {
        repeat(1000) { // Test a large number of times for randomness
            val fen = createFen()
            val rows = fen.split("/").toTypedArray()

            var whiteKingCount = 0
            var blackKingCount = 0
            var whiteKingPos = ""
            var blackKingPos = ""

            for (r in 0..7) {
                for (c in 0..7) {
                    if (rows[r][c] == 'K') {
                        whiteKingCount++
                        whiteKingPos = "" + r + c
                    }
                    if (rows[r][c] == 'k') {
                        blackKingCount++
                        blackKingPos = "" + r + c
                    }
                }
            }


            assertEquals(1, whiteKingCount)
            assertEquals(1, blackKingCount)

            val whiteKingR = whiteKingPos[0].digitToIntOrNull() ?: -1
            val whiteKingC = whiteKingPos[1].digitToIntOrNull() ?: -1
            val blackKingR = blackKingPos[0].digitToIntOrNull() ?: -1
            val blackKingC = blackKingPos[1].digitToIntOrNull() ?: -1

            assertTrue(abs(whiteKingR - blackKingR) > 1 || abs(whiteKingC - blackKingC) > 1, "Kings too close")
        }
    }

    @Test
    fun testNoPawnsOnPromotionRank() {
        repeat(1000) {
            val fen = createFen()
            val rows = fen.split("/").toTypedArray()
            assertFalse(rows[0].contains('P'))
            assertFalse(rows[7].contains('p'))
        }
    }

    @Test
    fun testFenFormat() {
        repeat(1000) {
            val fen = createFen()
            val pattern = Pattern.compile(
                "^([1-8prnbqkPRNBQK]{1,8}/){7}[1-8prnbqkPRNBQK]{1,8} w - - 0 1\$"
            )
            assertTrue(pattern.matcher(fen).matches())
        }

    }


    @Test
    fun testPieceCount() {
        repeat(1000) {
            val fen = createFen()
            val rows = fen.split("/").dropLast(1)
            var pieceCount = 0
            for (row in rows) {
                for (char in row) {
                    if (char.isLetter()) {
                        pieceCount++
                    } else if (char.isDigit()) {
                        pieceCount += char.digitToInt()
                    }
                }
            }

             assertTrue(pieceCount >=2 && pieceCount <=64)
        }

    }

    @Test
    fun testTurnAndFlags() {
        repeat(1000) {
            val fen = createFen()
            assertTrue(fen.endsWith(" w - - 0 1"))
        }
    }




}


