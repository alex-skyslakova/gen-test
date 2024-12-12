import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CommatizingNumbersTest {

    @Test
    public void testCommatizePi() {
        assertEquals("pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231",
                CommatizingNumbers.commatize("pi=3.14159265358979323846264338327950288419716939937510582097494459231", 6, 5, " "));
    }

    @Test
    public void testCommatizeZimbabweDollar() {
        assertEquals("The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).",
                CommatizingNumbers.commatize("The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", 0, 3, "."));
    }

    @Test
    public void testCommatizeAusDollar() {
        assertEquals("-in Aus$+1,411.8millions",
                CommatizingNumbers.commatize("-in Aus$+1411.8millions", 0, 3, ","));
    }

    @Test
    public void testCommatizeUSDollar() {
        assertEquals("===US$0017,440 millions=== (in 2000 dollars)",
                CommatizingNumbers.commatize("===US$0017440 millions=== (in 2000 dollars)", 0, 3, ","));
    }

    @Test
    public void testCommatizeBigNumber() {
        assertEquals("123.e8000 is pretty big.",
                CommatizingNumbers.commatize("123.e8000 is pretty big.", 0, 3, ","));
    }

    @Test
    public void testCommatizeEarthArea() {
        assertEquals("The land area of the earth is 57,268,900(29% of the surface) square miles.",
                CommatizingNumbers.commatize("The land area of the earth is 57268900(29% of the surface) square miles.", 0, 3, ","));
    }

    @Test
    public void testCommatizeNoNumbers() {
        assertEquals("Ain't no numbers in this here words, nohow, no way, Jose.",
                CommatizingNumbers.commatize("Ain't no numbers in this here words, nohow, no way, Jose.", 0, 3, ","));
    }

    @Test
    public void testCommatizeJames() {
        assertEquals("James was never known as 0000000007",
                CommatizingNumbers.commatize("James was never known as 0000000007", 0, 3, ","));
    }

    @Test
    public void testCommatizeEddingtonNumber() {
        assertEquals("Arthur Eddington wrote: I believe there are 15,747,724,136,275,002,577,605,653,961,181,555,468,044,717,914,527,116,709,366,231,425,076,185,631,031,296 protons in the universe.",
                CommatizingNumbers.commatize("Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.", 0, 3, ","));
    }

    @Test
    public void testCommatizeLeadingBlanks() {
        assertEquals("   $-140,000±100 millions.",
                CommatizingNumbers.commatize("   $-140000±100 millions.", 0, 3, ","));
    }

    @Test
    public void testCommatizeDate() {
        assertEquals("6/9/1946 was a good year for some.",
                CommatizingNumbers.commatize("6/9/1946 was a good year for some.", 0, 3, ","));
    }
}
