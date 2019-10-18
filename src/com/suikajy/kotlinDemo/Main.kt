package com.suikajy.kotlinDemo

import kotlinx.coroutines.GlobalScope
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

    listOf(1, 2, 3).apply {
        filterNot { it == 2 }.forEach(::println)
    }

    val uri = U(null)
    val set = listOf("a", "b", null)
    println(set.filterNotNull())

}


data class U(val schemes: String?)