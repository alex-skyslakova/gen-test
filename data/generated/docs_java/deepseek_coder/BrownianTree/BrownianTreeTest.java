import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrownianTreeTest {

    private BrownianTree brownianTree;
    private BufferedImage mockImage;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockImage = mock(BufferedImage.class);
        mockRandom = mock(Random.class);
        brownianTree = new BrownianTree() {
            @Override
            BufferedImage I = mockImage;
            @Override
            Random rand = mockRandom;
        };
    }

    @Test
    void testInitialization() {
        assertNotNull(brownianTree.I);
        assertNotNull(brownianTree.particles);
        assertEquals(400, brownianTree.getWidth());
        assertEquals(300, brownianTree.getHeight());
    }

    @Test
    void testParticleInitialization() {
        when(mockRandom.nextInt(anyInt())).thenReturn(100, 150);
        BrownianTree.Particle particle = brownianTree.new Particle();
        assertEquals(100, particle.x);
        assertEquals(150, particle.y);
    }

    @Test
    void testParticleMoveOutOfBounds() {
        when(mockRandom.nextInt(anyInt())).thenReturn(1, 1); // dx = 1, dy = 1
        BrownianTree.Particle particle = brownianTree.new Particle();
        particle.x = 399;
        particle.y = 299;
        assertTrue(particle.move());
    }

    @Test
    void testParticleMoveCollision() {
        when(mockRandom.nextInt(anyInt())).thenReturn(1, 1); // dx = 1, dy = 1
        when(mockImage.getRGB(anyInt(), anyInt())).thenReturn(0xff00);
        BrownianTree.Particle particle = brownianTree.new Particle();
        particle.x = 100;
        particle.y = 100;
        assertTrue(particle.move());
        verify(mockImage).setRGB(99, 99, 0xff00);
    }

    @Test
    void testParticleMoveNoCollision() {
        when(mockRandom.nextInt(anyInt())).thenReturn(1, 1); // dx = 1, dy = 1
        when(mockImage.getRGB(anyInt(), anyInt())).thenReturn(0x0000);
        BrownianTree.Particle particle = brownianTree.new Particle();
        particle.x = 100;
        particle.y = 100;
        assertFalse(particle.move());
        assertEquals(101, particle.x);
        assertEquals(101, particle.y);
    }

    @Test
    void testRunMethod() {
        List<BrownianTree.Particle> mockParticles = new LinkedList<>();
        BrownianTree.Particle mockParticle1 = mock(BrownianTree.Particle.class);
        BrownianTree.Particle mockParticle2 = mock(BrownianTree.Particle.class);
        mockParticles.add(mockParticle1);
        mockParticles.add(mockParticle2);

        brownianTree.particles = mockParticles;

        when(mockParticle1.move()).thenReturn(true);
        when(mockParticle2.move()).thenReturn(false).thenReturn(true);

        brownianTree.run();

        verify(mockParticle1, times(1)).move();
        verify(mockParticle2, times(2)).move();
        assertTrue(brownianTree.particles.isEmpty());
    }
}
