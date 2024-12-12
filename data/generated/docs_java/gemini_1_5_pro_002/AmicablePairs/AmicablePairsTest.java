import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

public class AmicablePairsTest {

    @Test
    void testProperDivsSum() {
        assertEquals(1, AmicablePairs.properDivsSum(2));
        assertEquals(6, AmicablePairs.properDivsSum(6));
        assertEquals(28, AmicablePairs.properDivsSum(28));
        assertEquals(1210, AmicablePairs.properDivsSum(1184));
        assertEquals(1184, AmicablePairs.properDivsSum(1210));
        assertEquals(2924, AmicablePairs.properDivsSum(284));
        assertEquals(1, AmicablePairs.properDivsSum(1));

    }



    @Test
    void testAmicablePairsSmallRange() {
        int limit = 300;
        Map<Long, Long> map = LongStream.rangeClosed(1, limit)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), AmicablePairs::properDivsSum));

        StringBuilder sb = new StringBuilder();
        LongStream.rangeClosed(1, limit)
                .forEach(n -> {
                    long m = map.get(n);
                    if (m > n && m <= limit && map.get(m) == n)
                        sb.append(String.format("%s %s ", n, m));
                });

        assertEquals("220 284 ", sb.toString());


    }
     @Test
    void testAmicablePairsNoPairs() {
        int limit = 220;
        Map<Long, Long> map = LongStream.rangeClosed(1, limit)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), AmicablePairs::properDivsSum));

        StringBuilder sb = new StringBuilder();
        LongStream.rangeClosed(1, limit)
                .forEach(n -> {
                    long m = map.get(n);
                    if (m > n && m <= limit && map.get(m) == n)
                        sb.append(String.format("%s %s ", n, m));
                });

        assertEquals("", sb.toString());


    }

}
