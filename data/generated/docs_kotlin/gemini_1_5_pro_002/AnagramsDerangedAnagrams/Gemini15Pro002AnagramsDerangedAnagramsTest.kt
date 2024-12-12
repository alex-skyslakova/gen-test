import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.BufferedReader
import java.io.StringReader


class AnagramsDerangedAnagramsTest {

    @Test
    fun testIsDeranged() {
        assertTrue(isDeranged("abc", "bca"))
        assertTrue(isDeranged("night", "thing"))
        assertFalse(isDeranged("abc", "aba"))
        assertFalse(isDeranged("abc", "abc"))
        assertTrue(isDeranged("stressed", "desserts"))
        assertFalse(isDeranged("a", "a"))
    }



    @Test
    fun testMain_NoDerangedAnagrams() {
        val input = "a\nb\nc\n"
        val reader = BufferedReader(StringReader(input))
        val anagrams = mutableMapOf<String, MutableList<String>>()
        var count = 0
        var word = reader.readLine()

        // Simulate the loop, reusing the local variables declared in main
        while (word != null) {
            val chars = word.toCharArray()
            chars.sort()
            val key = chars.joinToString("")
            if (!anagrams.containsKey(key)) {
                anagrams[key] = mutableListOf()
                anagrams[key]!!.add(word)
            } else {
                val deranged = anagrams[key]!!.any { isDeranged(it, word) }
                if (deranged) {
                    anagrams[key]!!.add(word)
                    count = maxOf(count, word.length)
                }
            }
            word = reader.readLine()
        }

       assertTrue(anagrams.values.filter { it.size > 1 && it[0].length == count }.isEmpty())


    }


    @Test
    fun testMain_WithDerangedAnagrams() {
        val input = "abc\nbac\ncab\ndcba\n"
        val reader = BufferedReader(StringReader(input))
        val anagrams = mutableMapOf<String, MutableList<String>>()
        var count = 0
        var word = reader.readLine()
         while (word != null) {
            val chars = word.toCharArray()
            chars.sort()
            val key = chars.joinToString("")
            if (!anagrams.containsKey(key)) {
                anagrams[key] = mutableListOf()
                anagrams[key]!!.add(word)
            } else {
                val deranged = anagrams[key]!!.any { isDeranged(it, word) }
                if (deranged) {
                    anagrams[key]!!.add(word)
                    count = maxOf(count, word.length)
                }
            }
            word = reader.readLine()
        }

       assertEquals(3, count)
       assertEquals(1, anagrams.values.filter { it.size > 1 && it[0].length == count }.size)


    }
}

