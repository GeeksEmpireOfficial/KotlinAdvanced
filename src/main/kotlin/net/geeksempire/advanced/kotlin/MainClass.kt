package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

fun main(/*args: Array<String>*/) = runBlocking<Unit> {

    val aCoroutineTask = GlobalScope.launch {
        delay(1000)

        println("10th Sentence")
    }
    println("12th Sentence") // main thread continues while coroutine is delayed
    aCoroutineTask.join()
}