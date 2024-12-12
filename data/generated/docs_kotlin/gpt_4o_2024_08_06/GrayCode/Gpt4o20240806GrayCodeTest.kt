import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GrayCodeTest {

    @Test
    fun testEncode() {
        val expectedGrayCodes = listOf(
            0b00000, // 0
            0b00001, // 1
            0b00011, // 2
            0b00010, // 3
            0b00110, // 4
            0b00111, // 5
            0b00101, // 6
            0b00100, // 7
            0b01100, // 8
            0b01101, // 9
            0b01111, // 10
            0b01110, // 11
            0b01010, // 12
            0b01011, // 13
            0b01001, // 14
            0b01000, // 15
            0b11000, // 16
            0b11001, // 17
            0b11011, // 18
            0b11010, // 19
            0b11110, // 20
            0b11111, // 21
            0b11101, // 22
            0b11100, // 23
            0b10100, // 24
            0b10101, // 25
            0b10111, // 26
            0b10110, // 27
            0b10010, // 28
            0b10011, // 29
            0b10001, // 30
            0b10000  // 31
        )

        for (i in 0..31) {
            assertEquals(expectedGrayCodes[i], Gray.encode(i), "Failed at index $i")
        }
    }

    @Test
    fun testDecode() {
        val grayCodes = listOf(
            0b00000, // 0
            0b00001, // 1
            0b00011, // 2
            0b00010, // 3
            0b00110, // 4
            0b00111, // 5
            0b00101, // 6
            0b00100, // 7
            0b01100, // 8
            0b01101, // 9
            0b01111, // 10
            0b01110, // 11
            0b01010, // 12
            0b01011, // 13
            0b01001, // 14
            0b01000, // 15
            0b11000, // 16
            0b11001, // 17
            0b11011, // 18
            0b11010, // 19
            0b11110, // 20
            0b11111, // 21
            0b11101, // 22
            0b11100, // 23
            0b10100, // 24
            0b10101, // 25
            0b10111, // 26
            0b10110, // 27
            0b10010, // 28
            0b10011, // 29
            0b10001, // 30
            0b10000  // 31
        )

        for (i in 0..31) {
            assertEquals(i, Gray.decode(grayCodes[i]), "Failed at index $i")
        }
    }
}
