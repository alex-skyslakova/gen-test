import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChristmasSundayTest {

    @Test
    fun testChristmasSundays() {
        val expectedYears = listOf(2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2100, 2106, 2117, 2123)
        val actualYears = mutableListOf<Int>()

        val calendar = GregorianCalendar(2008, Calendar.DECEMBER, 25)
        for (year in 2008..2121) {
            if (Calendar.SUNDAY == calendar[Calendar.DAY_OF_WEEK]) {
                actualYears.add(year)
            }
            calendar.add(Calendar.YEAR, 1)
        }

        assertEquals(expectedYears, actualYears)
    }

    @Test
    fun testSpecificYear2011() {
         val calendar = GregorianCalendar(2011, Calendar.DECEMBER, 25)
         assertEquals(Calendar.SUNDAY, calendar[Calendar.DAY_OF_WEEK])
    }


    @Test
    fun testSpecificYear2100(){
        val calendar = GregorianCalendar(2100, Calendar.DECEMBER, 25)
         assertEquals(Calendar.SUNDAY, calendar[Calendar.DAY_OF_WEEK])
    }

     @Test
    fun testSpecificYear2123(){ //out of range but we can still check the calculation is correct.
        val calendar = GregorianCalendar(2123, Calendar.DECEMBER, 25)
         assertEquals(Calendar.SUNDAY, calendar[Calendar.DAY_OF_WEEK])
    }

    @Test
    fun testNoOverflow(){
        val calendar = GregorianCalendar(2121, Calendar.DECEMBER, 25)
        //Adding another year shouldn't cause unexpected behavior or exceptions related to data type overflow.
        calendar.add(Calendar.YEAR, 1)
        assertTrue(calendar[Calendar.YEAR] == 2122) 
    }


}
