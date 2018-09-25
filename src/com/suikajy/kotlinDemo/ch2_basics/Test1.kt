package com.suikajy.kotlinDemo.ch2_basics

fun main(args: Array<String>) {
    val name = "world"
    println("hello $name")
    println("hello ${if (args.size > 0) args[0] else "someone"}")
    val message: String
    if (Math.random() > 0.5) {
        message = "1"
        // do something
    } else {
        message = "0"
    }

    println(message)
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b

fun max3(a: Int, b: Int) = if (a > b) a else b

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}