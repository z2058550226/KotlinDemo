@file:Suppress("unused")

package com.suikajy.kotlinDemo.coroutines.guide.sec1_basic

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

fun main()  {
    val scope = CoroutineScope(Dispatchers.IO)
    println("main thread: " + Thread.currentThread())
    scope.launch {
        testSuspend()
        println("scope thread: " + Thread.currentThread())
    }
    TimeUnit.SECONDS.sleep(3)
}

suspend fun testSuspend() {
    delay(2000)
    println("suspend: " + Thread.currentThread())
}

/**
 * simple hello world
 */
fun test0(args: Array<String>) {
    GlobalScope.launch {
        delay(1000L)
        println("World")
    }

    println("Hello,")
    TimeUnit.SECONDS.sleep(2000L)
}

fun test1() {
    // 在线程中无法使用延时方法
//    Thread{
//        delay(1000L)
//    }.start()
}

/**
 * coroutine has blocking and non-blocking
 */
fun test2() {
    GlobalScope.launch {
        delay(1000L)
        println("world")
    }
    println("hello ")
    runBlocking {
        // 这里面会阻塞主线程
        delay(2000L) // 延迟两秒，让jvm不挂掉
    }
}

fun test3() = runBlocking {
    // 开始主协程
    launch {
        // 在后台启动一个新的协程，然后继续执行
        delay(1000L)
        println("World!")
    }
    println("Hello,") // 主协程立即继续跑
    delay(2000L)      // 延迟2s，让jvm不挂掉
}

fun test4() = runBlocking<Unit> {
    val job = launch {
        // 启动一个新协程，并创建一个对其任务的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等到子协程完成
}

fun test5() = runBlocking {
    // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope {
        // Creates a new coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before nested launch
    }

    println("Coroutine scope is over") // This line is not printed until nested launch completes
}