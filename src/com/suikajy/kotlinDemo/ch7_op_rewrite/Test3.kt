package com.suikajy.kotlinDemo.ch7_op_rewrite

class Point2(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

data class NameComponents(val name: String, val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}

fun splitFilename2(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

fun printEntries(map: Map<String, String>) {
    // 在in循环中用解构声明
    for ((key, value) in map) {
        println("$key -> $value")
    }
}

fun main(args: Array<String>) {
    val p = Point(10, 20)
    val (x, y) = p
    println(x) // 10
    println(y) // 20

    val (name, ext) = splitFilename("example.kt")
    println(name) // example
    println(ext) // kt

    val map = mapOf("Oracle" to "Java", "jetBrain" to "Kotlin")
    printEntries(map)
    //Oracle -> Java
    //jetBrain -> Kotlin
}