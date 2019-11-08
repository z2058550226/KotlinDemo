package com.suikajy.kotlinDemo.coroutines.guide.sec3_compose

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val deferred1 = async(Dispatchers.IO) {
            delay(3000)
            println("#1 over")
            return@async 1
        }
        val deferred2 = async(Dispatchers.IO) {
            delay(4000)
            println("#2 over")
            return@async 2
        }
        println("#4 ${System.currentTimeMillis()}")
        val v1 = deferred1.await()
        val v2 = deferred2.await()
        println("#5 ${System.currentTimeMillis()}")
        for (i in 1..4) {
            delay(1000)
            println("#3 i: $i")
        }
        println("#6 ${System.currentTimeMillis()}")
    }
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

// 顺序执行演示操作
fun test1() = runBlocking {
    //sampleStart
    val time = measureTimeMillis {
        // 计算某段代码执行了多少毫秒
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
//sampleEnd
}

// 并发执行延时操作
fun test2() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() } // 这里返回Deferred 是一种可以拿到返回值的Job，类似Promise
        val two = async { doSomethingUsefulTwo() } // 这里开启了两个额外的协程
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
//sampleEnd
}

// 延时启动并发协程，当调用await()或者start()的时候才去执行，start会直接开启异步协程
// 如果只用await的话那么这段代码还是会顺序执行的
fun test3() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
//sampleEnd
}

// 异步方法提取
// The result type of somethingUsefulOneAsync is Deferred<Int>
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

// 这种方式使用异步方法有很多弊端，这只是一种为了和js写法相类似而做的展示
// 在异常发生时下面这种写法不容易捕捉，并且出现异常之后代码还会继续运行
// 如果想要实现结构化并发，最好使用更下面的做法
fun test4() {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

// 结构化并发，采用如下方式使用异步方法更佳
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

fun test5() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
    //sampleEnd
}

// 这种方式的异常捕获是下面的姿势，这时当异步协程失败时会告诉等待的parent协程：child协程失败，parent会取消其他协程的调用
// 这里和上面的不同之处在于：这里两个async所执行的协程的Parent协程是同一个协程
suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}

fun test6() = runBlocking<Unit> {
    val timeMillis = measureTimeMillis {
        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }
    println(timeMillis)
}

