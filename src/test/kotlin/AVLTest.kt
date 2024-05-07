import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import src.trees.AVLTree

class AVLTest {
    @Test
    //a test to check the structure of the resulting tree
    //an img of tree - https://disk.yandex.ru/i/2r6Fza70QbaQpg
    fun testCheckStructOfTree() {
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

}
