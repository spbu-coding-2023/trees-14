import trees.AVLTree

fun main(){
    val da = AVLTree<String>()
    da.add("rrr")
    da.add("eee")
    da.add("fgf")
    da.add("sd")
    da.add("raz")
    da.add("qwe")
    da.add("qww")
    if (da.find("rrr") != null)
        println("True")
    else println("False")
    if(da.delete("qww") != null)
        println("delete succesful")
    else println("value isn't in tree")
    if (da.find("qww") != null)
        println("True")
    else println("False")
    if(da.delete("qww") != null)
        println("delete succesful")
    else println("value isn't in tree")
    da.add("qweerty")
    val q = da.iterator()
    for (ii in q){
        print(ii)
        print(" ")
    }
    println()
}