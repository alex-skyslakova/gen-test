import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErr

class HelloWorldStandardErrorTest {

    @Test
    fun testStandardErrorOutput() {
        val errorOutput = tapSystemErr {
            main(arrayOf())
        }
        assertEquals("Goodbye, World!\n", errorOutput)
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("com.github.stefanbirkner:system-lambda:1.2.0")
}
