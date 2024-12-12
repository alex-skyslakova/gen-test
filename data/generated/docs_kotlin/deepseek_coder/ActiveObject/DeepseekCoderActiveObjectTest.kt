import kotlin.math.PI
import kotlin.math.sin
import kotlin.test.assertEquals
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.fail
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class IntegratorTest {

    private lateinit var integrator: Integrator

    @BeforeTest
    fun setUp() {
        integrator = Integrator({ sin(2 * PI * 0.5 * it) })
    }

    @Test
    fun testInitialState() {
        assertEquals(0.0, integrator.getOutput(), 0.0)
    }

    @Test
    fun testSetFunc() {
        integrator.setFunc({ 1.0 })
        Thread.sleep(100) // Allow some time for the integrator to update
        assertTrue(integrator.getOutput() > 0.0)
    }

    @Test
    fun testStop() {
        integrator.stop()
        val initialOutput = integrator.getOutput()
        Thread.sleep(100) // Allow some time for the integrator to potentially update
        assertEquals(initialOutput, integrator.getOutput(), 0.0)
    }

    @Test
    fun testIntegration() {
        Thread.sleep(2000) // Wait for 2 seconds
        integrator.setFunc({ 0.0 })
        Thread.sleep(500) // Wait for 0.5 seconds
        integrator.stop()

        val output = integrator.getOutput()
        // The output should be approximately 0, but due to scheduling and timing, we allow a small margin of error
        assertTrue(output < 0.1)
    }

    @Test
    fun testIntegrationWithDifferentFrequency() {
        integrator.setFunc({ sin(2 * PI * 1.0 * it) }) // Set frequency to 1Hz
        Thread.sleep(1000) // Wait for 1 second
        integrator.setFunc({ 0.0 })
        Thread.sleep(500) // Wait for 0.5 seconds
        integrator.stop()

        val output = integrator.getOutput()
        // The output should be approximately 0, but due to scheduling and timing, we allow a small margin of error
        assertTrue(output < 0.1)
    }

    @Test
    fun testIntegrationWithConstantInput() {
        integrator.setFunc({ 1.0 }) // Set constant input
        Thread.sleep(1000) // Wait for 1 second
        integrator.stop()

        val output = integrator.getOutput()
        // The output should be approximately 1.0, but due to scheduling and timing, we allow a small margin of error
        assertEquals(1.0, output, 0.1)
    }

    @Test
    fun testIntegrationWithNegativeInput() {
        integrator.setFunc({ -1.0 }) // Set constant negative input
        Thread.sleep(1000) // Wait for 1 second
        integrator.stop()

        val output = integrator.getOutput()
        // The output should be approximately -1.0, but due to scheduling and timing, we allow a small margin of error
        assertEquals(-1.0, output, 0.1)
    }

    @Test
    fun testIntegrationWithZeroInput() {
        integrator.setFunc({ 0.0 }) // Set constant zero input
        Thread.sleep(1000) // Wait for 1 second
        integrator.stop()

        val output = integrator.getOutput()
        // The output should be approximately 0.0
        assertEquals(0.0, output, 0.0)
    }

    @Test
    fun testIntegrationWithHighFrequency() {
        integrator.setFunc({ sin(2 * PI * 10.0 * it) }) // Set high frequency
        Thread.sleep(100) // Wait for 0.1 seconds
        integrator.setFunc({ 0.0 })
        Thread.sleep(500) // Wait for 0.5 seconds
        integrator.stop()

        val output = integrator.getOutput()
        // The output should be approximately 0, but due to scheduling and timing, we allow a small margin of error
        assertTrue(output < 0.1)
    }
}
