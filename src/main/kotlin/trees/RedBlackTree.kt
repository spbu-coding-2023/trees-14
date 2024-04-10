import trees.nodes.RBNode


class RedBlackTree<T : Comparable<T>> {
    private var root: RBNode<T>?
    private val empty = RBNode<T>()

    private fun inOrder(node: RBNode<T>?, t: MutableList<Pair<T, Any>>) {
        if (node !== empty) {
            if (node != null) {
                t.add(Pair(node.key!!, node.data!!))
            }
            inOrder(node!!.left, t)
            inOrder(node.right, t)
        }
    }

    private fun find(node: RBNode<T>?, key: T): RBNode<T>? {
        if (node === empty || key == node!!.key) {
            if (node === empty) {
                return null
            }
            return node
        }

        if (key < node.key!!) {
            return find(node.left, key)
        }
        return find(node.right, key)
    }

    private fun fixDelete(x: RBNode<T>?) {
        var x = x
        var s: RBNode<T>?
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

    private fun rbTransplant(u: RBNode<T>?, v: RBNode<T>?) {
        if (u!!.parent == null) {
            root = v
        } else if (u === u.parent!!.left) {
            u.parent!!.left = v
        } else {
            u.parent!!.right = v
        }
        v!!.parent = u.parent
    }

    private fun delete(node: RBNode<T>?, key: T) {
        var node = node
        var z: RBNode<T>? = empty
        val x: RBNode<T>?
        var y: RBNode<T>?
        while (node !== empty) {
            if (node!!.key == key) {
                z = node
            }

            node = if (node.key!! <= key) {
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

    private fun fixInsert(k: RBNode<T>) {
        var k: RBNode<T>? = k
        var u: RBNode<T>?
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

    fun iter(): List<Pair<T, Any>> {
        val t: MutableList<Pair<T, Any>> = ArrayList()
        inOrder(this.root, t)
        return t
    }

    fun find(k: T): RBNode<T>? {
        return find(root, k)
    }

    fun minimum(node: RBNode<T>?): RBNode<T>? {
        var node = node
        while (node!!.left !== empty) {
            node = node!!.left
        }
        return node
    }


    fun leftRotate(x: RBNode<T>?) {
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

    fun rightRotate(x: RBNode<T>?) {
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

    fun add(key: T, value: Any) {
        if (find(key) != null) {
            find(key)?.data = value
            return
        }
        val node = RBNode<T>()
        node.parent = null
        node.key = key
        node.data = value
        node.left = empty
        node.right = empty
        node.color = 1

        var y: RBNode<T>? = null
        var x = this.root

        while (x !== empty) {
            y = x
            x = if (node.key!! < x!!.key!!) {
                x.left
            } else {
                x.right
            }
        }

        node.parent = y
        if (y == null) {
            root = node
        } else if (node.key!! < y.key!!) {
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


    fun delete(key: T) {
        delete(this.root, key)
    }
}
