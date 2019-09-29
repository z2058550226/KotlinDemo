package com.suikajy.kotlinDemo.coroutines.guide.sec1_basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    test2_3()
}

fun test2_1() = runBlocking {
    launch { doWorld() }
    println("Hello,")
}

suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

// 协程是非常轻量的，这里同时开启了十万个协程。反应很快，但这里如果要是使用线程，可能就会OOM
fun test2_2() = runBlocking {
    repeat(100_000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}

fun test2_2_1() {
    for (i in 0 until 100_000) {
        Thread {
            Thread.sleep(1000L)
            print(".")
        }.start()
    }
}

fun test2_3() = runBlocking {
    //sampleStart
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // just quit after delay
//sampleEnd
}