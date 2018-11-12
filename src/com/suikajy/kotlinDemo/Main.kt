package com.suikajy.kotlinDemo

import com.suikajy.kotlinDemo.ch3_function.lastChar as last

val map = mutableMapOf<Int, String>()

fun main(args: Array<String>) {
    val s = "123456789"
    println(s.substring(0, 4))
    println(1.2e-10 + 2)

    map[1] = "one"

    val arr = arrayListOf(1, 2, 3)
    val joinToString = arr.joinToString { "num: $it" }
    println(joinToString)
}
