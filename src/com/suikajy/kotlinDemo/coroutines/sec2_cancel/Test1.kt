package com.suikajy.kotlinDemo.coroutines.sec2_cancel

import kotlinx.coroutines.*

fun main() {
    test6f1()

}

// 这里的cancel和join可以简写为cancelAndJoin()
fun test1f1() = runBlocking {
    //sampleStart
    val startTime = System.currentTimeMillis()
    val job = launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    println("cancel time is ${System.currentTimeMillis() - startTime}")
    job.join() // waits for job's completion
    println("join time is ${System.currentTimeMillis() - startTime}")
    println("main: Now I can quit.")
    //sampleEnd
}

// 在计算型协程上，cancel不会中断其执行。这有点类似android Handler了
fun test2f1() = runBlocking {
    //sampleStart
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
//sampleEnd
}

// the extension property isActive is automatically
// changed to false when the cancel function invoked.
fun test3f1() = runBlocking {
    //sampleStart
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
//sampleEnd
}

// 协程被中断时会产生CancellationException
// 也可以使用use()函数来关闭closeable
fun test4f1() = runBlocking {
    //sampleStart
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
//sampleEnd
}

// 不可取消协程
// 少数情况下，你可能需要一个无法被取消的协程，那么就使用withContext(NonCancellable)
fun test5f1() = runBlocking {
    //sampleStart
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
//sampleEnd
}

// TimeOut
// 通常情况下取消一个协程的原因一般是因为超时，所以kotlin中使用withTimeout(1300L)
// 来包裹一个协程即可在设定时间的时候自动取消它，类似于js
fun test6f1() = runBlocking {
    //sampleStart
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
//sampleEnd
}

// withTimeoutOrNull 在超时时不会抛出CancellationException，而是返回一个null，感觉更方便一些
fun test7f1() = runBlocking {
    //sampleStart
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
    println("Result is $result")
//sampleEnd
}
