package com.suikajy.kotlinDemo.coroutines.reflect

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors


fun main() = runBlocking {
    val clz = TSA::class.java
    val methods = clz.methods
    println()

    val executorService = Executors.newFixedThreadPool(2)
    executorService.asCoroutineDispatcher()

    val tsb = OriginalJavaCoroutineTest.TSB()
}

class TSA {
    suspend fun suspendAnnotationTest() {
        delay(2000)
        println("suspend")
    }
}

