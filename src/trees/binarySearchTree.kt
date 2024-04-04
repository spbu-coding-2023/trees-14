package trees

import trees.nodes.BinarySearchNode


class binarySearchTree<T : Comparable<T>> : BaseTree<T, BinarySearchNode<T>>() {
    override var root: BinarySearchNode<T>? = null
    override fun createNode(data: T): BinarySearchNode<T> = BinarySearchNode(data)

    override fun add(data: T): BinarySearchNode<T> {
        if (root == null) {
            root = createNode(data)
            return root!!
        }
        val newNode = createNode(data)
        var curNode = root
        while (true) {
            if (curNode == null)
                return newNode
            if (curNode.data > data) {
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

    override fun find(data: T): BinarySearchNode<T>? {
        if (root == null) {
            return null
        }
        var curNode = root
        while (true) {

            if (curNode!!.data > data) {
                if (curNode.left != null)
                    curNode = curNode.left
                else
                    return null
            }
            if (curNode!!.data < data) {
                if (curNode.right != null)
                    curNode = curNode.right
                else
                    return null
            } else
                return curNode
        }
    }

    override fun delete(data: T): BinarySearchNode<T>? {
        val node = find(data) ?: return null
        if (node.right == null && node.left == null) {
            if (node.parent == null) {
                root = null
            } else if (node.parent!!.data > data) {
                node.parent!!.left = null
            } else
                node.parent!!.right = null
            return node
        } else if (node.right != null && node.left != null) {
            val righ = node.right // >node
            val lef = node.left // < node
            if (node.parent == null) {
                root = righ
                var curNode = root
                while (true) {
                    if (curNode!!.left == null) {
                        curNode.left = lef
                        break
                    } else
                        curNode = curNode.left
                }
            } else {
                if (node.parent!!.data > data) {
                    node.parent!!.left = righ
                    var curNode = righ
                    while (true) {
                        if (curNode!!.left == null) {
                            curNode.left = lef
                            break
                        } else
                            curNode = curNode.left
                    }
                } else {
                    node.parent!!.right = righ
                    var curNode = righ
                    while (true) {
                        if (curNode!!.left == null) {
                            curNode.left = lef
                            break
                        } else
                            curNode = curNode.left
                    }
                }
            }

        } else if (node.right != null || node.left != null) {
            if (node.parent == null) {
                root = node.left ?: node.right
                return node
            } else if (node.right != null) {
                if (node.parent!!.data > data)
                    node.parent!!.left = node.right
                else
                    node.parent!!.right = node.right
            } else {
                if (node.parent!!.data > data)
                    node.parent!!.left = node.left
                else
                    node.parent!!.right = node.left
            }
        }
        return node
    }

    var out = mutableListOf<T>()
    override fun iterator(): MutableList<T> {
        out = mutableListOf<T>()
        inOrder(root)
        return out
    }

    private fun inOrder(root: BinarySearchNode<T>?) {
        if (root == null) {
            return
        }

        if (root.left != null) inOrder(root.left)
        out.add(root.value)
        if (root.right != null) inOrder(root.right)
    }

}
