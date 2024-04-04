package trees

import trees.nodes.AVLNode
import kotlin.math.max

internal class AVLTree <T : Comparable<T>>() : BaseTree<T, AVLNode<T>>(){

    //
    //returns the height of the node
    private fun heihgtN(key: AVLNode<T>?): Int {
        return key?.height ?: 0
    }


    // Balance computes the balance factor of the node
    private fun Balance(key: AVLNode<T>?): Int {
        return if (key == null) 0
        else heihgtN(key.right) - heihgtN(key.left)
    }


    // updateHeight updates the height of the node
    private fun updateHeight(key: AVLNode<T>?) {
        val l = heihgtN(key!!.left)
        val r = heihgtN(key.right)

        key.height = (max(l.toDouble(), r.toDouble()) + 1).toInt()
    }

    private fun rotateLeft(x: AVLNode<T>?): AVLNode<T> {
        val y = x!!.right
        val T2 = y!!.left

        y.left = x
        x.right = T2

        updateHeight(x)
        updateHeight(y)

        return y
    }

    private fun rotateRight(y: AVLNode<T>?): AVLNode<T> {
        val x = y!!.left
        val T2 = x!!.right

        x.right = y
        y.left = T2

        updateHeight(y)
        updateHeight(x)

        return x
    }

    // balanceTree balances the tree using rotations after an insertion or deletion
    private fun balanceTree(root: AVLNode<T>): AVLNode<T> {
        updateHeight(root)

        val balance = Balance(root)

        if (balance > 1) //R
        {
            if (Balance(root.right) < 0) //RL
            {
                root.right = rotateRight(root.right)
                return rotateLeft(root)
            } else  //RR
                return rotateLeft(root)
        }

        if (balance < -1) //L
        {
            if (Balance(root.left) > 0) //LR
            {
                root.left = rotateLeft(root.left)
                return rotateRight(root)
            } else  //LL
                return rotateRight(root)
        }

        return root
    }

    override var root: AVLNode<T>? = null
    override fun find(data: T): AVLNode<T>? {
        return findNode(root,data)
    }


    private fun insert(root: AVLNode<T>?, key: T): AVLNode<T> {
        if (root == null) return AVLNode(key)
        else if (key < root.value) root.left = insert(root.left, key)
        else root.right = insert(root.right, key)

        // Balances the tree after BST Insertion
        return balanceTree(root)
    }

    // Successor returns the next largest node
    private fun Successor(root: AVLNode<T>?): AVLNode<T>? {
        return if (root!!.left != null) Successor(root.left)
        else root
    }


    private fun remove(node: AVLNode<T>?, key: T): AVLNode<T>? {
        // Performs standard BST Deletion
        var root = node
        if (root == null) return root
        else if (key < root.value) root.left = remove(root.left, key)
        else if (key > root.value) root.right = remove(root.right, key)
        else {
            if (root.right == null) root = root.left
            else if (root.left == null) root = root.right
            else {
                val temp = Successor(root.right)
                root.value = temp!!.value
                root.right = remove(root.right, root.value)
            }
        }

        return if (root == null) root
        else  // Balances the tree after deletion
            balanceTree(root)
    }


    private fun findNode(root: AVLNode<T>?, key: T): AVLNode<T>? {
        if (root == null || key == root.value) return root

        return if (key < root.value) findNode(root.left, key)
        else findNode(root.right, key)
    }

    override fun add(data: T): AVLNode<T>?{
        return adding(data)

    }

    private fun adding(data: T): AVLNode<T>? {
        if (findNode(root, data) == null) {
            root = insert(root, data)
            //println("Inserted")
            return root
        }
        //println("Key already in tree")
        return null
    }

    fun search(key: T): Int {
        return if (findNode(root, key) == null) 0
        else 1
    }

    override fun delete(data: T): AVLNode<T>? {
        if (findNode(root, data) != null) {
            root = remove(root, data)
            return root
        }
        //println("key not in tree")
        return null
    }
    private var out = mutableListOf<T>()
    private fun inOrder(root: AVLNode<T>?) {
        if (root == null) {
            return
        }

        if (root.left != null) inOrder(root.left)
        out.add(root.value)
        if (root.right != null) inOrder(root.right)
    }

    override fun createNode(data: T): AVLNode<T> = AVLNode(data)


    override fun iterator(): MutableList<T> {
        out = mutableListOf<T>()
        inOrder(root)
        return out
    }
}
