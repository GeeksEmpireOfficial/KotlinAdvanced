package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

fun main(args: Array<String>) {

    GlobalScope.launch {
        delay(1000)

        println("10th Sentence")
    }
    println("12th Sentence") // main thread continues while coroutine is delayed
    Thread.sleep(2000) // block main thread for 2 seconds to keep JVM alive
}