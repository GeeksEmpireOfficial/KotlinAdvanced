package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

class Coroutines {

    init {
        aCoroutineTask()
    }

    fun aCoroutineTask() = runBlocking<Unit> {
        var aCoroutineTask: Job? = GlobalScope.launch {
            delay(1000)

            println("11th Sentence")
        }

        println("12th Sentence") // main thread continues while coroutine is delayed
        aCoroutineTask?.join()
    }
}