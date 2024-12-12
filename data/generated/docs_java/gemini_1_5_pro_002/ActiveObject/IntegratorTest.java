import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegratorTest {

    @Test
    void testIntegration() throws InterruptedException {
        Integrator integrator = new Integrator(t -> Math.sin(Math.PI * t));
        Thread.sleep(2000);

        integrator.setFunc(t -> 0.0);
        Thread.sleep(500);

        integrator.stop();
        double output = integrator.getOutput();
        assertEquals(0.0, output, 0.1); // Allow some tolerance due to timing variations
    }


    @Test
    void testConstantInput() throws InterruptedException {
        Integrator integrator = new Integrator(t -> 5.0);
        Thread.sleep(1000);

        integrator.stop();
        double output = integrator.getOutput();
        assertEquals(5.0, output, 0.1); // Allow tolerance, the exact value depends on thread scheduling.
    }


    @Test
    void testInitialConditions() {
        Integrator integrator = new Integrator(t -> 0.0);

        double output = integrator.getOutput();
        assertEquals(0.0, output, 0.0); // Initial output should be 0
    }

    @Test
    void testLinearInput() throws InterruptedException {
      Integrator integrator = new Integrator(t -> t);
      Thread.sleep(1000);
      integrator.stop();
      double output = integrator.getOutput();
      assertEquals(0.5, output, 0.1); // Expected integrated value after 1 second of t is roughly 0.5
    }
}
