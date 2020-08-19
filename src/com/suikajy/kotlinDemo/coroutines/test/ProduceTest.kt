package com.suikajy.kotlinDemo.coroutines.test

import com.suikajy.kotlinDemo.coroutines.ErrorHandleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.coroutineContext

fun main() {
    test1()
}

private fun test1() = runBlocking {
    ErrorHandleScope.launch {
        downloadResource().consumeEach {
            println("main consume $it")
        }
        println("main over")
    }.join()
    ErrorHandleScope.destroy()
}

private suspend fun downloadResource() =
        CoroutineScope(coroutineContext)
                .produce {
                    try {
                        download().consumeEach {
                            this@produce.send(it)
                        }
                        println("downloadResource over")
                    } finally {
                        println("downloadResource finally")
                    }
                }


private suspend fun download() = coroutineScope {
    produce<Int> {
        delay(1000)

        suspend fun delaySend(element: Int) {
            delay(100)
            send(element)
        }
        try {
            delaySend(1)
            delaySend(2)
            delaySend(3)
            delaySend(2)
            delaySend(3)
            delay(100)
            throw IllegalStateException("test")
            delaySend(4)
        } catch (e: Exception) {
            throw e
        } finally {
            println("download finally")
        }
    }
}
