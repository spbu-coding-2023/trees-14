package src.trees.nodes

class AVLNode<T : Comparable<T>>(k:T,data: Any) : BaseNode< T,AVLNode<T>>(k, data) {
    var height: Int = 1
}
