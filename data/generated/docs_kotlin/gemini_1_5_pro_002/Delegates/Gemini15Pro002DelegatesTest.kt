import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DelegatorTest {

    @Test
    fun testOperationNoDelegateResponse() {
        val d = Delegate(false)
        val dd = Delegator(d)
        assertEquals("default implementation", dd.operation())
    }

    @Test
    fun testOperationWithDelegateResponse() {
        val d = Delegate(true)
        val dd = Delegator(d)
        assertEquals("delegate implementation", dd.operation())
    }

    interface EmptyThingable { } // Interface with no 'thing' method


    @Test
    fun testOperationWithEmptyDelegate() {
        class EmptyDelegate: EmptyThingable

        class DelegatorEmpty(d: EmptyThingable): EmptyThingable by d{
            fun operation() = thing() ?: "default implementation"

            private fun thing(): String? {
               return try {
                    (this as Thingable).thing()
                } catch (e: ClassCastException) {
                    null
                }
            }
        }

        val emptyDelegate = EmptyDelegate()
        val delegatorEmpty = DelegatorEmpty(emptyDelegate)
        assertEquals("default implementation", delegatorEmpty.operation())

    }



}
