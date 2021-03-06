package com.suikajy.kotlinDemo.test

fun main() {
    val a = A()
    a.age = 4
    println(a.age)
    cA(a)
    println(a.age)
}

private fun cA(a: A) {
    a.age=5
}

private class A {
    var age = 1
}

data class Test(val name: String = "",
                val age: Int = 0)

class TestBuilder {
    var name = ""
    var age = 0

    fun String.toName() {
        name = this
    }

    fun build() = Test(name, age)
}