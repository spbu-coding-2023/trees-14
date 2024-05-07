package src.trees

import src.trees.nodes.BinarySearchNode

class binarySearchTree<T : Comparable<T>> : BaseTree<T, BinarySearchNode<T>>() {
    override var root: BinarySearchNode<T>? = null
    override fun createNode(key: T, data: Any): BinarySearchNode<T> = BinarySearchNode(key, data)
    override fun add(key: T, data: Any): BinarySearchNode<T> {
        if (root == null) {
            root = createNode(key, data)
            return root!!
        }
        val newNode = createNode(key, data)
        var curNode = root
        val temp = find(key)
        if (temp != null) {
            temp.value = data
            return temp!!
        }

        while (true) {
            if (curNode == null)
                return newNode
            if (curNode.key > key) {
                if (curNode.left == null) {
                    curNode.left = newNode
                    newNode.parent = curNode
                    return newNode
                } else
                    curNode = curNode.left
            } else {
                if (curNode.right == null) {
                    curNode.right = newNode
                    newNode.parent = curNode
                    return newNode
                } else
                    curNode = curNode.right
            }
        }
    }

    override fun find(key: T): BinarySearchNode<T>? {
        if (root == null) {
            return null
        }
        var curNode = root
        while (true) {
            if (curNode!!.key > key) {
                if (curNode.left != null)
                    curNode = curNode.left
                else
                    return null
            } else if (curNode!!.key < key) {
                if (curNode.right != null)
                    curNode = curNode.right
                else
                    return null
            } else
                return curNode
        }
    }

    override fun delete(key: T): BinarySearchNode<T>? {
        val node = find(key)
        if (node == null)
            return null
        if (node.right == null && node.left == null) {
            if (node.parent == null) {
                root = null
            } else if (node.parent!!.key > key) {
                node.parent!!.left = null
            } else
                node.parent!!.right = null
            return node
        } else if (node.right != null && node.left != null) {
            val righ = node.right // >node
            val lef = node.left // <node
            righ!!.parent = null
            lef!!.parent = null
            if (node.parent == null) {
                root = righ
                var curNode = righ
                while (curNode!!.left != null) {
                    curNode = curNode.left
                }
                lef.parent = curNode
                curNode.left = lef
            } else {
                if (node.parent!!.key > key) {
                    righ!!.parent = node.parent
                    node.parent!!.left = righ
                    var curNode = righ
                    while (true) {
                        if (curNode!!.left == null) {
                            lef!!.parent = curNode
                            curNode.left = lef
                            break
                        }
                        curNode = curNode.left
                    }
                } else {
                    righ.parent = node.parent
                    node.parent!!.right = righ
                    var curNode = righ
                    while (true) {
                        if (curNode!!.left == null) {
                            lef!!.parent = curNode
                            curNode.left = lef
                            break
                        } else
                            curNode = curNode.left
                    }
                }
            }

        } else {
            if (node.parent == null) {
                root = node.left ?: node.right
                root!!.parent = null
                return node
            } else if (node.right != null) {
                if (node.parent!!.key > key) {
                    node.right!!.parent = node.parent
                    node.parent!!.left = node.right
                } else {
                    node.right!!.parent = node.parent
                    node.parent!!.right = node.right
                }
            } else {
                if (node.parent!!.key > key) {
                    node.left!!.parent = node.parent
                    node.parent!!.left = node.left
                } else {
                    node.left!!.parent = node.parent
                    node.parent!!.right = node.left
                }
            }
        }
        return node
    }

    var out = mutableListOf<Pair<T, Any>>()
    override fun iter(): MutableList<Pair<T, Any>> {
        out = mutableListOf()
        inOrder(root)
        return out
    }

    private fun inOrder(node: BinarySearchNode<T>?) {
        if (node == null)
            return
        out.add(Pair(node.key, node.value))
        if (node.left != null)
            inOrder(node.left)
        if (node.right != null)
            inOrder(node.right)
    }
}