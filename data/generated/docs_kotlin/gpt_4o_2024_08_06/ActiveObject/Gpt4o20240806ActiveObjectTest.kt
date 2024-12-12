import kotlin.math.PI
import kotlin.math.sin
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.Test

class IntegratorTest {

    @Test
    fun testIntegratorWithSineFunction() {
        val integrator = Integrator { t -> sin(2 * PI * 0.5 * t) }
        
        // Wait for 2 seconds to integrate the sine wave
        Thread.sleep(2000)
        
        // Change the input function to a constant 0
        integrator.setFunc { 0.0 }
        
        // Wait for 0.5 seconds
        Thread.sleep(500)
        
        // Stop the integrator
        integrator.stop()
        
        // Get the output
        val output = integrator.getOutput()
        
        // Verify that the output is approximately 0
        // Allow a small margin of error due to time slicing and clock accuracy
        assertTrue(output in -0.1..0.1, "Output is not approximately 0, actual output: $output")
    }
}
