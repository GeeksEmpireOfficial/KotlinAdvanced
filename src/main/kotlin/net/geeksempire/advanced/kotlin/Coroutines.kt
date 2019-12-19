package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

class Coroutines {

    init {
        finalizeResourcesInCoroutine()
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
}