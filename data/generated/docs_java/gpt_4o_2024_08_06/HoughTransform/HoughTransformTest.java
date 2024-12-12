import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class HoughTransformTest {

    @Test
    public void testArrayDataInitialization() {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(10, 10);
        assertEquals(10, arrayData.width);
        assertEquals(10, arrayData.height);
        assertEquals(100, arrayData.dataArray.length);
    }

    @Test
    public void testArrayDataGetSet() {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(10, 10);
        arrayData.set(5, 5, 255);
        assertEquals(255, arrayData.get(5, 5));
    }

    @Test
    public void testArrayDataAccumulate() {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(10, 10);
        arrayData.set(5, 5, 100);
        arrayData.accumulate(5, 5, 50);
        assertEquals(150, arrayData.get(5, 5));
    }

    @Test
    public void testArrayDataContrast() {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(3, 3);
        arrayData.set(1, 1, 100);
        arrayData.set(0, 0, 150);
        assertTrue(arrayData.contrast(1, 1, 40));
        assertFalse(arrayData.contrast(1, 1, 60));
    }

    @Test
    public void testArrayDataGetMax() {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(3, 3);
        arrayData.set(0, 0, 50);
        arrayData.set(1, 1, 100);
        arrayData.set(2, 2, 75);
        assertEquals(100, arrayData.getMax());
    }

    @Test
    public void testHoughTransform() {
        HoughTransform.ArrayData inputData = new HoughTransform.ArrayData(3, 3);
        inputData.set(0, 0, 255);
        inputData.set(1, 1, 255);
        inputData.set(2, 2, 255);

        HoughTransform.ArrayData outputData = HoughTransform.houghTransform(inputData, 180, 180, 50);

        assertNotNull(outputData);
        assertEquals(180, outputData.width);
        assertEquals(180, outputData.height);
    }

    @Test
    public void testGetArrayDataFromImage() throws IOException {
        // Assuming a test image "test.png" exists in the project directory
        HoughTransform.ArrayData arrayData = HoughTransform.getArrayDataFromImage("test.png");
        assertNotNull(arrayData);
        assertTrue(arrayData.width > 0);
        assertTrue(arrayData.height > 0);
    }

    @Test
    public void testWriteOutputImage() throws IOException {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(3, 3);
        arrayData.set(0, 0, 255);
        arrayData.set(1, 1, 255);
        arrayData.set(2, 2, 255);

        // This will write an output image "output.png" in the project directory
        HoughTransform.writeOutputImage("output.png", arrayData);
        
        // Check if the file is created
        File outputFile = new File("output.png");
        assertTrue(outputFile.exists());
        
        // Clean up
        outputFile.delete();
    }
}
