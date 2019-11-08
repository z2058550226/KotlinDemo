package com.suikajy.kotlinDemo.coroutines.guide.sec7_channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    test3f1()
}

/**
 * Channel basics
 */
fun test1f1() = runBlocking {
    val channel = Channel<Int>()
    GlobalScope.launch {
        //        println("1# ${Thread.currentThread()}")
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
    // here we print five received integers:
//    println("2# ${Thread.currentThread()}")
    delay(111)
    repeat(3) { println(channel.receive()) }
    println("Done!")
}

/**
 * Closing and iteration over channels
 */
fun test2f1() = runBlocking {
    val channel = Channel<Int>()
    GlobalScope.launch {
        delay(1100)
        println("1# ${Thread.currentThread()}")
        for (x in 1..5) channel.send(x * x)
        channel.close() // we're done sending
    }
    println("2# ${Thread.currentThread()}")
// here we print received values using `for` loop (until the channel is closed)
    for (y in channel) println(y)
    println("Done!")
}

/**
 * Building channel producers
 */
fun test3f1() = runBlocking {

    fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
        for (x in 1..5) send(x * x)
    }

    val squares = produceSquares()
    squares.consumeEach {
        delay(500)
        println(it)
    }
    println("Done")
}

/**
 * Pipelines
 */
fun test4f1() = runBlocking {
    // A pipeline is a pattern where one coroutine is producing, possibly infinite, stream of values:
    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) send(x++) // infinite stream of integers starting from 1
    }

    // And another coroutine or coroutines are consuming that stream, doing some processing,
    // and producing some other results. In the example below, the numbers are just squared:
    fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
        for (x in numbers) send(x * x)
    }

    // The main code starts and connects the whole pipeline:
    val numbers = produceNumbers() // produces integers from 1 and on
    val squares = square(numbers) // squares integers
    for (i in 1..5) println(squares.receive()) // print first five
    println("Done!") // we are done
    coroutineContext.cancelChildren() // cancel children coroutines
}

fun test4f2() = runBlocking {
    // Let's take pipelines to the extreme with an example that generates prime
    // numbers using a pipeline of coroutines. We start with an infinite sequence of numbers.
    fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
        var x = start
        while (true) send(x++) // infinite stream of integers from start
    }

    // The following pipeline stage filters an incoming stream of numbers, removing all
    // the numbers that are divisible by the given prime number:
    fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
        for (x in numbers) if (x % prime != 0) send(x)
    }

    // Now we build our pipeline by starting a stream of numbers from 2, taking a prime number from the current
    // channel, and launching new pipeline stage for each prime number found:
    // numbersFrom(2) -> filter(2) -> filter(3) -> filter(5) -> filter(7) ...

    // The following example prints the first ten prime numbers, running the whole pipeline in the context of the main thread.
    // Since all the coroutines are launched in the scope of the main runBlocking coroutine we don't have to keep an explicit
    // list of all the coroutines we have started. We use cancelChildren extension function to cancel all the children
    // coroutines after we have printed the first ten prime numbers.
    var cur = numbersFrom(2)
    for (i in 1..10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren()
}

fun test6f1() = runBlocking {
    val channel = Channel<Int>(4) // create buffered channel
    val sender = launch {
        // launch sender coroutine
        repeat(10) {
            println("Sending $it") // print before sending each element
            channel.send(it) // will suspend when buffer is full
        }
    }
    // don't receive anything... just wait....
    delay(1000)
    sender.cancel() // cancel sender coroutine
}

class BasicTest1 : CoroutineScope {
    override val coroutineContext: CoroutineContext get() = EmptyCoroutineContext

    fun click() {
        launch {
            val channel = Channel<Unit>()

            channel.receive()
        }
        onClick()
    }

    val onClick: () -> Unit = {
        println("aaa")
    }
}