package net.geeksempire.advanced.kotlin

class ReflectionDeclaration {

    val xVariable: Int = 313

    init {
        getPropertyReference()
    }

    fun getPropertyReference() {
        println(::xVariable.get())
        println(::xVariable.name)

        val strs = listOf("a", "bc", "def")
        println(strs.map(String::length))
    }

    fun functionReferences() {
        fun isOdd(x: Int) = x % 2 != 0

        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        println(numbers.filter(::isOdd)) // refers to isOdd(x: Int)
    }

    fun <StringInput : String, IntTypeTextLength : Int, BooleanType : Boolean> compose(oddCheck: (IntTypeTextLength) -> BooleanType, lengthText: (StringInput) -> IntTypeTextLength) : (StringInput) -> BooleanType {
        return {
            oddCheck(lengthText(it))
        }
    }

    fun isOdd(x: Int) = x % 2 != 0

    fun getLength(s: String) = s.length

    fun composeFunctions() {

        val textLength = compose(::isOdd, ::getLength)
        println(textLength)

        val strings = listOf("a", "ab", "abc")

        println(strings.filter(textLength))

        strings.map {
            if (textLength(it)) {
                println("${it}")
            }
        }
    }
}

