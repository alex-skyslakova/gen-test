import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class ChaosGameTest {

    private ChaosGame chaosGame;

    @BeforeEach
    public void setUp() {
        chaosGame = new ChaosGame();
    }

    @Test
    public void testInitialPoints() {
        Point[] points = chaosGame.points;
        assertEquals(3, points.length);

        // Check the initial points of the equilateral triangle
        assertEquals(320, points[0].x);
        assertEquals(60, points[0].y);

        assertEquals(60, points[1].x);
        assertEquals(580, points[1].y);

        assertEquals(620, points[2].x);
        assertEquals(580, points[2].y);
    }

    @Test
    public void testInitialStack() {
        Stack<ChaosGame.ColoredPoint> stack = chaosGame.stack;
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        ChaosGame.ColoredPoint initialPoint = stack.peek();
        assertEquals(-1, initialPoint.x);
        assertEquals(-1, initialPoint.y);
        assertEquals(0, initialPoint.colorIndex);
    }

    @Test
    public void testAddPoint() {
        Stack<ChaosGame.ColoredPoint> stack = chaosGame.stack;
        int initialSize = stack.size();

        chaosGame.addPoint();

        assertEquals(initialSize + 1, stack.size());
    }

    @Test
    public void testHalfwayPoint() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 10);
        int colorIndex = 1;

        ChaosGame.ColoredPoint halfway = chaosGame.halfwayPoint(a, b, colorIndex);

        assertEquals(5, halfway.x);
        assertEquals(5, halfway.y);
        assertEquals(colorIndex, halfway.colorIndex);
    }

    @Test
    public void testDrawPoints() {
        // Mock Graphics2D to capture the drawing operations
        Graphics2D g = new Graphics2D() {
            @Override
            public void draw(Shape s) {}
            @Override
            public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) { return false; }
            @Override
            public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {}
            @Override
            public void drawRenderedImage(RenderedImage img, AffineTransform xform) {}
            @Override
            public void drawRenderableImage(RenderableImage img, AffineTransform xform) {}
            @Override
            public void drawString(String str, int x, int y) {}
            @Override
            public void drawString(String str, float x, float y) {}
            @Override
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {}
            @Override
            public void drawString(AttributedCharacterIterator iterator, float x, float y) {}
            @Override
            public void drawGlyphVector(GlyphVector g, float x, float y) {}
            @Override
            public void fill(Shape s) {}
            @Override
            public boolean hit(Rectangle rect, Shape s, boolean onStroke) { return false; }
            @Override
            public GraphicsConfiguration getDeviceConfiguration() { return null; }
            @Override
            public void setComposite(Composite comp) {}
            @Override
            public void setPaint(Paint paint) {}
            @Override
            public void setStroke(Stroke s) {}
            @Override
            public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {}
            @Override
            public Object getRenderingHint(RenderingHints.Key hintKey) { return null; }
            @Override
            public void setRenderingHints(Map<?, ?> hints) {}
            @Override
            public void addRenderingHints(Map<?, ?> hints) {}
            @Override
            public RenderingHints getRenderingHints() { return null; }
            @Override
            public void translate(int x, int y) {}
            @Override
            public void translate(double tx, double ty) {}
            @Override
            public void rotate(double theta) {}
            @Override
            public void rotate(double theta, double x, double y) {}
            @Override
            public void scale(double sx, double sy) {}
            @Override
            public void shear(double shx, double shy) {}
            @Override
            public void transform(AffineTransform Tx) {}
            @Override
            public void setTransform(AffineTransform Tx) {}
            @Override
            public AffineTransform getTransform() { return null; }
            @Override
            public Paint getPaint() { return null; }
            @Override
            public Composite getComposite() { return null; }
            @Override
            public void setBackground(Color color) {}
            @Override
            public Color getBackground() { return null; }
            @Override
            public Stroke getStroke() { return null; }
            @Override
            public void clip(Shape s) {}
            @Override
            public FontRenderContext getFontRenderContext() { return null; }
            @Override
            public Graphics create() { return null; }
            @Override
            public Color getColor() { return null; }
            @Override
            public void setColor(Color c) {}
            @Override
            public void setPaintMode() {}
            @Override
            public void setXORMode(Color c1) {}
            @Override
            public Font getFont() { return null; }
            @Override
            public void setFont(Font font) {}
            @Override
            public FontMetrics getFontMetrics(Font f) { return null; }
            @Override
            public Rectangle getClipBounds() { return null; }
            @Override
            public void clipRect(int x, int y, int width, int height) {}
            @Override
            public void setClip(int x, int y, int width, int height) {}
            @Override
            public Shape getClip() { return null; }
            @Override
            public void setClip(Shape clip) {}
            @Override
            public void copyArea(int x, int y, int width, int height, int dx, int dy) {}
            @Override
            public void drawLine(int x1, int y1, int x2, int y2) {}
            @Override
            public void fillRect(int x, int y, int width, int height) {}
            @Override
            public void clearRect(int x, int y, int width, int height) {}
            @Override
            public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {}
            @Override
            public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {}
            @Override
            public void drawOval(int x, int y, int width, int height) {}
            @Override
            public void fillOval(int x, int y, int width, int height) {}
            @Override
            public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {}
            @Override
            public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {}
            @Override
            public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {}
            @Override
            public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {}
            @Override
            public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {}
            @Override
            public void drawChars(char[] data, int offset, int length, int x, int y) {}
            @Override
            public void drawBytes(byte[] data, int offset, int length, int x, int y) {}
        };

        chaosGame.drawPoints(g);

        // Since drawPoints only draws points, we can't directly assert anything here.
        // This test is more about ensuring that the method runs without exceptions.
    }

    @Test
    public void testEmptyStackException() {
        chaosGame.stack.clear();
        assertThrows(EmptyStackException.class, () -> chaosGame.addPoint());
    }
}
