package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

class CoroutineContexts {

    init {
        nestedCoroutines()
    }

    fun nestedCoroutines() = runBlocking {
        newSingleThreadContext("Ctx1").use { ctx1 ->

            newSingleThreadContext("Ctx2").use { ctx2 ->

                runBlocking(ctx1) {
                    log("Started in ctx1")

                    withContext(ctx2) {
                        log("Working in ctx2")
                    }

                    log("Back to ctx1")
                }
            }
        }
    }
    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    fun testCoroutinesContexts() = runBlocking {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }
}