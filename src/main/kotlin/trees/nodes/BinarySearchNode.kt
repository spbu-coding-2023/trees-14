package src.trees.nodes

class BinarySearchNode<T : Comparable<T>>( k: T,data: Any) : BaseNode<T, BinarySearchNode<T>>(k, data)