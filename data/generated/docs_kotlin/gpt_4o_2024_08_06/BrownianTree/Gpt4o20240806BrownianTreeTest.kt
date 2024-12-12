import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage

class BrownianTreeTest {

    private lateinit var tree: BrownianTree
    private lateinit var img: BufferedImage

    @BeforeEach
    fun setUp() {
        tree = BrownianTree()
        img = BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB)
    }

    @Test
    fun testParticleInitialization() {
        val particle = tree.Particle()
        assertTrue(particle.x in 0 until img.width)
        assertTrue(particle.y in 0 until img.height)
    }

    @Test
    fun testParticleMoveOutOfBounds() {
        val particle = tree.Particle()
        // Manually set particle position to edge
        particle.x = 0
        particle.y = 0
        assertTrue(particle.move()) // Should return true as it moves out of bounds
    }

    @Test
    fun testParticleMoveCollision() {
        val particle = tree.Particle()
        // Manually set particle position to a point that will collide
        particle.x = img.width / 2
        particle.y = img.height / 2
        img.setRGB(particle.x, particle.y, 0xff00) // Simulate tree at this position
        assertTrue(particle.move()) // Should return true due to collision
    }

    @Test
    fun testBrownianTreeInitialization() {
        assertEquals(400, tree.width)
        assertEquals(300, tree.height)
        assertEquals(0xff00, img.getRGB(img.width / 2, img.height / 2) and 0xff00)
    }
}
