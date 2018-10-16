package com.suikajy.kotlinDemo.ch8_adv_function

val sum: (Int, Int) -> Int = { x, y -> x + y }
val action: () -> Unit = { println(42) }

var canReturnNull: (Int, Int) -> Int? = { x, y -> null }
var funOrNull: ((Int, Int) -> Int)? = null

fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("result = $result")
}

fun main(args: Array<String>) {
    twoAndThree() { a, b -> a + b } // result = 5
    twoAndThree() { a, b -> a * b } // result = 6
    val list = listOf(1, 2, 3)
    println(list.joinToString())
}

fun <T> Collection<T>.joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        limit: Int = -1,
        truncated: CharSequence = "...",
        transform: ((T) -> CharSequence)? = { it.toString() }
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        val str = transform?.invoke(element) ?: element.toString()
        result.append(str)
    }
    result.append(postfix)
    return result.toString()
}