package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

fun main(/*args: Array<String>*/) = runBlocking<Unit> {

    val aArray = arrayListOf<Int>()

    repeat(52) {
        aArray.add(it)
    }
    println(aArray)

    aArray.shuffle()
    println(aArray)

    println("\n")

    val a = aArray.slice(IntRange(0, 12))
    val b = aArray.slice(IntRange(13, 25))
    val c = aArray.slice(IntRange(26, 38))
    val d = aArray.slice(IntRange(39, 51))

    println(a)
    println(a.size)

    println(b)
    println(b.size)

    println(c)
    println(c.size)

    println(d)
    println(d.size)


//    execute(operations = Operations.PressHold(NumberDataClass(aNumber = 13)))
//
//    execute(operations = Operations.Press(NumberDataClass(aNumber = 13)))
//
//
//
//    execute(operations = Operations.PressHoldAction)
//
//    execute(operations = Operations.PressAction)
}

fun execute(operations: Operations) = when (operations) {
    is Operations.Press -> {
        val i = 10 + operations.data.aNumber

        println(i)
    }
    is Operations.PressHold -> {
        val i = 100 + operations.data.aNumber

        println(i)
    }

    Operations.PressAction -> {

        println("Press Action")
    }

    Operations.PressHoldAction -> {

        println("PressHold Action")
    }
}
