package com.suikajy.kotlinDemo.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    GlobalScope.launch {
        delay(1000L)
        println("World")
    }

    println("Hello,")
    TimeUnit.SECONDS.sleep(2000L)
}