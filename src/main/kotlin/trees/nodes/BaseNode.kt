package src.trees.nodes

abstract class BaseNode< T : Comparable<T>, N : BaseNode<T, N>>(private var k: T? = null, var value: Any? = null){
    var key : T? = k
    var left : N? = null
    var right : N? = null
    var parent : N? = null
}
