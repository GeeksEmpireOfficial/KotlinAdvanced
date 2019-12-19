package net.geeksempire.advanced.kotlin

//let - run - with - apply - also
fun scopeFunctions() {
    var intVariable: Int = 13
    intVariable.let { theVariable ->
        println("1. ${theVariable}")
    }

    //:: is Input Variable of Function
    intVariable.let(::getIntToPrint)

    val variableLength = intVariable.let {

        "${it}".length
    }
    println("1.2. ${variableLength}")

    intVariable.also {
        println("3. ${it}")
    }

    intVariable.run {
        println("4. ${this}")
    }

    intVariable.apply {
        println("5. ${this}")
    }

    val functionsClassData = FunctionsClassData(0).apply {
        aVariable = 111
        bVariable = 222
        cVariable = 333
    }

    with(functionsClassData) {
        val aNumber = aVariable.takeIf { it == 111 }.let {
            println("6. ${it}")
        }
    }

    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = with(numbers) {
        "The first element is ${this.first()}," +
                " the last element is ${this.last()}"
    }
    println("6.1. ${firstAndLast}")

    val runVariable = numbers.run {
        "First ${this.first()}," +
                " " +
                "Last ${this.last()}"
    }
    println("6.2. ${runVariable}")

    FunctionsClass().run {
        globalInput = 13579
        anActionOnInput(97531)
    }

    val runVariableNoContext = run {
        12 + 34
    }
    println("6.3. ${runVariableNoContext}")

    numbers
        .also {
            println("7. Before Adding New Element ::: $it")
        }
        .add("four")
        .also {
            println("7.1. Added Successfully ::: ${it}")
            println("7.2. ${numbers}")
        }
}

fun getIntToPrint(i: Int) {
    println("1.1. ${i}")
}