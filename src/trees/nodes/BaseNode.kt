package trees.nodes

abstract class BaseNode<T : Comparable<T>, N : BaseNode<T, N>>  (var value: T){
    var data : T = value
    var left : N? = null
    var right : N? = null
    var parent : N? = null
}