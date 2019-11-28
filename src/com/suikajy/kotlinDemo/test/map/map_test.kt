package com.suikajy.kotlinDemo.test.map

private val map = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
fun main() {
    println(getInt("ten"))
    println(getInt("nine"))
    println(getInt("ten"))
}

private fun getInt(name: String): Int {
    return map[name] ?: 10000.also {
        println("init $name")
        map[name] = it
    }
}