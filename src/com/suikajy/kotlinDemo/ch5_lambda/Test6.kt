package com.suikajy.kotlinDemo.ch5_lambda

class Book(val title: String, val authors: List<String>)

fun main(args: Array<String>) {
    val strings = listOf("abc", "def")
    // flatMap做了两件事：首先根据给定集合的每个元素进行变换(toList)，
    // 然后将变换后的多个列表合并成一个列表（或者说平铺）
    println(strings.flatMap { it.toList() })// [a, b, c, d, e, f]
}