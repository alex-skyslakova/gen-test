import static org.junit.Assert.*;
import org.junit.Test;

public class EqulibriumTest {

    @Test
    public void testEqulibriumIndicesWithNoEqulibrium() {
        int[] sequence = {1, 2, 3, 4, 5};
        // Capture the output to check if nothing is printed
        Equlibrium.equlibrium_indices(sequence);
        // No equilibrium indices should be printed
    }

    @Test
    public void testEqulibriumIndicesWithSingleEqulibrium() {
        int[] sequence = {-7, 1, 5, 2, -4, 3, 0};
        // Capture the output to check if the correct index is printed
        Equlibrium.equlibrium_indices(sequence);
        // Expected equilibrium indices: 3 and 6
    }

    @Test
    public void testEqulibriumIndicesWithMultipleEqulibrium() {
        int[] sequence = {0, -3, 5, -4, -2, 3, 1, 0};
        // Capture the output to check if the correct indices are printed
        Equlibrium.equlibrium_indices(sequence);
        // Expected equilibrium indices: 0, 3, 7
    }

    @Test
    public void testEqulibriumIndicesWithAllZeroes() {
        int[] sequence = {0, 0, 0, 0, 0};
        // Capture the output to check if all indices are printed
        Equlibrium.equlibrium_indices(sequence);
        // Expected equilibrium indices: 0, 1, 2, 3, 4
    }

    @Test
    public void testEqulibriumIndicesWithEmptySequence() {
        int[] sequence = {};
        // Capture the output to check if nothing is printed
        Equlibrium.equlibrium_indices(sequence);
        // No equilibrium indices should be printed
    }

    @Test
    public void testEqulibriumIndicesWithSingleElement() {
        int[] sequence = {1};
        // Capture the output to check if the correct index is printed
        Equlibrium.equlibrium_indices(sequence);
        // Expected equilibrium index: 0
    }

    @Test
    public void testEqulibriumIndicesWithNegativeAndPositiveNumbers() {
        int[] sequence = {-1, 3, -4, 5, 1, -6, 2, 1};
        // Capture the output to check if the correct indices are printed
        Equlibrium.equlibrium_indices(sequence);
        // Expected equilibrium indices: 1, 3, 7
    }

    @Test
    public void testEqulibriumIndicesWithLargeNumbers() {
        int[] sequence = {1000000, -1000000, 1000000, -1000000, 0};
        // Capture the output to check if the correct index is printed
        Equlibrium.equlibrium_indices(sequence);
        // Expected equilibrium index: 4
    }
}
