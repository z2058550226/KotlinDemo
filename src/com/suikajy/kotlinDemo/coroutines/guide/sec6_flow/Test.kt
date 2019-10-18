package com.suikajy.kotlinDemo.coroutines.guide.sec6_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun foo(): Flow<Int> = flow {
    // flow builder
    for (i in 1..3) {
        delay(1000) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        foo()
                .buffer()// buffer emissions, don't wait
                .collect { value ->
                    // delay(300) // pretend we are processing it for 300 ms
                    println(value)
                }
    }
    println("Collected in $time ms")
}