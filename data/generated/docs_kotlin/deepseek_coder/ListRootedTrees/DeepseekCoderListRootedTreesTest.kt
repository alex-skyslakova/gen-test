import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListRootedTreesTest {

    @Test
    fun testAppend() {
        val treeList = mutableListOf<Long>()
        val offset = IntArray(32) { if (it == 1) 1 else 0 }

        // Test appending a tree
        append(0L)
        assertEquals(1, treeList.size)
        assertEquals(1L, treeList[0])
    }

    @Test
    fun testShow() {
        // Test showing a tree
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        show(1L, 2) // Represents the tree ()
        assertEquals("()", outputStream.toString())

        outputStream.reset()
        show(3L, 4) // Represents the tree (())
        assertEquals("(())", outputStream.toString())

        System.setOut(System.out)
    }

    @Test
    fun testListTrees() {
        val treeList = mutableListOf<Long>()
        val offset = IntArray(32) { if (it == 1) 1 else 0 }

        // Manually populate treeList for testing
        treeList.add(1L) // ()
        treeList.add(3L) // (())
        offset[2] = 1
        offset[3] = 2

        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        listTrees(1)
        assertEquals("()\n", outputStream.toString())

        outputStream.reset()
        listTrees(2)
        assertEquals("(())\n", outputStream.toString())

        System.setOut(System.out)
    }

    @Test
    fun testAssemble() {
        val treeList = mutableListOf<Long>()
        val offset = IntArray(32) { if (it == 1) 1 else 0 }

        // Manually populate treeList for testing
        treeList.add(1L) // ()
        offset[2] = 1

        // Test assembling trees
        assemble(2, 0, 1, 0, 1)
        assertEquals(2, treeList.size)
        assertEquals(3L, treeList[1]) // (())
    }

    @Test
    fun testMakeTrees() {
        val treeList = mutableListOf<Long>()
        val offset = IntArray(32) { if (it == 1) 1 else 0 }

        // Test making trees
        makeTrees(1)
        assertEquals(1, treeList.size)
        assertEquals(1, offset[2])

        makeTrees(2)
        assertEquals(2, treeList.size)
        assertEquals(2, offset[3])
    }

    @Test
    fun testMain() {
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        // Test main function with valid input
        main(arrayOf("1"))
        assertTrue(outputStream.toString().contains("Number of 1-trees: 1"))
        assertTrue(outputStream.toString().contains("()"))

        outputStream.reset()
        main(arrayOf("2"))
        assertTrue(outputStream.toString().contains("Number of 2-trees: 1"))
        assertTrue(outputStream.toString().contains("(())"))

        System.setOut(System.out)
    }

    @Test
    fun testMainInvalidInput() {
        // Test main function with invalid input
        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf())
        }

        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf("invalid"))
        }

        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf("0"))
        }

        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf("13"))
        }
    }
}
