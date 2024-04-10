package src.trees

import src.trees.nodes.BaseNode

abstract class BaseTree<T : Comparable<T>, N : BaseNode<T, N>> {
    open var root: N? = null

    abstract fun createNode(key: T, data:Any): N

    abstract fun add(key: T, data: Any): N?

    abstract fun find(key: T): N?

    abstract fun delete(key: T): N?

    abstract fun iter(): MutableList<Pair<T,Any>>

}
