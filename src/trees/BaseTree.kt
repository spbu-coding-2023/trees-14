package trees

import trees.nodes.BaseNode

abstract class BaseTree<T: Comparable<T>, N: BaseNode<T, N>>{
    var root : N? = null

    /**
     * Adds a node to tree
     */
    open fun insertNode(data: T) {
        //doing smthng
    }
    open fun findNode(data: T){
        //doing smthng
    }
    open fun deleteNode(data: T){
        //doing smthng
    }
    open fun iterTree(){
        //doing smthng
    }
    open fun balanceTree(){
        //doing smthng
    }



}