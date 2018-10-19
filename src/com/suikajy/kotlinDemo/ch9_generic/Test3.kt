package com.suikajy.kotlinDemo.ch9_generic

import com.suikajy.kotlinDemo.ch5_lambda.Person
import java.lang.IllegalArgumentException
import java.util.*

class Test3<T>(val t: T) {
    val k: T = TODO()
}

fun <T> ensureTrailingPeriod(seq: T)
        where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

fun printSum(c: Collection<*>) {
    val intList = c as? List<Int>
            ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}

fun printSum2(c: Collection<Int>) {
    if (c is List<Int>) {
        println(c.sum())
    }
}

inline fun <reified T> isA(value: Any) = value is T

inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        if (element is T) {
            destination.add(element)
        }
    }
    return destination
}

inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java)
}

val list: List<Any> = listOf("a", "sadf")
val list2: MutableList<Any> = mutableListOf("dd", "as", 1, 1.2, Person("d", 12))


interface Producer<out T> {
    fun produce(): T
}

open class Animal {
    fun feed() {

    }
}

class Herd<T : Animal> {

    val list = mutableListOf<T>()

    val size: Int get() = list.size
    operator fun get(i: Int): T = list[i]
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Cat : Animal() {
    fun cleanLitter() {

    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        // 编译异常，Herd<Cat> 不能赋值给Herd<Animal>
//        feedAll(cats)
        val s = listOf("aa")
    }
}

class Stack<E> {

    private val innerList = mutableListOf<E>()

    fun push(e: E) {
        innerList += e
    }

    fun pop(): E {
        val last = innerList.last()
        innerList.removeAt(innerList.lastIndex)
        return last
    }

    fun isEmpty(): Boolean = innerList.isEmpty()

    fun pushAll(src: Iterable<E>) {
        for (e in src) {
            push(e)
        }
    }

    fun pushAll2(src: It<out E>) {

    }
}

class It<T>

fun main(args: Array<String>) {
    val numberStack = Stack<Number>()
    val integers = object : Iterable<Int> {
        override fun iterator(): Iterator<Int> {
            return object : Iterator<Int> {
                override fun next(): Int {
                    return 0
                }

                override fun hasNext(): Boolean {
                    return false
                }
            }
        }
    }
    numberStack.pushAll(integers)
    val it = It<Int>()
    numberStack.pushAll2(it)
}
