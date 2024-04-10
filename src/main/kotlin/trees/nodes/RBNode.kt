package trees.nodes

class RBNode<T: Comparable<T>> {
    var key: T? = null
    var data: Any? = null
    var parent: RBNode<T>? = null
    var left: RBNode<T>? = null
    var right: RBNode<T>? = null
    var color: Int = 0
}