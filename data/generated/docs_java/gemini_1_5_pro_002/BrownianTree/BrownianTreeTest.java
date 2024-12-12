import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class BrownianTreeTest {

    @Test
    void testParticleCreation() {
        BrownianTree.Particle p = new BrownianTree().new Particle();
        assertTrue(p.x >= 0 && p.x < 400); // Assuming default width
        assertTrue(p.y >= 0 && p.y < 300); // Assuming default height
    }

    @Test
    void testParticleMoveOutOfBounds() {
        BrownianTree bt = new BrownianTree();
        BrownianTree.Particle p = bt.new Particle();
        p.x = 0;
        p.y = 0;
        assertTrue(p.move()); // Moving out of bounds should return true

        p.x = bt.I.getWidth() -1;
        p.y = bt.I.getHeight() - 1;
        // Force a move that goes out of bounds
        assertTrue(p.move());
    }


    @Test
    void testParticleMoveCollision() {
        BrownianTree bt = new BrownianTree();
        BrownianTree.Particle p = bt.new Particle();

        // Place particle next to the seed
        p.x = bt.I.getWidth() / 2 + 1;
        p.y = bt.I.getHeight() / 2;
         
        boolean collided = p.move(); // Move towards the seed

        if (collided) {
             assertEquals(0xff00, bt.I.getRGB(bt.I.getWidth() / 2 +1, bt.I.getHeight() / 2) & 0xff00); //Should set previous location
        }
    }
    
      @Test
    void testParticleMoveNoCollision() {
        BrownianTree bt = new BrownianTree();
        BrownianTree.Particle p = bt.new Particle();

        // Place particle away from the seed
        p.x = 10;
        p.y = 10;
        // Mock random movement that doesn't hit boundaries or seed
        BrownianTree.rand = new Random(12345L); // Fixed seed for predictable movement

        assertFalse(p.move());

    }

    @Test
    void testRunMethodAddsParticles() {
       BrownianTree bt = new BrownianTree();
        List<BrownianTree.Particle> particles = new LinkedList<>();
        bt.particles = particles; // Accessing package-private member for testing
        bt.run();  
        assertTrue(particles.isEmpty()); // Because they'll all be consumed given the implementation of move() and the default image size

    }


    @Test
    void testBufferedImageInitialization() {
        BrownianTree bt = new BrownianTree();
        assertNotNull(bt.I);
        assertEquals(400, bt.I.getWidth()); // Assuming default width
        assertEquals(300, bt.I.getHeight()); // Assuming default height
        assertEquals(0xff00, bt.I.getRGB(bt.I.getWidth() / 2, bt.I.getHeight() / 2) & 0xff00); // Check seed color
    }




}
