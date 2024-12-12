import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DelegatesTest {

    @Test
    fun `test delegator without delegate responding to thing`() {
        val delegate = Delegate(false)
        val delegator = Delegator(delegate)
        assertEquals("default implementation", delegator.operation())
    }

    @Test
    fun `test delegator with delegate responding to thing`() {
        val delegate = Delegate(true)
        val delegator = Delegator(delegate)
        assertEquals("delegate implementation", delegator.operation())
    }

    @Test
    fun `test delegate directly with response`() {
        val delegate = Delegate(true)
        assertEquals("delegate implementation", delegate.thing())
    }

    @Test
    fun `test delegate directly without response`() {
        val delegate = Delegate(false)
        assertEquals(null, delegate.thing())
    }
}
