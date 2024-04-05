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

        if (find(data) != null) {
            //print("hi")
            //println(data)
            return find(data)!!
        }
        //println(data)
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
            else if (curNode!!.data < data) {
                if (curNode.right != null)
                    curNode = curNode.right
                else
                    return null
            } else
                return curNode
        }
    }

    override fun delete(data: T): BinarySearchNode<T>? {

        val node = find(data)
        if (node == null)
            return null
        if (node.right == null && node.left == null) {
            if (node.parent == null) {
                root = null
            } else if (node.parent!!.data > data) {
                node.parent!!.left = null
            } else
                node.parent!!.right = null
            return node
        }
        else if (node.right != null && node.left != null) {

            val righ = node.right // >node
            val lef = node.left // <node
            if (node.parent == null) {
                root = righ
                root!!.parent = null
                var curNode = righ
                while (curNode!!.left != null) {
                    curNode = curNode.left
                }
                lef!!.parent=curNode
                curNode.left = lef

            }
            else {
                if (node.parent!!.data > data) {
                    righ!!.parent = node.left
                    node.parent!!.left = righ
                    var curNode = righ
                    while (true) {
                        if (curNode!!.left == null) {
                            lef!!.parent=curNode
                            curNode.left = lef

                            break
                        }
                        curNode = curNode.left

                    }
                } else {
                    node.parent!!.right = righ
                    var curNode = righ
                    while (true) {
                        if (curNode!!.left == null) {
                            lef!!.parent=curNode
                            curNode.left = lef
                            break
                        } else
                            curNode = curNode.left
                    }
                }
            }

        } else{
            if (node.parent == null) {
                root = node.left ?: node.right
                root!!.parent = null
                return node
            } else if (node.right != null) {
                if (node.parent!!.data > data){
                    node.right!!.parent = node.parent
                    node.parent!!.left = node.right
                }
                else{
                    node.right!!.parent = node.parent
                    node.parent!!.right = node.right}
            } else {
                if (node.parent!!.data > data){
                    node.left!!.parent = node.parent
                    node.parent!!.left = node.left}
                else{
                    node.left!!.parent = node.parent
                    node.parent!!.right = node.left}
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

    private fun inOrder(node: BinarySearchNode<T>?) {
        if (node == null)
            return
        out.add(node.value)
        if (node.left != null)
            inOrder(node.left)
//        out.add(root.value)
        if (node.right != null)
            inOrder(node.right)
    }

}
