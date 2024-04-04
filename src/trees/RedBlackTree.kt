class Node<T: Comparable<T>> {
    var data: T? = null
    var parent: Node<T>? = null
    var left: Node<T>? = null
    var right: Node<T>? = null
    var color: Int = 0
}

class RedBlackTree<T:Comparable<T>>{
    private var root: Node<T>?
    private val empty = Node<T>()

    private fun inOrder(node: Node<T>?, t: MutableList<T>) {
        if (node !== empty) {
            inOrder(node!!.left, t)
            t.add(node.data!!)
            inOrder(node.right, t)
        }
    }

    private fun searchNode(node: Node<T>?, key: T): Node<T>? {
        if (node === empty || key == node!!.data) {
            return node
        }

        if (key < node.data!!) {
            return searchNode(node.left, key)
        }
        return searchNode(node.right, key)
    }

    private fun fixDelete(x: Node<T>?) {
        var x = x
        var s: Node<T>?
        while (x !== root && x!!.color == 0) {
            if (x === x.parent!!.left) {
                s = x.parent!!.right
                if (s!!.color == 1) {
                    s.color = 0
                    x.parent!!.color = 1
                    leftRotate(x.parent)
                    s = x.parent!!.right
                }

                if (s!!.left!!.color == 0 && s.right!!.color == 0) {
                    s.color = 1
                    x = x.parent
                } else {
                    if (s.right!!.color == 0) {
                        s.left!!.color = 0
                        s.color = 1
                        rightRotate(s)
                        s = x.parent!!.right
                    }

                    s!!.color = x.parent!!.color
                    x.parent!!.color = 0
                    s.right!!.color = 0
                    leftRotate(x.parent)
                    x = root
                }
            } else {
                s = x.parent!!.left
                if (s!!.color == 1) {
                    s.color = 0
                    x.parent!!.color = 1
                    rightRotate(x.parent)
                    s = x.parent!!.left
                }

                if (s!!.right!!.color == 0 && s.right!!.color == 0) {
                    s.color = 1
                    x = x.parent
                } else {
                    if (s.left!!.color == 0) {
                        s.right!!.color = 0
                        s.color = 1
                        leftRotate(s)
                        s = x.parent!!.left
                    }

                    s!!.color = x.parent!!.color
                    x.parent!!.color = 0
                    s.left!!.color = 0
                    rightRotate(x.parent)
                    x = root
                }
            }
        }
        x!!.color = 0
    }

    private fun rbTransplant(u: Node<T>?, v: Node<T>?) {
        if (u!!.parent == null) {
            root = v
        } else if (u === u.parent!!.left) {
            u.parent!!.left = v
        } else {
            u.parent!!.right = v
        }
        v!!.parent = u.parent
    }

    private fun deleteNode(node: Node<T>?, key: T) {
        var node = node
        var z: Node<T>? = empty
        val x: Node<T>?
        var y: Node<T>?
        while (node !== empty) {
            if (node!!.data == key) {
                z = node
            }

            node = if (node.data!! <= key) {
                node.right
            } else {
                node.left
            }
        }

        if (z === empty) {
            return
        }

        y = z
        var yOriginalColor = y!!.color
        if (z!!.left === empty) {
            x = z!!.right
            rbTransplant(z, z.right)
        } else if (z!!.right === empty) {
            x = z!!.left
            rbTransplant(z, z.left)
        } else {
            y = minimum(z!!.right)
            yOriginalColor = y!!.color
            x = y.right
            if (y.parent === z) {
                x!!.parent = y
            } else {
                rbTransplant(y, y.right)
                y.right = z.right
                y.right!!.parent = y
            }

            rbTransplant(z, y)
            y.left = z.left
            y.left!!.parent = y
            y.color = z.color
        }
        if (yOriginalColor == 0) {
            fixDelete(x)
        }
    }

    private fun fixInsert(k: Node<T>) {
        var k: Node<T>? = k
        var u: Node<T>?
        while (k!!.parent!!.color == 1) {
            if (k.parent === k.parent!!.parent!!.right) {
                u = k.parent!!.parent!!.left
                if (u!!.color == 1) {
                    u.color = 0
                    k.parent!!.color = 0
                    k.parent!!.parent!!.color = 1
                    k = k.parent!!.parent
                } else {
                    if (k === k.parent!!.left) {
                        k = k.parent
                        rightRotate(k)
                    }
                    k!!.parent!!.color = 0
                    k.parent!!.parent!!.color = 1
                    leftRotate(k.parent!!.parent)
                }
            } else {
                u = k.parent!!.parent!!.right

                if (u!!.color == 1) {
                    u.color = 0
                    k.parent!!.color = 0
                    k.parent!!.parent!!.color = 1
                    k = k.parent!!.parent
                } else {
                    if (k === k.parent!!.right) {
                        k = k.parent
                        leftRotate(k)
                    }
                    k!!.parent!!.color = 0
                    k.parent!!.parent!!.color = 1
                    rightRotate(k.parent!!.parent)
                }
            }
            if (k === root) {
                break
            }
        }
        root!!.color = 0
    }

    init {
        empty.color = 0
        empty.left = null
        empty.right = null
        root = empty
    }

    fun iter(): List<T> {
        val t: MutableList<T> = ArrayList()
        inOrder(this.root, t)
        return t
    }

    fun searchNode(k: T): Node<T>? {
        return searchNode(this.root, k)
    }

    fun minimum(node: Node<T>?): Node<T>? {
        var node = node
        while (node!!.left !== empty) {
            node = node!!.left
        }
        return node
    }


    fun leftRotate(x: Node<T>?) {
        val y = x!!.right
        x.right = y!!.left
        if (y.left !== empty) {
            y.left!!.parent = x
        }
        y.parent = x.parent
        if (x.parent == null) {
            this.root = y
        } else if (x === x.parent!!.left) {
            x.parent!!.left = y
        } else {
            x.parent!!.right = y
        }
        y.left = x
        x.parent = y
    }

    fun rightRotate(x: Node<T>?) {
        val y = x!!.left
        x.left = y!!.right
        if (y.right !== empty) {
            y.right!!.parent = x
        }
        y.parent = x.parent
        if (x.parent == null) {
            this.root = y
        } else if (x === x.parent!!.right) {
            x.parent!!.right = y
        } else {
            x.parent!!.left = y
        }
        y.right = x
        x.parent = y
    }

    fun insert(key: T) {
        val node = Node<T>()
        node.parent = null
        node.data = key
        node.left = empty
        node.right = empty
        node.color = 1

        var y: Node<T>? = null
        var x = this.root

        while (x !== empty) {
            y = x
            x = if (node.data!! < x!!.data!!) {
                x.left
            } else {
                x.right
            }
        }

        node.parent = y
        if (y == null) {
            root = node
        } else if (node.data!! < y.data!!) {
            y.left = node
        } else {
            y.right = node
        }

        if (node.parent == null) {
            node.color = 0
            return
        }

        if (node.parent!!.parent == null) {
            return
        }

        fixInsert(node)
    }


    fun deleteNode(data: T) {
        deleteNode(this.root, data)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val bst = RedBlackTree<String>()
            bst.insert("huy")
            bst.insert("pizda")
            bst.insert("suka")
            bst.insert("pizdec")
            bst.insert("grob")
            var t = bst.iter()
            for (i in t.indices) {
                println(t[i].toString() + ' ')
            }
            bst.deleteNode("huy")
            t = bst.iter()
            if (bst.searchNode("pizdac") === bst.empty) {
                print("sfdsd")
            }
            for (i in t.indices) {
                println(t[i].toString())
            }
        }
    }
}