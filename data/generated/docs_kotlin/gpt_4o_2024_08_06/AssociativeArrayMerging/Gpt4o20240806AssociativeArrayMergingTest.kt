import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AssociativeArrayMergingTest {

    @Test
    fun testMergeAssociativeArrays() {
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        val merged = HashMap(base)
        merged.putAll(update)

        // Expected result after merging
        val expected = HashMap<String, String>()
        expected["name"] = "Rocket Skates"
        expected["price"] = "15.25"
        expected["color"] = "red"
        expected["year"] = "1974"

        assertEquals(expected, merged, "The merged associative array is not as expected.")
    }

    @Test
    fun testBaseArrayUnchanged() {
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        val merged = HashMap(base)
        merged.putAll(update)

        // Ensure the base array is unchanged
        val expectedBase = HashMap<String, String>()
        expectedBase["name"] = "Rocket Skates"
        expectedBase["price"] = "12.75"
        expectedBase["color"] = "yellow"

        assertEquals(expectedBase, base, "The base associative array should not be mutated.")
    }

    @Test
    fun testUpdateArrayUnchanged() {
        val base = HashMap<String, String>()
        val update = HashMap<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        val merged = HashMap(base)
        merged.putAll(update)

        // Ensure the update array is unchanged
        val expectedUpdate = HashMap<String, String>()
        expectedUpdate["price"] = "15.25"
        expectedUpdate["color"] = "red"
        expectedUpdate["year"] = "1974"

        assertEquals(expectedUpdate, update, "The update associative array should not be mutated.")
    }
}
