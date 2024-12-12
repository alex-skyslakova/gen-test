import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegratorTest {

    @Test
    public void testIntegrator() throws InterruptedException {
        // Define the sine function with frequency f = 0.5Hz
        Integrator.Function sineFunction = t -> Math.sin(2 * Math.PI * 0.5 * t);

        // Create the integrator with the sine function
        Integrator integrator = new Integrator(sineFunction);

        // Wait for 2 seconds
        Thread.sleep(2000);

        // Set the input to constant 0
        integrator.setFunc(t -> 0.0);

        // Wait for 0.5 seconds
        Thread.sleep(500);

        // Stop the integrator
        integrator.stop();

        // Verify that the output is approximately 0
        double output = integrator.getOutput();
        assertEquals(0.0, output, 0.1, "The output should be approximately 0");
    }
}
