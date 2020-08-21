package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun notSuspend() = suspendCoroutine<Int> { continuation ->
    thread(true) {
        continuation.resume(100)
    }
}