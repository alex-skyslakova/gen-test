import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SqueezeTest {

    private fun squeeze(input: String, include: Char): String {
        val sb = StringBuilder()
        for (i in input.indices) {
            if (i == 0 || input[i - 1] != input[i] || input[i - 1] == input[i] && input[i] != include) {
                sb.append(input[i])
            }
        }
        return sb.toString()
    }

    @Test
    fun testEmptyString() {
        assertEquals("", squeeze("", ' '))
    }

    @Test
    fun testNoRepeats() {
        assertEquals("abc", squeeze("abc", ' '))
    }

    @Test
    fun testRepeatsNotIncluded() {
        assertEquals("abbc", squeeze("abbc", 'a'))
    }


    @Test
    fun testRepeatsIncluded() {
        assertEquals("abc", squeeze("abbc", 'b'))
    }

    @Test
    fun testMultipleRepeatsIncluded() {
        assertEquals("abc", squeeze("abbbbc", 'b'))
    }

    @Test
    fun testMultipleRepeatsDifferentChars() {
        assertEquals("abbcdd", squeeze("abbbbcddd", 'b'))
    }

    @Test
    fun testLincolnExample() {
        val input = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln "
        assertEquals(input, squeeze(input, '-'))
        
    }

    @Test
    fun testNumberExample(){
        val input = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        assertEquals("..111111111111111111111111111111111111111111111111111111111111111788", squeeze(input, '7'))
    }

    @Test
    fun testTrumanExampleBlank(){
        val input = "                                                    --- Harry S Truman  "
        val expected = " --- Harry S Truman  "
        assertEquals(expected, squeeze(input, ' '))
    }
    
    @Test
    fun testTrumanExampleMinus(){
        val input = "                                                    --- Harry S Truman  "
        val expected = "                                                    - Harry S Truman  "
        assertEquals(expected, squeeze(input, '-'))
    }

    @Test
    fun testTrumanExampleR(){
        val input = "                                                    --- Harry S Truman  "
        val expected = "                                                    --- Hay S Truman  "

        assertEquals(expected, squeeze(input, 'r'))
    }

    @Test
    fun testHeadmistressship(){
        val input = "headmistressship"
        val expected = "headmistreship"
        assertEquals(expected, squeeze(input, 's'))
    }

    @Test
    fun testBetterExample(){
        val input = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        val expected = "The beter the 4-whel drive, the further you'll be from help when ya get stuck!"
        assertEquals(expected, squeeze(input, 'e'))

    }


}
