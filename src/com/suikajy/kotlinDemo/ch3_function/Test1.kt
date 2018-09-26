@file:JvmName("Test1")

package com.suikajy.kotlinDemo.ch3_function

import java.lang.StringBuilder

fun main(args: Array<String>) {

    test2()
}

fun test1() {
    val set = hashSetOf(1, 3, 51)
    val list = arrayListOf(2, 3, 41, 2)
    val map = hashMapOf(1 to "one", 7 to "Seven", 53 to "Fifty-three")
    println(set.javaClass) // class java.util.HashSet
    println(list.javaClass) // class java.util.ArrayList
    println(map.javaClass) // class java.util.HashMap
}

fun <T> joinToString(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun test2() {
    val list = listOf(1, 2, 3)
    println(joinToString(list, ";", "(", ")"))
    println(joinToString(list, separator = " ", prefix = " ", postfix = "."))
    println(joinToString(list, ", ", "", ""))// 1, 2, 3
    println(joinToString(list))// 1, 2, 3
    println(joinToString(list, "; "))// 1; 2; 3
    println(joinToString(list, postfix = ";", prefix = "# "))// # 1, 2, 3;
}

