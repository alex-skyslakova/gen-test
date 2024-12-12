import kotlin.test.Test
import kotlin.test.assertEquals

class CherylBirthdayTest {

    private val choices = listOf(
        Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17), 
        Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
        Birthday(8, 15), Birthday(8, 17)
    )

    @Test
    fun testMonthUniqueIn() {
        assertEquals(false, Birthday(5, 15).monthUniqueIn(choices))
        assertEquals(false, Birthday(6, 17).monthUniqueIn(choices))
        assertEquals(false, Birthday(7, 14).monthUniqueIn(choices))
        assertEquals(false, Birthday(8, 14).monthUniqueIn(choices))
    }

    @Test
    fun testDayUniqueIn() {
        assertEquals(true, Birthday(5, 19).dayUniqueIn(choices))
        assertEquals(true, Birthday(6, 17).dayUniqueIn(choices))
        assertEquals(false, Birthday(7, 14).dayUniqueIn(choices))
        assertEquals(false, Birthday(8, 14).dayUniqueIn(choices))
    }

    @Test
    fun testMonthWithUniqueDayIn() {
        assertEquals(false, Birthday(5, 15).monthWithUniqueDayIn(choices))
        assertEquals(true, Birthday(6, 17).monthWithUniqueDayIn(choices))
        assertEquals(false, Birthday(7, 14).monthWithUniqueDayIn(choices))
        assertEquals(false, Birthday(8, 14).monthWithUniqueDayIn(choices))
    }

    @Test
    fun testCherylBirthdaySolution() {
        // Step 1: Filter out months that are unique
        var filtered = choices.filterNot { it.monthUniqueIn(choices) }
        assertEquals(7, filtered.size)

        // Step 2: Filter out months that have a unique day
        filtered = filtered.filterNot { it.monthWithUniqueDayIn(filtered) }
        assertEquals(3, filtered.size)

        // Step 3: Filter to find the unique day
        filtered = filtered.filter { it.dayUniqueIn(filtered) }
        assertEquals(1, filtered.size)

        // Step 4: Verify the final solution
        assertEquals("July 16", filtered[0].toString())
    }
}
