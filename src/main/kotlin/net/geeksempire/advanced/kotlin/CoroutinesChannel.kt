package net.geeksempire.advanced.kotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutinesChannel {

    init {
        coroutinesPipeline()
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
}