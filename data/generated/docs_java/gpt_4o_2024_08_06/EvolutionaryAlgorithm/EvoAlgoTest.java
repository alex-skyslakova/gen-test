import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EvoAlgoTest {

    private EvoAlgo evoAlgo;
    private Method fitnessMethod;
    private Method newMutateRateMethod;
    private Method mutateMethod;

    @BeforeEach
    public void setUp() throws Exception {
        evoAlgo = new EvoAlgo();
        fitnessMethod = EvoAlgo.class.getDeclaredMethod("fitness", String.class);
        fitnessMethod.setAccessible(true);

        newMutateRateMethod = EvoAlgo.class.getDeclaredMethod("newMutateRate");
        newMutateRateMethod.setAccessible(true);

        mutateMethod = EvoAlgo.class.getDeclaredMethod("mutate", String.class, double.class);
        mutateMethod.setAccessible(true);
    }

    @Test
    public void testFitness() throws Exception {
        String target = "METHINKS IT IS LIKE A WEASEL";
        int fitness = (int) fitnessMethod.invoke(evoAlgo, target);
        assertEquals(target.length(), fitness);

        String halfCorrect = "METHINKS IT IS LIKE A WEASEX";
        fitness = (int) fitnessMethod.invoke(evoAlgo, halfCorrect);
        assertEquals(target.length() - 1, fitness);

        String completelyWrong = "XXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        fitness = (int) fitnessMethod.invoke(evoAlgo, completelyWrong);
        assertEquals(0, fitness);
    }

    @Test
    public void testNewMutateRate() throws Exception {
        EvoAlgo.parent = "METHINKS IT IS LIKE A WEASEL";
        double rate = (double) newMutateRateMethod.invoke(evoAlgo);
        assertEquals(0.0, rate, 0.001);

        EvoAlgo.parent = "XXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        rate = (double) newMutateRateMethod.invoke(evoAlgo);
        assertTrue(rate > 0.09);
    }

    @Test
    public void testMutate() throws Exception {
        String parent = "METHINKS IT IS LIKE A WEASEL";
        double rate = 0.5;
        String mutated = (String) mutateMethod.invoke(evoAlgo, parent, rate);
        assertNotEquals(parent, mutated);
        assertEquals(parent.length(), mutated.length());

        rate = 0.0;
        mutated = (String) mutateMethod.invoke(evoAlgo, parent, rate);
        assertEquals(parent, mutated);

        rate = 1.0;
        mutated = (String) mutateMethod.invoke(evoAlgo, parent, rate);
        assertNotEquals(parent, mutated);
    }

    @Test
    public void testMainConvergence() {
        EvoAlgo.main(new String[]{});
        assertEquals(EvoAlgo.target, EvoAlgo.parent);
    }
}
