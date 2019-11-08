package com.suikajy.kotlinDemo.coroutines.issue

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * The reason to avoid GlobalScope
 */
fun main() {
    test0f1()
}

// the mocked cpu-consume or IO-block task
private fun work(i: Int) {
    Thread.sleep(1000)
    println("Work $i done")
}

// this will takes two seconds
fun test0() = runBlocking {
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

/**
 * flip the order of 'runBlocking' and 'measureTimeMillis'
 *
 * 这里的原因是：在一个scope代码块中调用的launch代码块是不会等待其执行完毕的，
 * 这里的等待是由外部的scope代码块也就是runBlocking来实现的
 */
fun test0f1() = runBlocking {
    val totalTime = measureTimeMillis {
        runBlocking {
            val time = measureTimeMillis {
                for (i in 1..2) {
                    launch {
                        work(i)
                    }
                }
            }
            println("Done in $time ms")
        }
    }
    println("Total done in $totalTime ms")
}

// to get concurrency, we can do this:
fun test1() = runBlocking {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                // The launch(Dispatchers.Default) creates children coroutines in
                // runBlocking scope, so runBlocking waits for their completion automatically.
                launch(Dispatchers.Default) {
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
}

// Don't do this, this test will not output the work result
fun test2() = runBlocking {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                GlobalScope.launch {
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
}

/**
 * However, GlobalScope.launch creates global coroutines. It is now developer’s responsibility to keep track of their lifetime.
 * We can “fix” an approach with GlobalScope by manually keeping track of the launched coroutines and waiting for their completion using join:
 */
fun test3() = runBlocking {
    val time = measureTimeMillis {
        runBlocking {
            val jobs = mutableListOf<Job>()
            for (i in 1..2) {
                jobs += GlobalScope.launch {
                    work(i)
                }
            }
            jobs.forEach { it.join() }
        }
    }
    println("Done in $time ms")
}