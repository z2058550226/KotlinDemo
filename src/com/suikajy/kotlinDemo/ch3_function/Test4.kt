package com.suikajy.kotlinDemo.ch3_function

fun main(args: Array<String>) {
    val strings: List<String> = listOf("first", "second", "fourteenth")
    println(strings.last())

    val numbers: Collection<Int> = setOf(1, 14, 2)
    println(numbers.max())

    val list = listOf("args: ", *args)
    println(list)

    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    val to = 1.to("one")
    println(to.javaClass)

    for ((index, element) in strings.withIndex()) {
        println("$index: $element")
    }

    println("12.345-6.A".split("\\.|-".toRegex()))
    println("12.345-6.A".split(".","-"))
}