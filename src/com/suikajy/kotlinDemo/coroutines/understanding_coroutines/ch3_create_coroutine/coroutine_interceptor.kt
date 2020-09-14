package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine

fun main() {
    val startTime = System.currentTimeMillis()

    suspend {
        suspendFunc02("hello", "kotlin")
        println("#1 spend time: ${System.currentTimeMillis() - startTime}")
        suspendFunc02("hello", "coroutine")
        println("#2 spend time: ${System.currentTimeMillis() - startTime}")
        suspendFunc02("hello", "suika")
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = LogInterceptor()

        override fun resumeWith(result: Result<Int>) {
            println("#3 spend time: ${System.currentTimeMillis() - startTime}")
            println(result.getOrNull())
        }
    })
}

class LogInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*> get() = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return LogContinuation<T>(continuation)
    }
}

class LogContinuation<T>(val continuation: Continuation<T>) : Continuation<T> by continuation {
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith:$result")
        continuation.resumeWith(result)
        println("after resumeWith.")
    }
}