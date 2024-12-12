import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvoAlgoTest {

    @Test
    public void testFitnessFunction() {
        // Test the fitness function with exact match
        assertEquals(EvoAlgo.perfectFitness, EvoAlgo.fitness(EvoAlgo.target));

        // Test the fitness function with no match
        String noMatch = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        assertEquals(0, EvoAlgo.fitness(noMatch));

        // Test the fitness function with partial match
        String partialMatch = "METHINKS IT IS LIKE A WEASEL";
        assertEquals(EvoAlgo.perfectFitness - 1, EvoAlgo.fitness(partialMatch));
    }

    @Test
    public void testNewMutateRate() {
        // Test the newMutateRate function with perfect fitness
        EvoAlgo.parent = EvoAlgo.target;
        assertEquals(0.0, EvoAlgo.newMutateRate());

        // Test the newMutateRate function with no fitness
        EvoAlgo.parent = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        assertEquals(1 - EvoAlgo.minMutateRate, EvoAlgo.newMutateRate());

        // Test the newMutateRate function with partial fitness
        EvoAlgo.parent = "METHINKS IT IS LIKE A WEASEL";
        double expectedRate = (((double)EvoAlgo.perfectFitness - (EvoAlgo.perfectFitness - 1)) / EvoAlgo.perfectFitness * (1 - EvoAlgo.minMutateRate));
        assertEquals(expectedRate, EvoAlgo.newMutateRate());
    }

    @Test
    public void testMutateFunction() {
        // Test the mutate function with a high mutation rate
        String mutated = EvoAlgo.mutate(EvoAlgo.target, 1.0);
        assertNotEquals(EvoAlgo.target, mutated);

        // Test the mutate function with a low mutation rate
        mutated = EvoAlgo.mutate(EvoAlgo.target, 0.0);
        assertEquals(EvoAlgo.target, mutated);

        // Test the mutate function with a moderate mutation rate
        mutated = EvoAlgo.mutate(EvoAlgo.target, 0.5);
        assertNotEquals(EvoAlgo.target, mutated);
    }

    @Test
    public void testMainFunction() {
        // Test the main function to ensure it converges to the target
        EvoAlgo.main(new String[]{});
        assertEquals(EvoAlgo.target, EvoAlgo.parent);
    }
}
