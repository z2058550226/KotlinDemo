package com.suikajy.kotlinDemo.ch3_function

import java.lang.StringBuilder

fun String.lastChar(): Char = this.get(this.length - 1)

fun String.lastChar2(): Char = get(length - 1)

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = set(length - 1, value)

fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

open class View

class Button : View()

fun View.showOff() = println("I'm a View")
fun Button.showOff() = println("I'm a Button")

fun main(args: Array<String>) {
    val view: View = Button()
    view.showOff()// I'm a View
    val button = Button()
    button.showOff()// I'm a Button

    val s = StringBuilder("abc")
    s.lastChar = 'd'
    println(s) // abd
}