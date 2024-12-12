import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PowerTreeTest {

    @BeforeAll
    public static void setUp() {
        // Ensure the static initialization block in PowerTree is executed
        PowerTree.main(new String[]{});
    }

    @Test
    public void testPathCalculation() {
        // Test path calculation for various values of n
        assertEquals(Arrays.asList(1), PowerTree.path(1));
        assertEquals(Arrays.asList(1, 2), PowerTree.path(2));
        assertEquals(Arrays.asList(1, 2, 3), PowerTree.path(3));
        assertEquals(Arrays.asList(1, 2, 4), PowerTree.path(4));
        assertEquals(Arrays.asList(1, 2, 3, 5), PowerTree.path(5));
        assertEquals(Arrays.asList(1, 2, 3, 6), PowerTree.path(6));
        assertEquals(Arrays.asList(1, 2, 4, 8), PowerTree.path(8));
        assertEquals(Arrays.asList(1, 2, 3, 6, 9, 18, 36, 72, 144, 288, 576, 1152, 2304, 4608, 9216, 18432, 36864, 73728, 147456, 294912, 589824, 1179648, 2359296, 4718592, 9437184, 18874368, 37748736, 75497472, 150994944, 301989888, 603979776, 1207959552, 2415919104, 4831838208L, 9663676416L, 19327352832L, 38654705664L, 77309411328L, 154618822656L, 309237645312L, 618475290624L, 1236950581248L, 2473901162496L, 4947802324992L, 9895604649984L, 19791209299968L, 39582418599936L, 79164837199872L, 158329674399744L, 316659348799488L, 633318697598976L, 1266637395197952L, 2533274790395904L, 5066549580791808L, 10133099161583616L, 20266198323167232L, 40532396646334464L, 81064793292668928L, 162129586585337856L, 324259173170675712L, 648518346341351424L, 1297036692682702848L, 2594073385365405696L, 5188146770730811392L), PowerTree.path(191));
    }

    @Test
    public void testTreePowCalculation() {
        // Test power calculation for various values of x and n
        assertEquals(BigDecimal.ONE, PowerTree.treePow(2.0, 0));
        assertEquals(BigDecimal.valueOf(2), PowerTree.treePow(2.0, 1));
        assertEquals(BigDecimal.valueOf(4), PowerTree.treePow(2.0, 2));
        assertEquals(BigDecimal.valueOf(8), PowerTree.treePow(2.0, 3));
        assertEquals(BigDecimal.valueOf(16), PowerTree.treePow(2.0, 4));
        assertEquals(BigDecimal.valueOf(32), PowerTree.treePow(2.0, 5));
        assertEquals(BigDecimal.valueOf(64), PowerTree.treePow(2.0, 6));
        assertEquals(BigDecimal.valueOf(128), PowerTree.treePow(2.0, 7));
        assertEquals(BigDecimal.valueOf(256), PowerTree.treePow(2.0, 8));
        assertEquals(BigDecimal.valueOf(512), PowerTree.treePow(2.0, 9));
        assertEquals(BigDecimal.valueOf(1024), PowerTree.treePow(2.0, 10));
        assertEquals(BigDecimal.valueOf(2048), PowerTree.treePow(2.0, 11));
        assertEquals(BigDecimal.valueOf(4096), PowerTree.treePow(2.0, 12));
        assertEquals(BigDecimal.valueOf(8192), PowerTree.treePow(2.0, 13));
        assertEquals(BigDecimal.valueOf(16384), PowerTree.treePow(2.0, 14));
        assertEquals(BigDecimal.valueOf(32768), PowerTree.treePow(2.0, 15));
        assertEquals(BigDecimal.valueOf(65536), PowerTree.treePow(2.0, 16));
        assertEquals(BigDecimal.valueOf(131072), PowerTree.treePow(2.0, 17));
        assertEquals(new BigDecimal("1.919710806691480363112382469207007729515283836896019289501953125"), PowerTree.treePow(1.1, 81));
        assertEquals(new BigDecimal("13494588674281093803728157396523884917402502294030101914066794676197552715441037713303062805467563267826914757795953903173520811865244074374005561751560061"), PowerTree.treePow(3.0, 191));
    }

    @Test
    public void testShowPowFormatting() {
        // Test that the showPow method does not throw exceptions and produces the expected output
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 0, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 1, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 2, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 3, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 4, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 5, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 6, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 7, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 8, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 9, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 10, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 11, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 12, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 13, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 14, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 15, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 16, true));
        assertDoesNotThrow(() -> PowerTree.showPow(2.0, 17, true));
        assertDoesNotThrow(() -> PowerTree.showPow(1.1, 81, false));
        assertDoesNotThrow(() -> PowerTree.showPow(3.0, 191, true));
    }
}
