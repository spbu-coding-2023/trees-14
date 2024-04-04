import trees.AVLTree

fun main(){
    val da = AVLTree<Int>()
    da.add(100)
    da.add(123)
    da.add(929)
    da.add(343)
    da.add(232)
    da.add(4)
    da.add(67)
    if (da.find(4) != null)
        println("True")
    else println("False")
    if(da.delete(4) != null)
        println("delete succesful")
    else println("value isn't in tree")
    if (da.find(4) != null)
        println("True")
    else println("False")
    if(da.delete(4) != null)
        println("delete succesful")
    else println("value isn't in tree")
    da.add(8348)
    val q = da.iterator()
    for (ii in q){
        print(ii)
        print(" ")
    }
    println()
}