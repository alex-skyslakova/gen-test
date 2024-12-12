import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;

public class FenTest {

    @Test
    void testCreateFen_KingPlacement() {
        for (int i = 0; i < 100; i++) { // Run multiple times to increase confidence in random placement
            String fen = Fen.createFen();
            assertTrue(fen.contains("K"));
            assertTrue(fen.contains("k"));
            assertFalse(areKingsAdjacent(fen));
        }
    }
    
    @Test
    void testCreateFen_NoPawnsOnPromotionRank(){
        for(int i = 0; i < 100; i++){
            String fen = Fen.createFen();
            assertFalse(fen.matches(".*8/P.*"));
            assertFalse(fen.matches(".*p/1.*"));
        }
    }

    @Test
    void testCreateFen_ValidFormat() {
        for (int i = 0; i < 100; i++) {
            String fen = Fen.createFen();
            String[] parts = fen.split(" ");
            assertEquals(6, parts.length);
            assertTrue(Pattern.matches("[rnbqkpRNBQKP1-8/]+", parts[0]));
            assertEquals("w", parts[1]);
            assertEquals("-", parts[2]);
            assertEquals("-", parts[3]);
            assertEquals("0", parts[4]);
            assertEquals("1", parts[5]);
        }
    }



    private boolean areKingsAdjacent(String fen) {
        String[] ranks = fen.split(" ")[0].split("/");
        int kRow = -1, kCol = -1, KRow = -1, KCol = -1;

        for (int i = 0; i < 8; i++) {
            String rank = ranks[i];
            for (int j = 0; j < rank.length(); j++) {
                char c = rank.charAt(j);
                int col = 0;

                if(Character.isDigit(c)){
                  col += c-'0';
                } else {
                  if (c == 'k') {
                    kRow = i;
                    kCol = col;
                  } else if (c == 'K') {
                    KRow = i;
                    KCol = col;
                  }
                  col++;
                }
                
            }
            
        }


        if (kRow != -1 && KRow != -1) {
            return Math.abs(kRow - KRow) <= 1 && Math.abs(kCol - KCol) <= 1;
        }
        return false; // Should not happen if FEN is valid
    }
}
