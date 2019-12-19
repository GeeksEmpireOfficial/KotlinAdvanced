package net.geeksempire.advanced.kotlin

class LambdaFunctionsClass {
    init {
        val lambdaVariable = lamdaFunction { inputOne, inputTwo ->
            normalFunctionInput(inputOne, inputTwo)
        }
        println("Lambda ::: ${lambdaVariable(1)}")
    }

    fun <aGeneric, bGeneric> genericFunction(aGenericInput: aGeneric, bGenericInput: bGeneric) : (aGeneric) -> bGeneric {

        return { genericOutput -> bGenericInput }
    }

    fun normalFunctionInput(aNumber: Int, bNumber: Int) : Int {

        return aNumber + bNumber + 1
    }

    fun normalFunctionOutput(number: Int) : Int {
        println("Output ::: $number")

        return number + 1
    }

    fun lamdaFunction(inputData: (Int, Int) -> Int) : (outputNumber: Int) -> Int {
        val iNumber = inputData(13, 46)
        println("iNumber ::: $iNumber")

        return (::normalFunctionOutput)
    }
}