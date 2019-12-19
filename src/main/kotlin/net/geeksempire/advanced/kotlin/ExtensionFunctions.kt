package net.geeksempire.advanced.kotlin

class ExtensionFunctions {
     init {
         val aList = arrayListOf<String>("a", "b", "c", "d", "e")

         println(aList.middleElement())
     }
}

val <T> List<T>.lastIndex: Int
    get() = size - 1

fun <T> List<T>.middleElement() : T {

    val middlePosition = (size / 2)

    return this[middlePosition]
}

fun String.splitIt() : List<String> {

    return this.split(".")
}