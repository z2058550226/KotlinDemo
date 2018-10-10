package com.suikajy.kotlinDemo.ch6_class_system

import java.util.concurrent.Callable

fun main(args: Array<String>) {
    for (i in args.indices) {
        println("Argument $i is ${args[i]}")
    }

    val letters: Array<String> = Array(26) { ('a' + it).toString() }
    println(letters.joinToString("")) // abcdefghijklmnopqrstuvwxyz

    val strings = listOf("a", "b", "c")
    // 使用展开运算符*变成可变参数。
    println("%s/%s/%s".format(*strings.toTypedArray()))

    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)

    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(squares.joinToString()) // 1, 4, 9, 16, 25

    args.forEachIndexed { index, element ->
        println("Argument $index is $element")
    }
}