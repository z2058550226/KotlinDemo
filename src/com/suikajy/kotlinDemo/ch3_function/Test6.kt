package com.suikajy.kotlinDemo.ch3_function

fun main(args: Array<String>) {
    val kotlinLogo = """| //
        .|//
        .|/ \
    """.trimMargin(".")
    println(kotlinLogo)
    // 输出：
    // | //
    // |//
    // |/ \
    val path = "C:\\Users\\yole\\kotlin-book"
    val path2 = """C:\Users\yole\kotlin-book"""
    println(path == path2) // true

    val price = """$ {'$'} 99.9"""
    println(price)
}