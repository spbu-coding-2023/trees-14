package trees.nodes

import src.trees.nodes.BaseNode

class RBNode<T : Comparable<T>>(k: T? = null, data: Any? = null) : BaseNode<T, RBNode<T>>(k, data) {
    var data: Any? = null
    var color: Int = 0
}