import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class LeftFacTest {

    @Test
    public void testFactorial() {
        assertEquals(BigInteger.ONE, LeftFac.factorial(BigInteger.ZERO));
        assertEquals(BigInteger.ONE, LeftFac.factorial(BigInteger.ONE));
        assertEquals(new BigInteger("2"), LeftFac.factorial(new BigInteger("2")));
        assertEquals(new BigInteger("6"), LeftFac.factorial(new BigInteger("3")));
        assertEquals(new BigInteger("24"), LeftFac.factorial(new BigInteger("4")));
        assertEquals(new BigInteger("120"), LeftFac.factorial(new BigInteger("5")));
    }

    @Test
    public void testLeftFact() {
        assertEquals(BigInteger.ZERO, LeftFac.leftFact(BigInteger.ZERO));
        assertEquals(BigInteger.ONE, LeftFac.leftFact(BigInteger.ONE));
        assertEquals(new BigInteger("2"), LeftFac.leftFact(new BigInteger("2")));
        assertEquals(new BigInteger("4"), LeftFac.leftFact(new BigInteger("3")));
        assertEquals(new BigInteger("10"), LeftFac.leftFact(new BigInteger("4")));
        assertEquals(new BigInteger("34"), LeftFac.leftFact(new BigInteger("5")));
    }

    @Test
    public void testLeftFactZeroThroughTen() {
        assertEquals(BigInteger.ZERO, LeftFac.leftFact(BigInteger.ZERO));
        assertEquals(BigInteger.ONE, LeftFac.leftFact(BigInteger.ONE));
        assertEquals(new BigInteger("2"), LeftFac.leftFact(new BigInteger("2")));
        assertEquals(new BigInteger("4"), LeftFac.leftFact(new BigInteger("3")));
        assertEquals(new BigInteger("10"), LeftFac.leftFact(new BigInteger("4")));
        assertEquals(new BigInteger("34"), LeftFac.leftFact(new BigInteger("5")));
        assertEquals(new BigInteger("154"), LeftFac.leftFact(new BigInteger("6")));
        assertEquals(new BigInteger("874"), LeftFac.leftFact(new BigInteger("7")));
        assertEquals(new BigInteger("5914"), LeftFac.leftFact(new BigInteger("8")));
        assertEquals(new BigInteger("46234"), LeftFac.leftFact(new BigInteger("9")));
        assertEquals(new BigInteger("409114"), LeftFac.leftFact(new BigInteger("10")));
    }

    @Test
    public void testLeftFactTwentyThroughHundredTen() {
        assertEquals(new BigInteger("128425485935180314"), LeftFac.leftFact(new BigInteger("20")));
        assertEquals(new BigInteger("9157958657951075573395300940314"), LeftFac.leftFact(new BigInteger("30")));
        assertEquals(new BigInteger("20935051082417771847631371547939998232420940314"), LeftFac.leftFact(new BigInteger("40")));
        assertEquals(new BigInteger("620960027832821612639424806694551108812720525606160920420940314"), LeftFac.leftFact(new BigInteger("50")));
        assertEquals(new BigInteger("141074930726669571000530822087000522211656242116439949000980378746128920420940314"), LeftFac.leftFact(new BigInteger("60")));
        assertEquals(new BigInteger("173639511802987526699717162409282876065556519849603157850853034644815111221599509216528920420940314"), LeftFac.leftFact(new BigInteger("70")));
        assertEquals(new BigInteger("906089587987695346534516804650290637694024830011956365184327674619752094289696314882008531991840922336528920420940314"), LeftFac.leftFact(new BigInteger("80")));
        assertEquals(new BigInteger("16695570072624210767034167688394623360733515163575864136345910335924039962404869510225723072235842668787507993136908442336528920420940314"), LeftFac.leftFact(new BigInteger("90")));
        assertEquals(new BigInteger("942786239765826579160595268206839381354754349601050974345395410407078230249590414458830117442618180732911203520208889371641659121356556442336528920420940314"), LeftFac.leftFact(new BigInteger("100")));
        assertEquals(new BigInteger("145722981061585297004706728001906071948635199234860720988658042536179281328615541936083296163475394237524337422204397431927131629058103519228197429698252556442336528920420940314"), LeftFac.leftFact(new BigInteger("110")));
    }

    @Test
    public void testLeftFactLengthForThousands() {
        assertEquals(2565, LeftFac.leftFact(new BigInteger("1000")).toString().length());
        assertEquals(5733, LeftFac.leftFact(new BigInteger("2000")).toString().length());
        assertEquals(9128, LeftFac.leftFact(new BigInteger("3000")).toString().length());
        assertEquals(12670, LeftFac.leftFact(new BigInteger("4000")).toString().length());
        assertEquals(16322, LeftFac.leftFact(new BigInteger("5000")).toString().length());
        assertEquals(20062, LeftFac.leftFact(new BigInteger("6000")).toString().length());
        assertEquals(23875, LeftFac.leftFact(new BigInteger("7000")).toString().length());
        assertEquals(27749, LeftFac.leftFact(new BigInteger("8000")).toString().length());
        assertEquals(31678, LeftFac.leftFact(new BigInteger("9000")).toString().length());
        assertEquals(35656, LeftFac.leftFact(new BigInteger("10000")).toString().length());
    }
}
