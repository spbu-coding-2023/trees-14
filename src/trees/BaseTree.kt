package trees

import trees.nodes.BaseNode

abstract class BaseTree<T : Comparable<T>, N : BaseNode<T, N>> {
    var root: N? = null

    abstract fun createNode(data: T): N

    abstract fun add(data: T): N

    abstract fun find(data: T): N?

    abstract fun delete(data: T)

    abstract fun print()

}
