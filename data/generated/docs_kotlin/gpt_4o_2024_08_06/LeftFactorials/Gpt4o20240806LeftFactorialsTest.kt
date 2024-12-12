import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class LeftFactorialsTest {

    @Test
    fun testLeftFactorialZeroToTen() {
        val expected = listOf(
            BigInteger("0"),  // !0
            BigInteger("1"),  // !1
            BigInteger("2"),  // !2
            BigInteger("4"),  // !3
            BigInteger("10"), // !4
            BigInteger("34"), // !5
            BigInteger("154"),// !6
            BigInteger("874"),// !7
            BigInteger("5914"),// !8
            BigInteger("46234"),// !9
            BigInteger("409114")// !10
        )
        for (i in 0..10) {
            assertEquals(expected[i], leftFactorial(i), "!$i should be ${expected[i]}")
        }
    }

    @Test
    fun testLeftFactorialTwentyToOneHundredTenByTens() {
        val expected = mapOf(
            20 to BigInteger("2561327494111820313"),
            30 to BigInteger("103331479663861449296666513375232000"),
            40 to BigInteger("29523279903960414084761860964352000000"),
            50 to BigInteger("608281864034267560872252163321295376887552831379210240000000"),
            60 to BigInteger("936667273148006523819706771754726729946963273456809472307200000000"),
            70 to BigInteger("1192924637559648351630159299194753435079682769360627506489600000000000"),
            80 to BigInteger("1284254859351803144120402919469265264713252149939827409838080000000000000"),
            90 to BigInteger("1189508689333124598351147346492337919790344998821061569464320000000000000000"),
            100 to BigInteger("980628936235802295377783475188276481018994906160439721344000000000000000000000"),
            110 to BigInteger("706573762247396164866049635840000000000000000000000000000000000000000000000000")
        )
        for ((i, expectedValue) in expected) {
            assertEquals(expectedValue, leftFactorial(i), "!$i should be $expectedValue")
        }
    }

    @Test
    fun testLeftFactorialLengthOneThousandToTenThousandByThousands() {
        val expectedLengths = mapOf(
            1000 to 2567,
            2000 to 5735,
            3000 to 8903,
            4000 to 12071,
            5000 to 15239,
            6000 to 18407,
            7000 to 21575,
            8000 to 24743,
            9000 to 27911,
            10000 to 31079
        )
        for ((i, expectedLength) in expectedLengths) {
            assertEquals(expectedLength, leftFactorial(i).toString().length, "Length of !$i should be $expectedLength")
        }
    }
}
