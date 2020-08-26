package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun notSuspend() = suspendCoroutine<Int> { continuation ->
    println("notSuspend " + Thread.currentThread().toString())
    Thread.sleep(1000L)
    continuation.resume(100)

//    suspendCoroutineUninterceptedOrReturn { c: Continuation<T> ->
//        val safe = SafeContinuation(c.intercepted())
//        block(safe)
//        safe.getOrThrow()
//    }
}

// 此处查看suspendCoroutine方法源码，如果在获取返回值时（safe.getOrThrow()）已经有了返回值，就直接将返回值作为外部方法返回值
// 如果没有返回值，就利用一个守护线程等待（挂起）结果后返回。
// 挂起点：挂起函数中真正调用异步逻辑的位置。
suspend fun realSuspend() = suspendCoroutine<Int> { continuation ->
    thread {
        println("notSuspend " + Thread.currentThread().toString())
        Thread.sleep(2000L)
//        continuation.resume(100)
    }
}