package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

class CoroutinesFlow {

    init {
        flowTransform()
    }

    fun flowTransform()  = runBlocking {
        (1..9).asFlow() // a flow of requests
            .transform { request ->
                emit("Making Request $request ^ 13")
                emit(performRequest(request, 13.0))
            }
            .take(6)
            .collect { response -> println(response) }
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