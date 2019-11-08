package com.suikajy.kotlinDemo.coroutines.test

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

fun main() {
    test2()
}

/**
 * Actor
 */
private fun test2() = runBlocking {
    val scope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Executors.newSingleThreadExecutor().asCoroutineDispatcher() + job + errorHandler()

        val job: Job = Job()

        fun errorHandler() = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("error handler: $throwable")
        }

        fun doSomething() {
            launch {
                downloadResource(actor {
                    consumeEach {
                        println("launch consume: $it")
                    }
                })
                println("launch over")
            }
        }
    }

    download(actor {
        consumeEach {
            delay(1000)
            println("downloadResource consume $it")
        }
    })

    println("main over")
}

private suspend fun downloadResource(sendChannel: SendChannel<Int>) {
    try {
       val unit = coroutineScope {
            download(actor {
                consumeEach {
                    println("downloadResource consume $it")
                    sendChannel.send(it)
                }
            })
        }
        println("downloadResource over")
    } finally {
        println("downloadResource close")
        sendChannel.close()
    }
}

private suspend fun download(sendChannel: SendChannel<Int>) {
    delay(2000)

    suspend fun send(i: Int) {
        println("to send $i")
        sendChannel.send(i)
    }
    try {
        send(1)
        send(2)
        send(3)
        send(2)
        send(3)
        delay(100)
        send(4)
        throw IllegalStateException("test")
    } catch (e: Exception) {
//        throw e
    } finally {
        // 这里必须要close，否则进程永远不会结束
//        delay(11)
        println("close channel")
        sendChannel.close()
    }
}