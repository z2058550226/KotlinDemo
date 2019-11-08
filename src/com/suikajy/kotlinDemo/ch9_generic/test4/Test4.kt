package com.suikajy.kotlinDemo.ch9_generic.test4

fun main() {
    val testProducer: TestProducer<in Child> = TestProducer(22)
    println(testProducer.get())
}

open class Parent

open class Child : Parent()

class TestProducer<T>(private val data: T) {
    fun get(): T = data

    fun consume(someData: T) {
        println(someData)
    }
}