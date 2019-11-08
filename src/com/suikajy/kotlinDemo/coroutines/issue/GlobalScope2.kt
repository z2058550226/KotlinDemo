package com.suikajy.kotlinDemo.coroutines.issue

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

private suspend fun work(i: Int) = withContext(Dispatchers.Default) {
    Thread.sleep(1000)
    println("Work $i done")
}

// right concurrency code, this run in 1 second
fun main() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                launch {
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
}