package com.suikajy.kotlinDemo.ch7_op_rewrite

import java.lang.IndexOutOfBoundsException

operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) {
    return when (index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

fun main(args: Array<String>) {
    val p = Point(10, 20)
    println(p[1]) // 20

    val mp = MutablePoint(10, 20)
    mp[1] = 42
    println(mp) // MutablePoint(x=10, y=42)
}