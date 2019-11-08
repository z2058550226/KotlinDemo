package com.suikajy.kotlinDemo.coroutines.test

import com.suikajy.kotlinDemo.coroutines.ErrorHandleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.coroutineContext

fun main() {
    test3()
}

private fun test1() = runBlocking {
    getRes2().consumeEach {
        println("#4 thread: ${Thread.currentThread()}")
        println("consume: $it")
    }

    println("#2 thread: ${Thread.currentThread()}")
}

/**
 * 这里的coroutineScope保证了代码块内的代码必须执行完毕才能结束阻塞（听起来好像没啥卵用）
 */
private suspend fun getRes() = coroutineScope {
    produce<Int> {
        println("#3 thread: ${Thread.currentThread()}")
        for (i in 1..5) {
            delay(100)
            // send(i)
        }
    }
}

private suspend fun getRes2() = CoroutineScope(coroutineContext).produce<Int> {
    println("#3 thread: ${Thread.currentThread()}")
    for (i in 1..5) {
        delay(100)
        send(i)
    }
}

/**
 * 这里总结了一条规律：produce中执行一次send，produce代码块中的代码就会被阻塞，直到ReceiveChannel进行接收
 *
 * 所以不能把一个produce代码块放到一个coroutineScope代码块中，然后再对coroutineScope代码块调用consume方法。
 * 上面的test1就是由于send方法阻塞，并且无人接收导致方法永远无法调用
 */
private fun test2() = runBlocking {
    coroutineScope {
        produce<Int> {
            println("#3 thread: ${Thread.currentThread()}")
            for (i in 1..5) {
                delay(100)
                println("produce: $i")
                send(i)
            }
        }.consumeEach {
            println("#4 thread: ${Thread.currentThread()}")
            println("consume: $it")
        }
    }

    println("#2 thread: ${Thread.currentThread()}")
}

/**
 * 测试suspendCoroutine代码块
 */
private fun test3() = runBlocking {
    ErrorHandleScope.launch {
        withIO {
            for (i in 1..5) {
                getRes3(i).consumeEach {
                    println("#4 thread: ${Thread.currentThread()}")
                    println("consume: $it")
                }
            }
        }
    }.join()
    println("#2 thread: ${Thread.currentThread()}")
    ErrorHandleScope.destroy()
}

private fun CoroutineScope.getRes3(resId: Int) = produce {
    println("#3 thread: ${Thread.currentThread()}")
    for (i in 1..5) {
        if (i == 4) throw IllegalStateException("test exception.")
        delay(100)
        println("send $resId - $i")
        send(i)
    }
}

suspend inline fun <R> withIO(noinline block: suspend CoroutineScope.() -> R): R =
        withContext(Dispatchers.IO, block)