package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

class CoroutinesFlow {

    init {
        flattenFlow()
    }

    fun flattenFlow() = runBlocking {
        val startTime = System.currentTimeMillis() // remember the start time
        (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
            .flatMapConcat { requestFlow(it) }
            .catch { e -> println("Caught $e") }
            .collect { value -> // collect and print
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }
    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First")

        delay(500) // wait 500 ms

        emit("$i: Second")
    }

    fun flowOnOperator() : Flow<Int> = flow {

        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way

            emit(i) // emit next value
        }

    }.flowOn(Dispatchers.Default)

    fun flowTransform()  = runBlocking {
        /*(1..9).asFlow() // a flow of requests
            .transform { request ->
                emit("Making Request $request ^ 13")
                emit(performRequest(request, 13.0))
            }
            .take(6)
            .collect { response -> println(response) }*/


        val sumOfNumbers = (1..7).asFlow()
            .filter {
                println("Filter ::: ${it}")

                (it % 2 == 0)
            }
            .map {
                println("Map ::: ${it}")

                it * it
            } // squares of numbers from 1 to 5
            .reduce { accumulator /*Last Result of Reduce*/, value /*Value from Map*/ ->
                println("${accumulator} + ${value} = ${accumulator + value}")

                accumulator + value
            } // sum them (terminal operator)
        println(sumOfNumbers)
    }

    fun flowOfRequest() = runBlocking {
        (1..3).asFlow() // a flow of requests
            .map { request ->
                /*Perform Task on Input Data*/
                performRequest(request, 13.0)
            }
            .take(3)
            .collect { response ->
                println(response)
            }
    }
    suspend fun performRequest(request: Int, power: Double): String {
        delay(1000) // imitate long-running asynchronous work

        return "Response ${Math.pow(request.toDouble(), power)}"
    }

    fun flowFunctionTimeout() = runBlocking {
        withTimeoutOrNull(900) { // Timeout after 250ms
            flowCollector().collect { value -> println(value) }
        }
        println("Done")
    }

    fun flowFunction() = runBlocking {
        println("Calling foo...")
        val flow = flowCollector()
        println("Calling collect...")
        flow.collect { value -> println(value) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }
    fun flowCollector(): Flow<Int> = flow {
        println("Flow started")

        for (i in 1..3) {
            delay(333)

            emit(i)
        }
    }
}