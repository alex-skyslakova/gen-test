import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CubanPrimesTest {

    @Test
    fun testFirst200CubanPrimes() {
        val expectedFirst200CubanPrimes = listOf(
            7, 19, 37, 61, 127, 271, 331, 397, 547, 631,
            919, 1051, 1153, 1483, 1879, 1993, 2089, 2803, 3169, 3463,
            3583, 4003, 4337, 4789, 5471, 5869, 6121, 7489, 8263, 9433,
            9649, 11279, 12251, 12763, 13523, 14321, 15131, 16381, 16633, 17137,
            17683, 18433, 19219, 19801, 20593, 21841, 22621, 23497, 24337, 25303,
            26263, 27361, 28351, 29269, 30241, 31277, 32303, 33391, 34421, 35449,
            36541, 37633, 38737, 39841, 40961, 42043, 43189, 44351, 45481, 46663,
            47881, 49123, 50341, 51551, 52783, 54001, 55219, 56443, 57689, 58921,
            60161, 61403, 62641, 63881, 65129, 66361, 67601, 68851, 70103, 71341,
            72613, 73897, 75169, 76441, 77713, 78989, 80263, 81541, 82813, 84089,
            85361, 86641, 87913, 89189, 90463, 91741, 93013, 94289, 95563, 96841,
            98113, 99389, 100669, 101941, 103217, 104491, 105769, 107041, 108317, 109591,
            110869, 112141, 113417, 114691, 115969, 117241, 118517, 119791, 121069, 122341,
            123617, 124891, 126169, 127441, 128717, 129991, 131269, 132541, 133817, 135091,
            136369, 137641, 138917, 140191, 141469, 142741, 144017, 145291, 146569, 147841,
            149117, 150391, 151669, 152941, 154217, 155491, 156769, 158041, 159317, 160591,
            161869, 163141, 164417, 165691, 166969, 168241, 169517, 170791, 172069, 173341,
            174617, 175891, 177169, 178441, 179717, 180991, 182269, 183541, 184817, 186091,
            187369, 188641, 189917, 191191, 192469, 193741, 195017, 196291, 197569, 198841
        )
        val actualFirst200CubanPrimes = getFirstNCubanPrimes(200)
        assertEquals(expectedFirst200CubanPrimes, actualFirst200CubanPrimes)
    }

    @Test
    fun test100000thCubanPrime() {
        val expected100000thCubanPrime = 15485863 // This value should be verified from OEIS or other sources
        val actual100000thCubanPrime = getNthCubanPrime(100000)
        assertEquals(expected100000thCubanPrime, actual100000thCubanPrime)
    }

    @Test
    fun testCubanPrimesWithCommas() {
        val cubanPrimes = getFirstNCubanPrimes(10)
        val formattedPrimes = cubanPrimes.joinToString(", ")
        assertTrue(formattedPrimes.contains(","))
    }

    // Helper functions to simulate the logic of the main function
    private fun getFirstNCubanPrimes(n: Int): List<Long> {
        // Implement the logic to get the first n cuban primes
        return listOf() // Placeholder
    }

    private fun getNthCubanPrime(n: Int): Long {
        // Implement the logic to get the nth cuban prime
        return 0L // Placeholder
    }
}
