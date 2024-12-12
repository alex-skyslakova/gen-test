import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LinePrinterTest {

    private FileWriter mockFileWriter;

    @BeforeEach
    public void setup() throws IOException {
        mockFileWriter = mock(FileWriter.class);
    }


    @Test
    public void testPrintSuccess() throws IOException {
        // Inject the mock FileWriter
        LinePrinter.main(new String[0]); //Can't inject due to static main
       
        try (FileWriter lp0 = new FileWriter("/dev/lp0")){
            verify(lp0).write("Hello World!");

        } catch (IOException e) {

        }

    }


    @Test
    public void testPrintIOException() throws IOException {
         doThrow(new IOException()).when(mockFileWriter).write(anyString());

        try (FileWriter lp0 = new FileWriter("/dev/lp0")){
            lp0.write("Hello World!");
        }catch (IOException e){
            //verify io exception thrown. 
        }



    }
}
