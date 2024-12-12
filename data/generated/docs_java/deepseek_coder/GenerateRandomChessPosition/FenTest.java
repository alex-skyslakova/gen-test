import static org.junit.Assert.*;
import org.junit.Test;

public class FenTest {

    @Test
    public void testCreateFen() {
        String fen = Fen.createFen();
        
        // Check if the FEN string is not null or empty
        assertNotNull(fen);
        assertFalse(fen.isEmpty());
        
        // Check if the FEN string ends with " w - - 0 1"
        assertTrue(fen.endsWith(" w - - 0 1"));
        
        // Check if there is exactly one white king and one black king
        int whiteKingCount = countOccurrences(fen, 'K');
        int blackKingCount = countOccurrences(fen, 'k');
        assertEquals(1, whiteKingCount);
        assertEquals(1, blackKingCount);
        
        // Check if there are no pawns on the promotion squares
        assertFalse(fen.contains("P/") || fen.contains("/P"));
        assertFalse(fen.contains("p/") || fen.contains("/p"));
        
        // Check if the total number of pieces does not exceed 32
        int pieceCount = fen.replaceAll("[0-9/]", "").length();
        assertTrue(pieceCount <= 32);
    }

    @Test
    public void testPlaceKings() {
        char[][] grid = new char[8][8];
        Fen.placeKings(grid);
        
        // Check if there is exactly one white king and one black king
        int whiteKingCount = 0;
        int blackKingCount = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] == 'K') whiteKingCount++;
                if (grid[r][c] == 'k') blackKingCount++;
            }
        }
        assertEquals(1, whiteKingCount);
        assertEquals(1, blackKingCount);
        
        // Check if the kings are not on adjacent squares
        int r1 = -1, c1 = -1, r2 = -1, c2 = -1;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] == 'K') {
                    r1 = r;
                    c1 = c;
                }
                if (grid[r][c] == 'k') {
                    r2 = r;
                    c2 = c;
                }
            }
        }
        assertTrue(Math.abs(r1 - r2) > 1 || Math.abs(c1 - c2) > 1);
    }

    @Test
    public void testPlacePieces() {
        char[][] grid = new char[8][8];
        Fen.placeKings(grid);
        
        // Place some pieces and check if they are placed correctly
        Fen.placePieces(grid, "PPPPPPPP", true);
        Fen.placePieces(grid, "pppppppp", true);
        Fen.placePieces(grid, "RNBQBNR", false);
        Fen.placePieces(grid, "rnbqbnr", false);
        
        // Check if there are no pawns on the promotion squares
        for (int c = 0; c < 8; c++) {
            assertFalse(grid[0][c] == 'P');
            assertFalse(grid[7][c] == 'p');
        }
        
        // Check if the total number of pieces does not exceed 32
        int pieceCount = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] != 0) pieceCount++;
            }
        }
        assertTrue(pieceCount <= 32);
    }

    @Test
    public void testToFen() {
        char[][] grid = new char[8][8];
        Fen.placeKings(grid);
        Fen.placePieces(grid, "PPPPPPPP", true);
        Fen.placePieces(grid, "pppppppp", true);
        Fen.placePieces(grid, "RNBQBNR", false);
        Fen.placePieces(grid, "rnbqbnr", false);
        
        String fen = Fen.toFen(grid);
        
        // Check if the FEN string is not null or empty
        assertNotNull(fen);
        assertFalse(fen.isEmpty());
        
        // Check if the FEN string ends with " w - - 0 1"
        assertTrue(fen.endsWith(" w - - 0 1"));
        
        // Check if the FEN string is correctly formatted
        String[] parts = fen.split(" ");
        assertEquals(6, parts.length);
        assertEquals("w", parts[1]);
        assertEquals("-", parts[2]);
        assertEquals("-", parts[3]);
        assertEquals("0", parts[4]);
        assertEquals("1", parts[5]);
    }

    private int countOccurrences(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ch) count++;
        }
        return count;
    }
}
