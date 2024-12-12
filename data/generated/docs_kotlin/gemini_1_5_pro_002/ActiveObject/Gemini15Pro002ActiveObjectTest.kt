import kotlin.math.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.TimeUnit

class IntegratorTest {

    @Test
    fun testSineIntegration() {
        val integrator = Integrator { sin(2 * PI * 0.5 * it) }

        TimeUnit.SECONDS.sleep(2)

        integrator.setFunc { 0.0 }
        TimeUnit.MILLISECONDS.sleep(500)

        integrator.stop()

        val output = integrator.getOutput()
        assertTrue(abs(output) < 0.1, "Output should be close to 0, but was $output")
    }

    @Test
    fun testConstantInput() {
        val constant = 5.0
        val integrator = Integrator { constant }

        TimeUnit.SECONDS.sleep(2)

        integrator.stop()

        val expected = constant * 2.0 
        val output = integrator.getOutput()
        assertEquals(expected, output, 0.1, "Output should be close to ${expected}, but was $output")
    }


    @Test
    fun testZeroInput() {
        val integrator = Integrator { 0.0 }

        TimeUnit.SECONDS.sleep(1)

        integrator.stop()

        val output = integrator.getOutput()
        assertEquals(0.0, output, 0.0001)
    }

    @Test
    fun testLinearInput(){
        val integrator = Integrator { it } // Linear function f(t) = t

        TimeUnit.SECONDS.sleep(2)

        integrator.stop()

        val expected = 2.0 // Integral of t from 0 to 2 is t^2/2 = 2
        val output = integrator.getOutput()
        assertEquals(expected, output, 0.1, "Output should be close to $expected, but was $output")
    }
}

