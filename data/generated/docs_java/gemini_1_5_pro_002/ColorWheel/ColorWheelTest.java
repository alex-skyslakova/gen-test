import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorWheelTest {

    @Test
    void testHsvToRgb_Red() {
        Color expected = new Color(255, 0, 0);
        Color actual = ColorWheel.hsvToRgb(0, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_Green() {
        Color expected = new Color(0, 255, 0);
        Color actual = ColorWheel.hsvToRgb(120, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_Blue() {
        Color expected = new Color(0, 0, 255);
        Color actual = ColorWheel.hsvToRgb(240, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_Yellow() {
        Color expected = new Color(255, 255, 0);
        Color actual = ColorWheel.hsvToRgb(60, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_Cyan() {
        Color expected = new Color(0, 255, 255);
        Color actual = ColorWheel.hsvToRgb(180, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_Magenta() {
        Color expected = new Color(255, 0, 255);
        Color actual = ColorWheel.hsvToRgb(300, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }


    @Test
    void testHsvToRgb_Black() {
        Color expected = new Color(0, 0, 0);
        Color actual = ColorWheel.hsvToRgb(0, 0.0, 0.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_White() {
        Color expected = new Color(255, 255, 255);
        Color actual = ColorWheel.hsvToRgb(0, 0.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }

    @Test
    void testHsvToRgb_Gray() {
        Color expected = new Color(127, 127, 127);
        Color actual = ColorWheel.hsvToRgb(0, 0.0, 0.5);
        assertEquals(expected.getRGB(), actual.getRGB());

    }


    @Test
    void testHsvToRgb_BoundaryHue360() {
        Color expected = ColorWheel.hsvToRgb(0, 1.0, 1.0); //equivalent to hue 0.
        Color actual = ColorWheel.hsvToRgb(360, 1.0, 1.0);
        assertEquals(expected.getRGB(), actual.getRGB());
    }
}

