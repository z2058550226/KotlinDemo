package com.suikajy.kotlinDemo.coroutines.reflect

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

class TestSuspendClass {
    suspend fun t1(): String {
        delay(2000)
        return "t1 result"
    }

    fun t1ForJava(): CompletableFuture<String> = GlobalScope.future { t1() }
}