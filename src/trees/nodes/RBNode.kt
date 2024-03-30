package trees.nodes

class RBNode<T : Comparable<T>>(data: T) : BaseNode<T, RBNode<T>>(data){
    // 1 for red and 0 for black
    var color : UInt = 0u
    fun switchColor(){
        color = if (color == 0u) 1u else 0u
    }
}