import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ExtendYourLanguageTest {

    @Test
    fun testBothConditionsTrue() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        ifBoth(true, true) {
            println("both conditions are true")
        }
        .elseFirst {
            println("first condition is true")
        }
        .elseSecond {
            println("second condition is true")
        }
        .elseNeither {
            println("no condition is true")
        }

        assertEquals("both conditions are true\n", outputStream.toString())
    }

    @Test
    fun testFirstConditionTrue() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        ifBoth(true, false) {
            println("both conditions are true")
        }
        .elseFirst {
            println("first condition is true")
        }
        .elseSecond {
            println("second condition is true")
        }
        .elseNeither {
            println("no condition is true")
        }

        assertEquals("first condition is true\n", outputStream.toString())
    }

    @Test
    fun testSecondConditionTrue() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        ifBoth(false, true) {
            println("both conditions are true")
        }
        .elseFirst {
            println("first condition is true")
        }
        .elseSecond {
            println("second condition is true")
        }
        .elseNeither {
            println("no condition is true")
        }

        assertEquals("second condition is true\n", outputStream.toString())
    }

    @Test
    fun testNoConditionTrue() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        ifBoth(false, false) {
            println("both conditions are true")
        }
        .elseFirst {
            println("first condition is true")
        }
        .elseSecond {
            println("second condition is true")
        }
        .elseNeither {
            println("no condition is true")
        }

        assertEquals("no condition is true\n", outputStream.toString())
    }

    @Test
    fun testOmittingElseClauses() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        ifBoth(true, false) {
            println("both conditions are true")
        }
        .elseFirst {
            println("first condition is true")
        }

        assertEquals("first condition is true\n", outputStream.toString())
    }

    @Test
    fun testCallingElseClausesOutOfOrder() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        ifBoth(false, false) {
            println("both conditions are true")
        }
        .elseNeither {
            println("no condition is true")
        }
        .elseFirst {
            println("first condition is true")
        }

        assertEquals("no condition is true\n", outputStream.toString())
    }
}
