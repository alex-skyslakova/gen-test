import static org.junit.Assert.*;
import org.junit.Test;

public class IntegratorTest {

    @Test
    public void testIntegratorOutput() throws InterruptedException {
        // Create an integrator with a sine function (2π * 0.5Hz * t)
        Integrator integrator = new Integrator(t -> Math.sin(Math.PI * t));

        // Wait for 2 seconds
        Thread.sleep(2000);

        // Set the input to constant 0
        integrator.setFunc(t -> 0.0);

        // Wait for an additional 0.5 seconds
        Thread.sleep(500);

        // Stop the integrator
        integrator.stop();

        // Get the output and verify it is approximately 0
        double output = integrator.getOutput();
        assertTrue(Math.abs(output) < 0.01); // Allowing a small margin of error
    }
}
