import org.junit.Test
import kotlin.test.assertEquals

class DelegatorTest {

    @Test
    fun `test operation without delegate`() {
        val delegator = Delegator(Delegate(false))
        val result = delegator.operation()
        assertEquals("default implementation", result)
    }

    @Test
    fun `test operation with delegate that does not implement thing`() {
        val delegator = Delegator(Delegate(false))
        val result = delegator.operation()
        assertEquals("default implementation", result)
    }

    @Test
    fun `test operation with delegate that implements thing`() {
        val delegator = Delegator(Delegate(true))
        val result = delegator.operation()
        assertEquals("delegate implementation", result)
    }
}
