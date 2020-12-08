package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

fun main(/*args: Array<String>*/) = runBlocking<Unit> {

    val sum: String.(Int,Int) -> String = { a, b ->
        this.plus(a + b)
    }

    val t = "xxx".sum(1, 32)
    println(t)

//    execute(operations = Operations.PressHold(NumberDataClass(aNumber = 13)))
//    execute(operations = Operations.Press(NumberDataClass(aNumber = 13)))

//    execute(operations = Operations.PressHoldAction)
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
