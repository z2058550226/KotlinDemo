package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.testFurture

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val startTime = System.currentTimeMillis()
    val pool = Executors.newFixedThreadPool(4)

    val result = pool.submit(Callable {
        Thread.sleep(1000)
        return@Callable "Suika"
    }).get(2, TimeUnit.SECONDS)

    println(result)

    pool.shutdown()
    println("spendTime: ${System.currentTimeMillis() - startTime}")
}