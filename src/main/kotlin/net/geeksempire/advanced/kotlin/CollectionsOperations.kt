package net.geeksempire.advanced.kotlin

//List - Array - etc
fun collectionsOperations() {
    val numbersList = listOf<Int>(1, 2, 3)
    println(numbersList.map { it * 3 })

    numbersList.map {
        println(it * 3)
    }
}