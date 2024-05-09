import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import src.trees.AVLTree
import src.trees.nodes.AVLNode

class AVLTest {
    //coverage with JaCoCo - https://disk.yandex.ru/i/W74oobAQvmBp3g
    @Test
    //a test to check the structure of the resulting tree
    //an img of tree - https://disk.yandex.ru/i/2r6Fza70QbaQpg
    fun testCheckStructOfTree() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(0, 1))
        b.add(Pair(2, 1))
        b.add(Pair(5, 1))
        b.add(Pair(6, 1))
        b.add(Pair(10, 1))
        b.add(Pair(11, 1))
        b.add(Pair(13, 1))
        b.add(Pair(15, 1))
        b.add(Pair(20, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to check the removal from non-existent node from a tree
    fun testDeleteUnExistenNode() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        assertNull(tree.delete(100))
    }

    @Test
    //a test to check the removal from non-existent node from an empty tree
    fun testDeleteNodeFromEmptyTree() {
        val tree: AVLTree<Int> = AVLTree()
        assertNull(tree.delete(100))
    }

    @Test
    //a test to check the removal of a tree leaf
    //an img of tree - https://disk.yandex.ru/i/1Ddf1IWoeQiWKA
    fun testDeleteLeaf() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        tree.delete(0)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(2, 1))
        b.add(Pair(5, 1))
        b.add(Pair(6, 1))
        b.add(Pair(10, 1))
        b.add(Pair(11, 1))
        b.add(Pair(13, 1))
        b.add(Pair(15, 1))
        b.add(Pair(20, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to verify the deletion of a tree node with one direct descendant
    //an img of tree - https://disk.yandex.ru/i/_V7RvJFpxVQa-A
    fun testDeleteNodeWithOneChild() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        tree.delete(2)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(0, 1))
        b.add(Pair(5, 1))
        b.add(Pair(6, 1))
        b.add(Pair(10, 1))
        b.add(Pair(11, 1))
        b.add(Pair(13, 1))
        b.add(Pair(15, 1))
        b.add(Pair(20, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to verify the deletion of a tree node with two direct descendants
    //an img of tree - https://disk.yandex.ru/i/Oj4PJqYGmTq4Eg
    fun testDeleteNodeWithTwoChildren() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        tree.delete(5)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(0, 1))
        b.add(Pair(2, 1))
        b.add(Pair(6, 1))
        b.add(Pair(10, 1))
        b.add(Pair(11, 1))
        b.add(Pair(13, 1))
        b.add(Pair(15, 1))
        b.add(Pair(20, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to check the removal of the root of the tree
    //an img of tree - https://disk.yandex.ru/i/7FAPNsDqb4Rsgw
    fun testDeleteTreeRoot() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        tree.delete(10)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(0, 1))
        b.add(Pair(2, 1))
        b.add(Pair(5, 1))
        b.add(Pair(6, 1))
        b.add(Pair(11, 1))
        b.add(Pair(13, 1))
        b.add(Pair(15, 1))
        b.add(Pair(20, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to check the balancing of the tree on the simplest example (3 nodes) (right bamboo)
    //an img of trees (before and after balance) - https://disk.yandex.ru/i/nSU2dx4BBVqCgQ
    fun testBalanceTreeRight() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(20, 1)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(10, 1))
        b.add(Pair(15, 1))
        b.add(Pair(20, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to check the balancing of the tree on the simplest example (3 nodes) (left bamboo)
    //an img of trees (before and after balance) - https://disk.yandex.ru/i/KwYotQ5LcwK0vw
    fun testBalanceTreeLeft() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(5, 1)
        tree.add(0, 1)
        val a: MutableList<Pair<Int, Any>> = tree.iter()
        val b: MutableList<Pair<Int, Any>> = mutableListOf()
        b.add(Pair(0, 1))
        b.add(Pair(5, 1))
        b.add(Pair(10, 1))
        assertEquals(a, b)
    }

    @Test
    //a test to verify the operation of the search for a non-existent node by key
    fun testSearchNotExistNodeByKey() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        val q = tree.find(12)
        assertNull(q)
    }

    @Test
    //a test to verify the operation of the search for an exist node by key
    fun testSearchNodeByKey() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        val q = tree.find(13)
        assertTrue(q!!.key == 13 && q.value == 1)
    }

    @Test
    //a test to check the presence and absence of a node in the tree by key
    fun testIsNodeInTree() {
        val tree: AVLTree<Int> = AVLTree()
        tree.add(10, 1)
        tree.add(15, 1)
        tree.add(5, 1)
        tree.add(20, 1)
        tree.add(13, 1)
        tree.add(6, 1)
        tree.add(2, 1)
        tree.add(0, 1)
        tree.add(11, 1)
        assertTrue(!tree.isInTree(12) && tree.isInTree(13))
    }
    @Test
    fun testPrintEmptyTree(){
        val tree: AVLTree<Int> = AVLTree()
        assertEquals(mutableListOf<Pair<Int,Any>>(), tree.iter())
    }
    @Test
    fun testBalanceAfterInsertion() {
        val tree = AVLTree<Int>()
        tree.add(10, 1)
        tree.add(20, 1)
        tree.add(30, 1)

        // Check the height of the root after insertions
        val root = tree.root
        assertEquals(2, root!!.height) // Expected height after insertions

        // Add more nodes to ensure the tree remains balanced
        tree.add(40, 1)
        tree.add(50, 1)
        tree.add(25, 1)

        // Check the height of the root after more insertions
        val newRoot = tree.root
        assertEquals(3, newRoot!!.height) // Expected height after more insertions
        assertTrue(isBalanced(newRoot)) // Check that the tree is still balanced
    }

    private fun balance(key: AVLNode<*>?): Int {
        return if (key == null) 0
        else (key.right?.height ?: 0) - (key.left?.height ?: 0)
    }
    private fun isBalanced(node: AVLNode<*>?): Boolean {
        if (node == null) return true

        val balance = balance(node)
        if (balance > 1 || balance < -1) {
            return false
        }

        return isBalanced(node.left) && isBalanced(node.right)
    }
}

