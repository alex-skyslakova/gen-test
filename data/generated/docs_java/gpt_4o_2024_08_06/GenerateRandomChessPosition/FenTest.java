import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FenTest {

    @Test
    public void testCreateFen() {
        String fen = Fen.createFen();
        assertNotNull(fen, "FEN string should not be null");
        assertTrue(fen.endsWith(" w - - 0 1"), "FEN string should end with ' w - - 0 1'");
    }

    @Test
    public void testKingPlacement() {
        for (int i = 0; i < 100; i++) { // Run multiple times to ensure randomness
            String fen = Fen.createFen();
            assertTrue(fen.contains("K"), "FEN string should contain a white king");
            assertTrue(fen.contains("k"), "FEN string should contain a black king");

            int whiteKingIndex = fen.indexOf('K');
            int blackKingIndex = fen.indexOf('k');

            int whiteKingRow = getRowFromIndex(whiteKingIndex);
            int whiteKingCol = getColFromIndex(whiteKingIndex);
            int blackKingRow = getRowFromIndex(blackKingIndex);
            int blackKingCol = getColFromIndex(blackKingIndex);

            assertFalse(areKingsAdjacent(whiteKingRow, whiteKingCol, blackKingRow, blackKingCol),
                    "Kings should not be on adjacent squares");
        }
    }

    @Test
    public void testPawnPlacement() {
        for (int i = 0; i < 100; i++) { // Run multiple times to ensure randomness
            String fen = Fen.createFen();
            String[] rows = fen.split(" ")[0].split("/");

            assertFalse(rows[0].contains("p"), "No black pawn should be on the first rank");
            assertFalse(rows[7].contains("P"), "No white pawn should be on the eighth rank");
        }
    }

    @Test
    public void testPieceCount() {
        for (int i = 0; i < 100; i++) { // Run multiple times to ensure randomness
            String fen = Fen.createFen();
            String[] rows = fen.split(" ")[0].split("/");

            int pieceCount = 0;
            for (String row : rows) {
                for (char ch : row.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        pieceCount++;
                    }
                }
            }
            assertTrue(pieceCount <= 32, "Total number of pieces should not exceed 32");
        }
    }

    private int getRowFromIndex(int index) {
        return index / 9; // 8 columns + 1 '/' per row
    }

    private int getColFromIndex(int index) {
        return index % 9; // 8 columns + 1 '/' per row
    }

    private boolean areKingsAdjacent(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) <= 1 && Math.abs(c1 - c2) <= 1;
    }
}
