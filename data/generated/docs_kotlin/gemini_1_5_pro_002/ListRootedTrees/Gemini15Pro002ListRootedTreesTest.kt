import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ListRootedTreesTest {

    @Test
    fun testMakeTrees1() {
        makeTrees(1)
        assertEquals(1, offset[2])
        assertEquals(0, treeList[0])
    }

    @Test
    fun testMakeTrees2() {
        makeTrees(2)
        assertEquals(1, offset[2])
        assertEquals(2, offset[3])
        assertEquals(0, treeList[0])
        assertEquals(1, treeList[1])
    }

    @Test
    fun testMakeTrees3() {
        makeTrees(3)
        assertEquals(2, offset[3])
        assertEquals(4, offset[4])
        assertEquals(listOf<Long>(0, 1, 3, 2), treeList.subList(0, 4))
    }

    @Test
    fun testMakeTrees4() {
        makeTrees(4)
        assertEquals(4, offset[4])
        assertEquals(9, offset[5])
    }

    @Test
    fun testMakeTrees5() {
        makeTrees(5)
        assertEquals(9, offset[5])
        assertEquals(20, offset[6])
    }


    @Test
    fun testListTrees1() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        makeTrees(1)
        listTrees(1)

        assertEquals("()\n", outputStream.toString())
    }

    @Test
    fun testListTrees2() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        makeTrees(2)
        listTrees(2)

        assertEquals("(())\n", outputStream.toString())
    }

    @Test
    fun testListTrees3() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        makeTrees(3)
        listTrees(3)

        assertEquals("((()))\n" +
                "(()())\n", outputStream.toString())
    }

    @Test
    fun testShow() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        show(1L, 4)

        assertEquals("(())", outputStream.toString())

        outputStream.reset()
        show(3L, 6)
        assertEquals("((()))", outputStream.toString())

        outputStream.reset()
        show(2L, 6)

        assertEquals("(()())", outputStream.toString())
    }


    @Test
    fun testMainInvalidInput() {
        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf())
        }
        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf("abc"))
        }
        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf("0"))
        }
        assertThrows(IllegalArgumentException::class.java) {
            main(arrayOf("13"))
        }
    }

    @Test
    fun testMainValidInput() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("1"))

        assertTrue(outputStream.toString().contains("Number of 1-trees: 1"))
        assertTrue(outputStream.toString().contains("()\n"))

        outputStream.reset()
        main(arrayOf("2"))
        assertTrue(outputStream.toString().contains("Number of 2-trees: 1"))
        assertTrue(outputStream.toString().contains("(())\n"))
    }

}
