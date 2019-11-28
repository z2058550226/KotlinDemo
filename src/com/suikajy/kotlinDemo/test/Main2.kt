package com.suikajy.kotlinDemo.test

fun main() {
    var a: A? = null
    a?.name = getName()
}

fun getName(): String {
    return "suika".also(::println)
}

data class A(var name: String)