package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*

class CoroutinesExceptions {

    init {
        supervisedScopeException()
    }

    fun exceptionHandler() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        val job = GlobalScope.launch(handler) {
            launch { // the first child
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    withContext(NonCancellable) {
                        println("Children are cancelled, but exception is not handled until all children terminate")
                        delay(100)
                        println("The first child finished its non cancellable block")
                    }
                }
            }
            launch { // the second child
                delay(10)
                println("Second child throws an exception")
                throw ArithmeticException()
            }
        }
        job.join()
    }

    fun supervisedJobException() = runBlocking {
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            // launch the first child -- its exception is ignored for this example (don't do this in practice!)
            val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
                println("First child is failing")
                throw AssertionError("First child is cancelled")
            }

            // launch the second child
            val secondChild = launch {
                firstChild.join()
                // Cancellation of the first child is not propagated to the second child
                println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
                try {
                    delay(2000)
                } finally {
                    // But cancellation of the supervisor is propagated
                    println("Second child is cancelled because supervisor is cancelled")
                }
            }
            // wait until the first child fails & completes
            firstChild.join()

            println("Cancelling supervisor")
            supervisor.cancel()

            secondChild.join()
        }
    }

    fun supervisedScopeException() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        supervisorScope {
            val child = launch(handler) {
                println("Child throws an exception")
                throw AssertionError()
            }
            println("Scope is completing")
        }
        println("Scope is completed")
    }
}