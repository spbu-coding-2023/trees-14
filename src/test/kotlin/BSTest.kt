import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import src.trees.binarySearchTree
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class BSTest {
    @Test
    fun treeStructCheck() {
        val tree: binarySearchTree<Int> = binarySearchTree()
        tree.add(7, 1)
        tree.add(9, 1)
        tree.add(15, 1)
        tree.add(10, 1)
        tree.add(5, 1)
        tree.add(24, 1)
        tree.add(37, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        tree.add(100, 1)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(7, 1))
        b.add(Pair(5, 1))
        b.add(Pair(2, 1))
        b.add(Pair(0, 1))
        b.add(Pair(6, 1))
        b.add(Pair(9, 1))
        b.add(Pair(15, 1))
        b.add(Pair(10, 1))
        b.add(Pair(13, 1))
        b.add(Pair(11, 1))
        b.add(Pair(24, 1))
        b.add(Pair(37, 1))
        b.add(Pair(100, 1))
        assertEquals(a, b)
    }

    @Test //checking for deletion of a non-existent node in a tree
    fun DeleteUnExistenNode() {
        val tree: binarySearchTree<Int> = binarySearchTree()
        tree.add(10, 2)
        tree.add(15, 2)
        tree.add(5, 2)
        tree.add(20, 2)
        tree.add(13, 2)
        tree.add(6, 2)
        tree.add(2, 2)
        tree.add(0, 2)
        tree.add(11, 2)
        tree.add(17, 2)
        tree.add(50, 2)
        assertEquals(null, tree.delete(100))

    }

    @Test //check the operation of searching for a non-existent node by key
    fun SearchNotExistNodeByKey() {
        val tree: binarySearchTree<Int> = binarySearchTree()
        tree.add(10, 3)
        tree.add(15, 3)
        tree.add(5, 3)
        tree.add(20, 3)
        tree.add(13, 3)
        tree.add(6, 3)
        tree.add(2, 3)
        tree.add(0, 3)
        tree.add(11, 3)
        tree.add(17, 3)
        tree.add(50, 3)
        val nodeByKey = tree.find(69)
        assertEquals(null, nodeByKey)
    }

    @Test //Checking the operation of an existing node using a key
    fun SearchNodeByKey() {
        val tree: binarySearchTree<Int> = binarySearchTree()
        tree.add(10, 4)
        tree.add(15, 4)
        tree.add(5, 4)
        tree.add(20, 4)
        tree.add(13, 4)
        tree.add(6, 4)
        tree.add(2, 4)
        tree.add(0, 4)
        tree.add(11, 4)
        val findedNode = tree.find(13)
        assertEquals(true, findedNode!!.key == 13 && findedNode.value == 4)
    }

    @Test //checking tree root removal
    fun deleteTreeRoot() {
        val tree: binarySearchTree<Int> = binarySearchTree()
        tree.add(20, 5)
        tree.add(45, 5)
        tree.add(75, 5)
        tree.add(79, 5)
        tree.add(69, 5)
        tree.add(13, 5)
        tree.add(18, 5)
        tree.add(40, 5)
        tree.add(500, 5)
        tree.add(100, 5)
        tree.add(35, 5)
        tree.add(200, 5)
        tree.delete(200)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(20, 5))
        b.add(Pair(13, 5))
        b.add(Pair(18, 5))
        b.add(Pair(45, 5))
        b.add(Pair(40, 5))
        b.add(Pair(35, 5))
        b.add(Pair(75, 5))
        b.add(Pair(69, 5))
        b.add(Pair(79, 5))
        b.add(Pair(500, 5))
        b.add(Pair(100, 5))
        assertEquals(a, b)
    }

    private lateinit var tree: binarySearchTree<Int>

    @BeforeEach
    fun setUp() {
        tree = binarySearchTree()
    }

    @Test
    fun addElementsToTheTree() {
        tree.add(5, "five")
        tree.add(3, "three")
        tree.add(7, "seven")
        tree.add(2, "two")
        tree.add(4, "four")
        tree.add(6, "six")
        tree.add(8, "eight")
        assertEquals(5, tree.root?.key)
        assertEquals("five", tree.root?.value)
        assertEquals(3, tree.root?.left?.key)
        assertEquals("three", tree.root?.left?.value)
        assertEquals(7, tree.root?.right?.key)
        assertEquals("seven", tree.root?.right?.value)
    }

    @Test
    fun findAnElementInTheTree() {
        tree.add(5, "five")
        tree.add(3, "three")
        tree.add(7, "seven")

        val foundNode = tree.find(5)
        assertNotNull(foundNode)
        assertEquals(5, foundNode?.key)
        assertEquals("five", foundNode?.value)

        val notFoundNode = tree.find(10)
        assertNull(notFoundNode)
    }

    @Test
    fun deleteAnElementFromTheTree() {
        tree.add(5, "five")
        tree.add(3, "three")
        tree.add(7, "seven")

        val deletedNode = tree.delete(5)
        assertNotNull(deletedNode)
        assertEquals(5, deletedNode?.key)
        assertEquals("five", deletedNode?.value)

        val notFoundNode = tree.find(5)
        assertNull(notFoundNode)
    }

    @Test
    fun iterateOverTheTree() {
        tree.add(5, "five")
        tree.add(3, "three")
        tree.add(7, "seven")

        val expectedOrder = listOf(
            Pair(5, "five"),
            Pair(3, "three"),
            Pair(7, "seven")
        )
        val actualOrder = tree.iter()
        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun AddElementsToTheTree() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)
        tree.add(2, 20)
        tree.add(4, 40)
        tree.add(6, 60)
        tree.add(8, 80)

        assertEquals(5, tree.root?.key)
        assertEquals(50, tree.root?.value)
        assertEquals(3, tree.root?.left?.key)
        assertEquals(30, tree.root?.left?.value)
        assertEquals(7, tree.root?.right?.key)
        assertEquals(70, tree.root?.right?.value)
    }

    @Test
    fun FindAnElementInTheTree() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)

        val foundNode = tree.find(5)
        assertNotNull(foundNode)
        assertEquals(5, foundNode?.key)
        assertEquals(50, foundNode?.value)

        val notFoundNode = tree.find(10)
        assertNull(notFoundNode)
    }

    @Test
    fun deleteARootWithTwoChildren() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)
        tree.add(2, 20)
        tree.add(4, 40)
        tree.add(6, 60)
        tree.add(8, 80)

        val deletedNode = tree.delete(5)
        assertNotNull(deletedNode)
        assertEquals(5, deletedNode?.key)
        assertEquals(50, deletedNode?.value)

        val notFoundNode = tree.find(5)
        assertNull(notFoundNode)

        assertEquals(7, tree.root?.key)
        assertEquals(70, tree.root?.value)
        assertEquals(6, tree.root?.left?.key)
        assertEquals(60, tree.root?.left?.value)
        assertEquals(8, tree.root?.right?.key)
        assertEquals(80, tree.root?.right?.value)
        assertEquals(3, tree.root?.left?.left?.key)
        assertEquals(30, tree.root?.left?.left?.value)
        assertEquals(4, tree.root?.left?.left?.right?.key)
        assertEquals(40, tree.root?.left?.left?.right?.value)
        assertEquals(8, tree.root?.right?.key)
        assertEquals(80, tree.root?.right?.value)
    }

    @Test
    fun deleteANodeWithTwoChildren() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)
        tree.add(2, 20)
        tree.add(4, 40)
        tree.add(6, 60)
        tree.add(8, 80)

        val deletedNode = tree.delete(3)
        assertNotNull(deletedNode)
        assertEquals(3, deletedNode?.key)
        assertEquals(30, deletedNode?.value)

        val notFoundNode = tree.find(3)
        assertNull(notFoundNode)

        assertEquals(5, tree.root?.key)
        assertEquals(50, tree.root?.value)
        assertEquals(4, tree.root?.left?.key)
        assertEquals(40, tree.root?.left?.value)
        assertEquals(7, tree.root?.right?.key)
        assertEquals(70, tree.root?.right?.value)
        assertEquals(2, tree.root?.left?.left?.key)
        assertEquals(20, tree.root?.left?.left?.value)
    }

    @Test
    fun DeleteANodeWithOneChild() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)
        tree.add(6, 60)

        val deletedNode = tree.delete(7)
        assertNotNull(deletedNode)
        assertEquals(7, deletedNode?.key)
        assertEquals(70, deletedNode?.value)

        val notFoundNode = tree.find(7)
        assertNull(notFoundNode)

        assertEquals(5, tree.root?.key)
        assertEquals(50, tree.root?.value)
        assertEquals(3, tree.root?.left?.key)
        assertEquals(30, tree.root?.left?.value)
        assertEquals(6, tree.root?.right?.key)
        assertEquals(60, tree.root?.right?.value)
    }

    @Test
    fun IterateOverTheTree() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)

        val expectedOrder = listOf(
            Pair(5, 50),
            Pair(3, 30),
            Pair(7, 70)
        )

        val actualOrder = tree.iter()
        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun DeleteANodeWithOneChildWithFloat() {
        tree.add(5, "five")
        tree.add(3, "three")
        tree.add(7, "seven")
        tree.add(6, "six")

        val deletedNode = tree.delete(7)
        assertNotNull(deletedNode)
        assertEquals(7, deletedNode?.key)
        assertEquals("seven", deletedNode?.value)

        val notFoundNode = tree.find(7)
        assertNull(notFoundNode)

        assertEquals(5, tree.root?.key)
        assertEquals("five", tree.root?.value)
        assertEquals(3, tree.root?.left?.key)
        assertEquals("three", tree.root?.left?.value)
        assertEquals(6, tree.root?.right?.key)
        assertEquals("six", tree.root?.right?.value)
    }

    @Test
    fun deleteANodeWithNoChild() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)
        tree.add(2, 20)
        tree.add(4, 40)
        tree.add(6, 60)
        tree.add(8, 80)

        val deletedNode = tree.delete(2)
        assertNotNull(deletedNode)
        assertEquals(2, deletedNode?.key)
        assertEquals(20, deletedNode?.value)

        val notFoundNode = tree.find(2)
        assertNull(notFoundNode)

        assertEquals(5, tree.root?.key)
        assertEquals(50, tree.root?.value)
        assertEquals(3, tree.root?.left?.key)
        assertEquals(30, tree.root?.left?.value)
        assertEquals(7, tree.root?.right?.key)
        assertEquals(70, tree.root?.right?.value)
        assertEquals(4, tree.root?.left?.right?.key)
        assertEquals(40, tree.root?.left?.right?.value)
    }

    @Test
    fun deleteARootFromARightBamboo() {
        tree.add(5, 50)
        tree.add(7, 70)
        tree.add(8, 80)

        val deletedNode = tree.delete(5)
        assertNotNull(deletedNode)
        assertEquals(5, deletedNode?.key)
        assertEquals(50, deletedNode?.value)

        val notFoundNode = tree.find(5)
        assertNull(notFoundNode)

        assertEquals(7, tree.root?.key)
        assertEquals(70, tree.root?.value)
        assertEquals(8, tree.root?.right?.key)
        assertEquals(80, tree.root?.right?.value)
    }

    @Test
    fun deleteARootFromALeftBamboo() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(2, 20)

        val deletedNode = tree.delete(5)
        assertNotNull(deletedNode)
        assertEquals(5, deletedNode?.key)
        assertEquals(50, deletedNode?.value)

        val notFoundNode = tree.find(5)
        assertNull(notFoundNode)

        assertEquals(3, tree.root?.key)
        assertEquals(30, tree.root?.value)
        assertEquals(2, tree.root?.left?.key)
        assertEquals(20, tree.root?.left?.value)
    }

    @Test
    fun deleteANodeWithTwoChildren2() {
        tree.add(5, 50)
        tree.add(3, 30)
        tree.add(7, 70)
        tree.add(2, 20)
        tree.add(4, 40)
        tree.add(6, 60)
        tree.add(8, 80)

        val deletedNode = tree.delete(7)
        assertNotNull(deletedNode)
        assertEquals(7, deletedNode?.key)
        assertEquals(70, deletedNode?.value)

        val notFoundNode = tree.find(7)
        assertNull(notFoundNode)

        assertEquals(5, tree.root?.key)
        assertEquals(50, tree.root?.value)
        assertEquals(3, tree.root?.left?.key)
        assertEquals(30, tree.root?.left?.value)
        assertEquals(8, tree.root?.right?.key)
        assertEquals(80, tree.root?.right?.value)
        assertEquals(2, tree.root?.left?.left?.key)
        assertEquals(20, tree.root?.left?.left?.value)
    }
}