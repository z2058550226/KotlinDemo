package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine
// 两种不同的resume方式，如果协程体直接由返回值返回，则称为fast path，如果由resumeWith返回，则称为slow path
suspend fun suspendFunc01(a: Int) {
    return
}

suspend fun suspendFunc02(a: String, b: String) = suspendCoroutine<Int> { continuation ->
    thread {
        Thread.sleep(1000)
        continuation.resumeWith(Result.success(5))
    }
}

fun main() {
    val lock = Object()
    synchronized(lock) {
        println(lock.`class` == lock.javaClass)
    }
}