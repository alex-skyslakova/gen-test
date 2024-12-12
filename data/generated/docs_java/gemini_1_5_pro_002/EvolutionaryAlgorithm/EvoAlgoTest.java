import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class EvoAlgoTest {

    @Test
    void testFitness() {
        assertEquals(EvoAlgo.perfectFitness, EvoAlgo.fitness(EvoAlgo.target));
        assertEquals(0, EvoAlgo.fitness(""));
        String testString = "METHINKS IT IS LIKE A WEASEL";
        assertEquals(EvoAlgo.perfectFitness, EvoAlgo.fitness(testString));
        testString = "XXXXXXXXXXXXXXXXXXXXXXXXXX";
        assertEquals(0, EvoAlgo.fitness(testString));

    }


    @Test
    void testNewMutateRate() {
        EvoAlgo.parent = EvoAlgo.target;
        assertEquals(EvoAlgo.minMutateRate, EvoAlgo.newMutateRate());
        EvoAlgo.parent = "";
        for(int i=0; i< EvoAlgo.target.length(); i++){
          EvoAlgo.parent += "X";
        }
        assertEquals(1.0 - EvoAlgo.minMutateRate, EvoAlgo.newMutateRate());

    }

    @Test
    void testMutate() {
        EvoAlgo.rand = new Random(123); // Fixed seed for predictable results
        String original = "TEST";
        String mutated = EvoAlgo.mutate(original, 1.0); // Guaranteed mutation
        assertNotEquals(original, mutated);

        mutated = EvoAlgo.mutate(original, 0.0); // No mutation
        assertEquals(original, mutated);

        EvoAlgo.rand = new Random(); // Reset random for other tests
    }



    @Test
    void testConvergence() {

        EvoAlgo.C = 50; // Reduce for faster testing
        EvoAlgo.minMutateRate = 0.1;
        EvoAlgo.parent = EvoAlgo.mutate(EvoAlgo.target, 1); // Start with fully mutated parent
        int maxIterations = 50000; // Set a maximum number of iterations for the test
        int iter = 0;
        while (!EvoAlgo.target.equals(EvoAlgo.parent) && iter < maxIterations) {
          EvoAlgo.main(null);
          iter++;

        }

        assertTrue(iter < maxIterations, "Did not converge within maximum iterations");


    }
}
