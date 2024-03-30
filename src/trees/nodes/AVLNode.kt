package trees.nodes

class AVLNode<T : Comparable<T>>(data: T) : BaseNode<T, AVLNode<T>>(data) {
    var height: Int = 1
}
