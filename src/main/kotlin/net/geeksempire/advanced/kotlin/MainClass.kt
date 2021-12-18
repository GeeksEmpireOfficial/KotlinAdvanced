package net.geeksempire.advanced.kotlin

//interface StringFunction {
//    fun run(str: String): String
//}
//
//val exclaim = object : StringFunction {
//
//    override fun run (str: String): String {
//
//        return "${str}!"
//    }
//
//}
//
//
//fun printFormatted(str: String, format: StringFunction) {
//    val result = format.run(str)
//
//    println(result)
//}

fun main(/*args: Array<String>*/) {



//    var text: String? = null
//    var i = 3 + 3
//
//    val j = text.takeIf {
//
//        (text != null)
//    }?.takeUnless {
//
//        (text == null)
//    }
//
//    println("1. " + i)
//    println("2. " + j)

//    val sum: String.(Int,Int) -> String = { a, b ->
//        this.plus(a + b)
//    }
//
//    val t = "xxx".sum(1, 32)
//    println(t)
//
//    printFormatted("TEST", exclaim)
//
//    val lambda2 : (Int) -> Int = {
//        println("${it} Hello, world")
//
//        1
//    }
//    println(">>> ${lambda2.invoke(666)}")
//
//    execute(operations = Operations.PressHold(NumberDataClass(aNumber = 13)))
//    execute(operations = Operations.Press(NumberDataClass(aNumber = 13)))
//
//    execute(operations = Operations.PressHoldAction)
//    execute(operations = Operations.PressAction)
}

//fun execute(operations: Operations) = when (operations) {
//    is Operations.Press -> {
//        val i = 10 + operations.data.aNumber
//
//        println(i)
//    }
//    is Operations.PressHold -> {
//        val i = 100 + operations.data.aNumber
//
//        println(i)
//    }
//
//    Operations.PressAction -> {
//
//        println("Press Action")
//    }
//
//    Operations.PressHoldAction -> {
//
//        println("PressHold Action")
//    }
//}
