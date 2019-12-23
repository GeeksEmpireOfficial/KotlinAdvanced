package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

class CoroutinesChannel {

    init {
        tickingProducer()
    }

    fun bufferChannel() = runBlocking {
        val channel = Channel<Int>(2) // create buffered channel

        val sender = launch { // launch sender coroutine
            repeat(10) {
                println("Sending $it") // print before sending each element

                channel.send(it) // will suspend when buffer is full
            }
        }

        // don't receive anything... just wait....
        delay(1000)
        sender.cancel() // cancel sender coroutine
    }

    //One Coroutine produce stream of data & other coroutines are using it.
    fun coroutinesPipeline() = runBlocking {
        var allNumbers = numbersFrom(2)

        for (i in 1..10) {
            val prime = allNumbers.receive()

            allNumbers = filter(allNumbers, prime)

            println("${i}. Prime ${prime}")
        }
        coroutineContext.cancelChildren() // cancel all children to let main finish
    }

    fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
        var x = start

        while (true) {//Just a Process of infinite loop
            send(x++)
        } // infinite stream of integers from start
    }

    fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
        for (x in numbers) {//a process to validate prime number
            if (x % prime != 0) {
                send(x)
            }
        }
    }

    fun channelSendReceive() = runBlocking {
        val channel = Channel<Int>()

        launch {
            // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
            for (x in 1..5) {
                channel.send(x * x)
            }
        }

        // here we print five received integers:
        repeat(5) {
            println(channel.receive())
        }

      //  channel.close()
        println("Done!")
    }

    fun CoroutineScope.produceSquares() : ReceiveChannel<Int> = produce {
        for (x in 1..5) {
            send(x * x)
        }
    }

    fun channelProducer() = runBlocking {
        val squares = produceSquares()
        squares.consumeEach {
            println(it)
        }

        println("Done!")
    }

    fun tickingProducer() = runBlocking<Unit> {
        val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0) // create ticker channel
        var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
        println("Initial element is available immediately: $nextElement") // initial delay hasn't passed yet

        nextElement = withTimeoutOrNull(50) { tickerChannel.receive() } // all subsequent elements has 100ms delay
        println("Next element is not ready in 50 ms: $nextElement")

        nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
        println("Next element is ready in 100 ms: $nextElement")

        // Emulate large consumption delays
        println("Consumer pauses for 150ms")
        delay(150)
        // Next element is available immediately
        nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
        println("Next element is available immediately after large consumer delay: $nextElement")
        // Note that the pause between `receive` calls is taken into account and next element arrives faster
        nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
        println("Next element is ready in 50ms after consumer pause in 150ms: $nextElement")

        tickerChannel.cancel() // indicate that no more elements are needed
    }
}