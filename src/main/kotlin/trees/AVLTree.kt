package src.trees

import src.trees.nodes.AVLNode
import kotlin.math.max

internal class AVLTree <T : Comparable<T>> : BaseTree<T, AVLNode<T>>(){

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
    override fun find(key: T): AVLNode<T>? {
        return findNode(root,key)
    }


    private fun insert(root: AVLNode<T>?, key: T, data:Any): AVLNode<T> {
        if (root == null) return createNode(key, data)
        else if (key < root.key!!) root.left = insert(root.left, key, data)
        else root.right = insert(root.right, key, data)

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
        else if (key < root.key!!) root.left = remove(root.left, key)
        else if (key > root.key!!) root.right = remove(root.right, key)
        else {
            if (root.right == null) root = root.left
            else if (root.left == null) root = root.right
            else {
                val temp = Successor(root.right)
                root.key = temp!!.key
                root.value = temp.value
                root.right = remove(root.right, root.key!!)
            }
        }

        return if (root == null) root
        else  // Balances the tree after deletion
            balanceTree(root)
    }


    private fun findNode(root: AVLNode<T>?, key: T): AVLNode<T>? {
        if (root == null || key == root.key) return root

        return if (key < root.key!!) findNode(root.left, key)
        else findNode(root.right, key)
    }

    override fun add(key: T, data: Any): AVLNode<T>? {
        return adding(key, data)

    }

    private fun adding(key: T, data: Any): AVLNode<T>? {
        val temp = findNode(root, key)
        if ( temp == null) {
            root = insert(root, key, data)
            return root
        }
        temp.value = data
        return root
    }

    fun isInTree(key: T): Boolean {
        return if (findNode(root, key) == null) false
        else true
    }

    override fun delete(key: T): AVLNode<T>? {
        if (findNode(root, key) != null) {
            root = remove(root, key)
            return root
        }
        return null
    }
    private var out = mutableListOf<Pair<T,Any>>()
    private fun inOrder(root: AVLNode<T>?) {
        if (root == null) {
            return
        }

        if (root.left != null) inOrder(root.left)
        out.add(Pair(root.key!!, root.value!!))
        if (root.right != null) inOrder(root.right)
    }

    override fun createNode(key: T, data:Any): AVLNode<T> = AVLNode(key,data)


    override fun iter(): MutableList<Pair<T,Any>> {
        out = mutableListOf<Pair<T,Any>>()
        inOrder(root)
        return out
    }
}
