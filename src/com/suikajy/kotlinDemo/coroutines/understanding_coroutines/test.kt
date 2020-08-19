package com.suikajy.kotlinDemo.coroutines.understanding_coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine

fun main() {
    suspend {
        println("in coroutine")
        5
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = Dispatchers.IO

        override fun resumeWith(result: Result<Int>) {
            println("resume")
            println(result.getOrNull().toString())
        }
    })
    Thread.sleep(1000L)
}