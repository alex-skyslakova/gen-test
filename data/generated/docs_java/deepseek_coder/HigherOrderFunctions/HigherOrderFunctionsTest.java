import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NewClassTest {

    @Test
    void testFirstMethodCallsCallback() {
        // Arrange
        NewClass newClass = new NewClass();
        AnEventOrCallback mockCallback = mock(AnEventOrCallback.class);

        // Act
        newClass.first(mockCallback);

        // Assert
        verify(mockCallback, times(1)).call();
    }

    @Test
    void testSecondMethodIsCalled() {
        // Arrange
        NewClass newClass = new NewClass();
        NewClass spyNewClass = spy(newClass);

        // Act
        spyNewClass.first(new AnEventOrCallback() {
            @Override
            public void call() {
                spyNewClass.second();
            }
        });

        // Assert
        verify(spyNewClass, times(1)).second();
    }

    @Test
    void testConstructorCallsFirstMethod() {
        // Arrange
        NewClass newClass = new NewClass();
        NewClass spyNewClass = spy(newClass);

        // Act
        spyNewClass.first(new AnEventOrCallback() {
            @Override
            public void call() {
                spyNewClass.second();
            }
        });

        // Assert
        verify(spyNewClass, times(1)).first(any(AnEventOrCallback.class));
    }
}
