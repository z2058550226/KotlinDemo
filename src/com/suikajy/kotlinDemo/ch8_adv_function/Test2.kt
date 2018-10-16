package com.suikajy.kotlinDemo.ch8_adv_function

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition

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

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        // 因为传参是不固定的，所以这里的body不会被内联，
        // 而synchronized是固定的，会被内联
        synchronized(lock, body)
    }
}

fun main(args: Array<String>) {
    val l = Lock()
    synchronized(l) {
        // ..
    }
}