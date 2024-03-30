package trees;

import trees.nodes.AVLNode
import kotlin.math.max


class AVLTree<T : Comparable<T>>(data: T) : BaseTree<T, AVLNode<T>>() {

    override fun createNode(data: T) = AVLNode(data)

    override fun add(data: T): AVLNode<T> {
        val newNode = createNode(data)
        if (root == null) {
            root = newNode
            return newNode
        } // after this root always exist (зуб даю)

        var curNode = root ?: throw ExeptionAllGoesOnPizde("pizda...")
        while (true) {
            if (newNode.data > curNode.data) {
                if (curNode.right == null) {
                    newNode.parent = curNode
                    curNode.right = newNode
                    fixHeights(curNode)
                    root = balance(root!!) //вопросы по скилу
                    return newNode
                }
                curNode = curNode.right ?: throw ExeptionAllGoesOnPizde("unreal...")
            } else {
                if (curNode.left == null) {
                    newNode.parent = curNode
                    curNode.left = newNode
                    fixHeights(curNode)
                    root = balance(root!!) //вопросы по скилу
                    return newNode
                }
                curNode = curNode.left ?: throw ExeptionAllGoesOnPizde("unreal...")
            }
        }
    }

    private fun fixOneHeight(node: AVLNode<T>) {
        val hR = node.right?.height ?: 0
        val hL = node.left?.height ?: 0
        node.height = max(hR, hL) + 1
    }

    private fun fixHeights(node: AVLNode<T>) {
        if ((node.right?.height ?: 0) == (node.left?.height ?: 0))
            return

        fixOneHeight(node)
        if (node.parent == null)
            return

        fixHeights(node.parent ?: return)
    }

    override fun find(data: T): AVLNode<T>? {
        var curNode = root
        for (i in 1..(root?.height ?: 0)) {
            if (curNode != null)
                if (data > curNode.data)
                    curNode = curNode.right
                else if (data < curNode.data)
                    curNode = curNode.left
                else
                    return curNode
        }
        return null
    }

    override fun delete(data: T) {
        TODO("Not yet implemented")
    }

    override fun print() {
        TODO("Not yet implemented")
    }

    fun ExeptionAllGoesOnPizde(s: String): Throwable {
        TODO("Not yet implemented")
    }

    private fun balance(node: AVLNode<T>): AVLNode<T>? {
        fixOneHeight(node)
        if (balanceFactor(node) == 2) {
            if (node.right != null && balanceFactor(node.right!!) < 0) { //вопросы по скилу
                node.right = rotateRight(node.right!!)
            }
            return rotateLeft(node)
        }
        if (balanceFactor(node) == -2) {
            if (node.left != null && balanceFactor(node.left!!) > 0) { //вопросы по скилу
                node.left = rotateLeft(node.left!!)
            }
            return rotateRight(node)
        }
        return node // no balancing needed
    }

    private fun balanceFactor(node: AVLNode<T>): Int {
        return (node.right?.height ?: 0) - (node.left?.height ?: 0)
    }

    private fun rotateRight(P: AVLNode<T>): AVLNode<T>? {
        val Q = P.left
        if (Q != null) {
            P.left = Q.right
            Q.right = P
            fixOneHeight(P)
            fixOneHeight(Q)
        }
        return Q
    }

    private fun rotateLeft(Q: AVLNode<T>): AVLNode<T>? {
        val P = Q.right
        if (P != null) {
            Q.right = P.left
            P.left = Q
            fixOneHeight(Q)
            fixOneHeight(P)
        }
        return P
    }
}
