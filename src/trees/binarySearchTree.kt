package trees

import trees.nodes.BaseNode
import trees.nodes.BinarySearchNode


class binarySearchTree<T: Comparable<T>>(data: T) : BaseTree<T, BinarySearchNode<T>>(){
    //что то архитектурное происходит (самый цензурный вариант)
    override fun createNode(data: T): BinarySearchNode<T> {
        TODO("Not yet implemented")
    }

    override fun add(data: T): BinarySearchNode<T> {
        TODO("Not yet implemented")
    }

    override fun find(data: T): BinarySearchNode<T>? {
        TODO("Not yet implemented")
    }

    override fun delete(data: T) {
        TODO("Not yet implemented")
    }

    override fun print() {
        TODO("Not yet implemented")
    }
}