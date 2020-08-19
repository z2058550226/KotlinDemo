package com.suikajy.kotlinDemo.test

import kotlin.math.max

fun main() {
    println(0 / 0.0f) // output: NaN
    println(1 / 0.0f) // output: Infinity

    val a: Float = 0F/0
    val b: Float = -0F / 0
    println(max(a, b))
    println(1f * a)
    println(a == b)
    println(a > b)
    println(a < b)
}