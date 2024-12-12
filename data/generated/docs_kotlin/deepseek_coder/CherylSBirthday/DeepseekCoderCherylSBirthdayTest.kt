import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CherylSBirthdayTest {

    @Test
    fun testMonthUniqueIn() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )

        // Test unique month
        assertEquals(true, Birthday(5, 15).monthUniqueIn(choices))
        assertEquals(false, Birthday(7, 14).monthUniqueIn(choices))
    }

    @Test
    fun testDayUniqueIn() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )

        // Test unique day
        assertEquals(true, Birthday(5, 15).dayUniqueIn(choices))
        assertEquals(false, Birthday(7, 14).dayUniqueIn(choices))
    }

    @Test
    fun testMonthWithUniqueDayIn() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )

        // Test month with unique day
        assertEquals(true, Birthday(5, 15).monthWithUniqueDayIn(choices))
        assertEquals(false, Birthday(7, 14).monthWithUniqueDayIn(choices))
    }

    @Test
    fun testCherylSBirthday() {
        val choices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        )

        // Albert knows the month but doesn't know the day.
        var filtered = choices.filterNot { it.monthUniqueIn(choices) }
        assertEquals(listOf(
            Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        ), filtered)

        // Albert also knows that Bernard doesn't know the answer.
        filtered = filtered.filterNot { it.monthWithUniqueDayIn(filtered) }
        assertEquals(listOf(
            Birthday(7, 14), Birthday(8, 14),
            Birthday(8, 15), Birthday(8, 17)
        ), filtered)

        // Bernard now knows the answer.
        filtered = filtered.filter { it.dayUniqueIn(filtered) }
        assertEquals(listOf(
            Birthday(8, 15), Birthday(8, 17)
        ), filtered)

        // Albert now knows the answer too.
        filtered = filtered.filter { it.monthUniqueIn(filtered) }
        assertEquals(listOf(
            Birthday(8, 17)
        ), filtered)

        assertEquals("August 17", filtered[0].toString())
    }

    @Test
    fun testInvalidInput() {
        val invalidChoices = listOf(
            Birthday(5, 15), Birthday(5, 16), Birthday(5, 19), Birthday(6, 17),
            Birthday(6, 18), Birthday(7, 14), Birthday(7, 16), Birthday(8, 14),
            Birthday(8, 15) // Missing one date to make it invalid
        )

        assertFailsWith<AssertionError> {
            // Albert knows the month but doesn't know the day.
            var filtered = invalidChoices.filterNot { it.monthUniqueIn(invalidChoices) }
            // Albert also knows that Bernard doesn't know the answer.
            filtered = filtered.filterNot { it.monthWithUniqueDayIn(filtered) }
            // Bernard now knows the answer.
            filtered = filtered.filter { it.dayUniqueIn(filtered) }
            // Albert now knows the answer too.
            filtered = filtered.filter { it.monthUniqueIn(filtered) }

            if (filtered.size == 1)
                println("Cheryl's birthday is ${filtered[0]}")
            else
                println("Something went wrong!")
        }
    }
}
