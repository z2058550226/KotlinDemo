package com.suikajy.kotlinDemo.ch5_lambda

fun main(args: Array<String>) {
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2));
    { println(42) }()
    run { println(42) }
}