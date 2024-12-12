import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AssociativeArrayMergingTest {

    @Test
    fun testMergeAssociativeArrays() {
        val base = hashMapOf<String, String>()
        val update = hashMapOf<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        val merged = mergeAssociativeArrays(base, update)

        assertEquals(4, merged.size)
        assertEquals("Rocket Skates", merged["name"])
        assertEquals("15.25", merged["price"])
        assertEquals("red", merged["color"])
        assertEquals("1974", merged["year"])


        // Verify original maps are not modified
        assertEquals(3, base.size)
        assertEquals("12.75", base["price"])
        assertEquals("yellow", base["color"])

        assertEquals(3, update.size)
    }

    @Test
    fun testMergeAssociativeArrays_emptyBase() {
        val base = hashMapOf<String, String>()
        val update = hashMapOf<String, String>()

        update["price"] = "15.25"
        update["color"] = "red"
        update["year"] = "1974"

        val merged = mergeAssociativeArrays(base, update)

        assertEquals(3, merged.size)
        assertEquals("15.25", merged["price"])
        assertEquals("red", merged["color"])
        assertEquals("1974", merged["year"])
    }


    @Test
    fun testMergeAssociativeArrays_emptyUpdate() {
        val base = hashMapOf<String, String>()
        val update = hashMapOf<String, String>()

        base["name"] = "Rocket Skates"
        base["price"] = "12.75"
        base["color"] = "yellow"

        val merged = mergeAssociativeArrays(base, update)

        assertEquals(3, merged.size)
        assertEquals("Rocket Skates", merged["name"])
        assertEquals("12.75", merged["price"])
        assertEquals("yellow", merged["color"])
    }


    @Test
    fun testMergeAssociativeArrays_bothEmpty() {
        val base = hashMapOf<String, String>()
        val update = hashMapOf<String, String>()

        val merged = mergeAssociativeArrays(base, update)

        assertTrue(merged.isEmpty())
    }


    fun mergeAssociativeArrays(base: Map<String, String>, update: Map<String, String>): Map<String, String> {
        val merged = HashMap(base)
        merged.putAll(update)
        return merged
    }

}
