import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HoughTransformTest {

    @Test
    void testHoughTransform_emptyInput() {
        ArrayData inputData = new ArrayData(0, 0);
        ArrayData outputData = HoughTransform.houghTransform(inputData, 10, 10, 10);
        assertEquals(10, outputData.width);
        assertEquals(10, outputData.height);
        assertEquals(0, outputData.dataArray.length);
    }

    @Test
    void testHoughTransform_singlePointContrast() {
        int width = 3;
        int height = 3;
        ArrayData inputData = new ArrayData(width, height);
        inputData.set(1, 1, 255); // Center point with high contrast

        ArrayData outputData = HoughTransform.houghTransform(inputData, 180, 100, 50); // Using finer theta resolution

        // Check for accumulation in the output data corresponding to the line
        assertNotEquals(0, outputData.getMax());
    }

     @Test
    void testHoughTransform_noContrast() {
        int width = 3;
        int height = 3;
        ArrayData inputData = new ArrayData(width, height);
        // Fill with the same value - no contrast
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                inputData.set(x, y, 128);
            }
        }

        ArrayData outputData = HoughTransform.houghTransform(inputData, 180, 100, 50);
        assertEquals(0, outputData.getMax()); // Expecting no accumulation
    }

    @Test
    void testArrayData_getAndSet() {
        ArrayData arrayData = new ArrayData(10, 5);
        arrayData.set(3, 2, 123);
        assertEquals(123, arrayData.get(3, 2));
    }

    @Test
    void testArrayData_accumulate() {
        ArrayData arrayData = new ArrayData(10, 5);
        arrayData.accumulate(3, 2, 50);
        assertEquals(50, arrayData.get(3, 2));
        arrayData.accumulate(3, 2, 25);
        assertEquals(75, arrayData.get(3, 2));
    }


    @Test
    void testArrayData_contrast() {
        ArrayData arrayData = new ArrayData(3, 3);
        arrayData.set(1, 1, 128); // Center
        arrayData.set(0, 1, 100); // Left
        arrayData.set(2, 1, 150); // Right

        assertTrue(arrayData.contrast(1, 1, 20)); // Sufficient contrast
        assertFalse(arrayData.contrast(1, 1, 30)); // Not enough contrast

    }


    @Test
    void testArrayData_getMax_empty() {
        ArrayData arrayData = new ArrayData(0, 0);
        assertEquals(0, arrayData.getMax());
    }

    @Test
    void testArrayData_getMax_populated() {
        ArrayData arrayData = new ArrayData(3, 3);
        arrayData.set(0, 0, 10);
        arrayData.set(1, 1, 150);
        arrayData.set(2, 2, 50);
        assertEquals(150, arrayData.getMax());
    }



}
