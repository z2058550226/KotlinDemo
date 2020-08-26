package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

suspend fun suspendFunc01(a: Int) {
    return
}

suspend fun suspendFunc02(a: String, b: String) = suspendCoroutine<Int> { continuation ->
    thread {
        continuation.resumeWith(Result.success(5))
    }
}

fun main() {
    val lock = Object()
    synchronized(lock) {
        println(lock.`class` == lock.javaClass)
    }
}