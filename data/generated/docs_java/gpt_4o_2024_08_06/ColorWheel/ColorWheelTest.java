import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorWheelTest {

    @Test
    public void testHsvToRgbRed() {
        Color expected = new Color(255, 0, 0);
        Color actual = ColorWheel.hsvToRgb(0, 1.0, 1.0);
        assertEquals(expected, actual, "HSV to RGB conversion failed for red color");
    }

    @Test
    public void testHsvToRgbGreen() {
        Color expected = new Color(0, 255, 0);
        Color actual = ColorWheel.hsvToRgb(120, 1.0, 1.0);
        assertEquals(expected, actual, "HSV to RGB conversion failed for green color");
    }

    @Test
    public void testHsvToRgbBlue() {
        Color expected = new Color(0, 0, 255);
        Color actual = ColorWheel.hsvToRgb(240, 1.0, 1.0);
        assertEquals(expected, actual, "HSV to RGB conversion failed for blue color");
    }

    @Test
    public void testHsvToRgbWhite() {
        Color expected = new Color(255, 255, 255);
        Color actual = ColorWheel.hsvToRgb(0, 0.0, 1.0);
        assertEquals(expected, actual, "HSV to RGB conversion failed for white color");
    }

    @Test
    public void testHsvToRgbBlack() {
        Color expected = new Color(0, 0, 0);
        Color actual = ColorWheel.hsvToRgb(0, 0.0, 0.0);
        assertEquals(expected, actual, "HSV to RGB conversion failed for black color");
    }

    @Test
    public void testHsvToRgbGray() {
        Color expected = new Color(128, 128, 128);
        Color actual = ColorWheel.hsvToRgb(0, 0.0, 0.5);
        assertEquals(expected, actual, "HSV to RGB conversion failed for gray color");
    }
}
