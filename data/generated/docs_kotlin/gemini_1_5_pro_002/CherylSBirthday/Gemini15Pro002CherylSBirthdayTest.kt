import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CherylBirthdayTest {

    @Test
    fun testMonthUniqueIn() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )
        assertTrue(Birthday(7, 14).monthUniqueIn(listOf(Birthday(6, 18), Birthday(7, 14))))
        assertFalse(Birthday(5, 15).monthUniqueIn(choices))
    }

    @Test
    fun testDayUniqueIn() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )
        assertTrue(Birthday(6, 17).dayUniqueIn(choices))
        assertFalse(Birthday(8, 14).dayUniqueIn(choices))

    }

    @Test
    fun testMonthWithUniqueDayIn() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )
        assertTrue(Birthday(6,18).monthWithUniqueDayIn(choices)) // June has unique day 17
        assertFalse(Birthday(8, 14).monthWithUniqueDayIn(choices))// August does not have a unique day


        val filtered = listOf(Birthday(7, 16), Birthday(7, 14),Birthday(8, 15),Birthday(8, 17))
        assertFalse(Birthday(7,16).monthWithUniqueDayIn(filtered))


    }


    @Test
    fun testCherylBirthdaySolution() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )

        var filtered = choices.filterNot { it.monthUniqueIn(choices) }
        filtered = filtered.filterNot { it.monthWithUniqueDayIn(filtered) }
        filtered = filtered.filter { it.dayUniqueIn(filtered) }
        filtered = filtered.filter { it.monthUniqueIn(filtered) }

        assertEquals(1, filtered.size)
        assertEquals(Birthday(7, 16), filtered[0])
    }
}
