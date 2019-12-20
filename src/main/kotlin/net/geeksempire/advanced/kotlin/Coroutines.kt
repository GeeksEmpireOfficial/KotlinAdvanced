package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class Coroutines {

    init {
        lazyCoroutine()
    }

    fun aCoroutineTask() = runBlocking<Unit> {
        var aCoroutineTask: Job? = GlobalScope.launch {
            delay(1000)

            println("First Sentence")
        }

        println("Second Sentence") // main thread continues while coroutine is delayed
        aCoroutineTask?.join()
    }

    fun repeatCoroutine() = runBlocking<Unit> {
        val job = launch {
            repeat(10) { i ->
                println("job: I'm sleeping $i ...")
                delay(777)
            }
        }
    }

    fun finalizeResourcesInCoroutine() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("job: I'm running finally")
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    fun sequentialCoroutine() = runBlocking {
        val time = measureTimeMillis {
            val oneProcess = doSomethingUsefulOne()
            val twoProcess = doSomethingUsefulTwo()

            println("Answer ::: ${oneProcess + twoProcess}")
        }
        println("Completed in $time ms")
    }

    fun concurrentCoroutines() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }

            println("Answer ::: ${one.await()}")
            println("Answer ::: ${two.await()}")
            println("Answer ::: ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    fun lazyCoroutine() = runBlocking {
        val time = measureTimeMillis {
            println("Answer ::: ${concurrentCoroutine()}")
        }
        println("Completed in $time ms")
    }

    suspend fun concurrentCoroutine(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }

        one.await() + two.await()
    }
    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here

        return 1
    }
    suspend fun doSomethingUsefulTwo(): Int {
        delay(3000L) // pretend we are doing something useful here, too

        return 2
    }
}