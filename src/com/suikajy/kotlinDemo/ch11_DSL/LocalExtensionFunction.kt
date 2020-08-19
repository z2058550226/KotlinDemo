package com.suikajy.kotlinDemo.ch11_DSL

fun main() {
    val test = TestBuilder().apply {
        // 这里可以像TestBuilder内部一样用类内扩展方法
        "ss".toName()
        age = 2
    }.build()
    println(test)
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