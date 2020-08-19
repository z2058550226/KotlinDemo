package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlinx.coroutines.delay
import kotlin.coroutines.*

fun main() {
    test1()
}

private fun test1() {
    // create a coroutine
    val continuation = suspend {
        println("In coroutine.")
        5
    }.createCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println("Coroutine End: $result")
        }
    })

    // run the coroutine
    continuation.resume(Unit)
}

fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("Coroutine End: $result")
        }
    })
}

// 作用域，用来提供函数支持，也可以增加限制
class ProducerScope<T> {
    suspend fun produce(value: T) {
        println("produce: $value")
    }
}

fun callLaunchCoroutine() {
    launchCoroutine(ProducerScope<Int>()) {
        println("In Coroutine")
        produce(1024)
        delay(1000)
        produce(2048)
    }
}

@RestrictsSuspension
class RestrictProducerScope<T> {
    suspend fun produce(value: T) {
        println("produce $value")
    }
}

fun callLaunchCoroutineRestricted(){
    launchCoroutine(RestrictProducerScope<Int>()){
        produce(123)
        // 编译错误
        // delay(1000)
    }
}