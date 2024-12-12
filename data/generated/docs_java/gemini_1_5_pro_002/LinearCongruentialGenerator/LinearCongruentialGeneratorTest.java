import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class LinearCongruentialGeneratorTest {

    @Test
    void testRandBSD_seed0() {
        int[] expected = {12345, 1406932606, 1622650073, 984943658, 1144108930, 470211272, 101027544, 1457850878, 1458777923, 2007237709};
        assertArrayEquals(expected, LinearCongruentialGenerator.randBSD(0).limit(10).toArray());
    }

    @Test
    void testRandBSD_seed1() {
        int[] expected = {1103527590, 2087675682, 2015716764, 1068091242, 1481183058, 1784738562, 467761394, 889841788, 1527277794, 1776733102};
        assertArrayEquals(expected, LinearCongruentialGenerator.randBSD(1).limit(10).toArray());
    }

    @Test
    void testRandMS_seed0() {
        int[] expected = {12345, 20748, 10537, 29492, 26989, 16230, 5871, 23460, 9305, 14418};
        assertArrayEquals(expected, LinearCongruentialGenerator.randMS(0).limit(10).toArray());
    }

    @Test
    void testRandMS_seed1() {
        int[] expected = {21558, 19065, 30858, 8521, 25968, 15107, 28366, 14100, 771, 27413};
        assertArrayEquals(expected, LinearCongruentialGenerator.randMS(1).limit(10).toArray());
    }
}
