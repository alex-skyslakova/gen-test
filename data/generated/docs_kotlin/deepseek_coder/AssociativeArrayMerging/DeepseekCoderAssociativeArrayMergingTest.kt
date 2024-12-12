import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.HashMap

class AssociativeArrayMergingTest {

    @Test
    fun testMergeAssociativeArrays() {
        // Arrange
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        // Act
        val merged = HashMap(base)
        merged.putAll(update)

        // Assert
        val expected = HashMap<String, String>()
        expected["name"] = "Rocket Skates"
        expected["price"] = "15.25"
        expected["color"] = "red"
        expected["year"] = "1974"

        assertEquals(expected, merged)
    }

    @Test
    fun testMergeAssociativeArraysWithNoOverlap() {
        // Arrange
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"

        update["color"] = "red"
        update["year"] = "1974"

        // Act
        val merged = HashMap(base)
        merged.putAll(update)

        // Assert
        val expected = HashMap<String, String>()
        expected["name"] = "Rocket Skates"
        expected["price"] = "12.75"
        expected["color"] = "red"
        expected["year"] = "1974"

        assertEquals(expected, merged)
    }

    @Test
    fun testMergeAssociativeArraysWithEmptyBase() {
        // Arrange
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        update["name"] = "Rocket Skates"
        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        // Act
        val merged = HashMap(base)
        merged.putAll(update)

        // Assert
        val expected = HashMap<String, String>()
        expected["name"] = "Rocket Skates"
        expected["price"] = "15.25"
        expected["color"] = "red"
        expected["year"] = "1974"

        assertEquals(expected, merged)
    }

    @Test
    fun testMergeAssociativeArraysWithEmptyUpdate() {
        // Arrange
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        // Act
        val merged = HashMap(base)
        merged.putAll(update)

        // Assert
        val expected = HashMap<String, String>()
        expected["name"] = "Rocket Skates"
        expected["price"] = "12.75"
        expected["color"] = "yellow"

        assertEquals(expected, merged)
    }

    @Test
    fun testMergeAssociativeArraysWithIdenticalData() {
        // Arrange
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        update["name"] = "Rocket Skates"
        update["price"] = "12.75"
        update["color"] = "yellow"

        // Act
        val merged = HashMap(base)
        merged.putAll(update)

        // Assert
        val expected = HashMap<String, String>()
        expected["name"] = "Rocket Skates"
        expected["price"] = "12.75"
        expected["color"] = "yellow"

        assertEquals(expected, merged)
    }
}
