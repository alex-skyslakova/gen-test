import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.util.List;

public class BrownianTreeTest {

    private BrownianTree brownianTree;
    private BufferedImage image;
    private List<BrownianTree.Particle> particles;

    @BeforeEach
    public void setUp() {
        brownianTree = new BrownianTree();
        image = new BufferedImage(brownianTree.getWidth(), brownianTree.getHeight(), BufferedImage.TYPE_INT_RGB);
        particles = brownianTree.particles;
    }

    @Test
    public void testInitialSeedPlacement() {
        int centerX = image.getWidth() / 2;
        int centerY = image.getHeight() / 2;
        int rgb = image.getRGB(centerX, centerY);
        assertEquals(0xff00, rgb & 0xff00, "Initial seed should be placed at the center with color 0xff00");
    }

    @Test
    public void testParticleInitialization() {
        BrownianTree.Particle particle = brownianTree.new Particle();
        assertTrue(particle.x >= 0 && particle.x < image.getWidth(), "Particle x-coordinate should be within bounds");
        assertTrue(particle.y >= 0 && particle.y < image.getHeight(), "Particle y-coordinate should be within bounds");
    }

    @Test
    public void testParticleMovement() {
        BrownianTree.Particle particle = brownianTree.new Particle();
        int initialX = particle.x;
        int initialY = particle.y;
        boolean moved = particle.move();
        assertTrue(moved || (particle.x != initialX || particle.y != initialY), "Particle should move or collide");
    }

    @Test
    public void testParticleCollisionWithTree() {
        BrownianTree.Particle particle = brownianTree.new Particle();
        // Manually set particle position to collide with the initial seed
        particle.x = image.getWidth() / 2;
        particle.y = image.getHeight() / 2;
        boolean collided = particle.move();
        assertTrue(collided, "Particle should collide with the tree at the initial seed position");
    }

    @Test
    public void testParticleOutOfBounds() {
        BrownianTree.Particle particle = brownianTree.new Particle();
        // Manually set particle position to out of bounds
        particle.x = -1;
        particle.y = -1;
        boolean outOfBounds = particle.move();
        assertTrue(outOfBounds, "Particle should be out of bounds and return true");
    }
}
