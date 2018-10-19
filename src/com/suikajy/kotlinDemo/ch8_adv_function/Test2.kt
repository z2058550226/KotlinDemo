package com.suikajy.kotlinDemo.ch8_adv_function

import com.suikajy.kotlinDemo.ch5_lambda.Person
import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import kotlin.concurrent.withLock

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalulator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }
    }
    return { order -> 1.2 * order.itemCount }
}

class Lock : java.util.concurrent.locks.Lock {
    override fun lock() {

    }

    override fun tryLock(): Boolean {
        return false
    }

    override fun tryLock(time: Long, unit: TimeUnit?): Boolean {
        return false
    }

    override fun unlock() {
    }

    override fun lockInterruptibly() {
    }

    override fun newCondition(): Condition? {
        return null
    }

}

inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}

fun __foo__(l: Lock) {
    println("Before sync")
    l.lock()
    try {
        println("Action")
    } finally {
        l.unlock()
    }
    println("After sync")
}

inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    // ...
}

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        // 因为传参是不固定的，所以这里的body不会被内联，
        // 而synchronized是固定的，会被内联
        synchronized(lock, body)
    }
}

fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br ->
        // 这里使用了非局部return，直接从lambda中对外部函数进行了返回
        return br.readLine()
    }
}

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not found")
}

fun lookForAlice2(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            // 非局部返回
            return@forEach
        }
    }
    println("Alice is not found")
}

fun lookForAlice3(people: List<Person>) {
    people.forEach label@{
        if (it.name == "Alice") {
            println("Found!")
            // 非局部返回
            return@label
        }
    }
    println("Alice is not found")
}

fun lookForAlice4(people: List<Person>) {
    people.forEach(fun(person) {
        if (person.name == "Alice")
            // 默认使用局部返回
            return
        println("${person.name} is not Alice")
    })
}


fun main(args: Array<String>) {
    val l = Lock()
    synchronized(l) {
        // ..
    }
    l.withLock(action)
}