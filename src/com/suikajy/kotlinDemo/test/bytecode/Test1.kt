package com.suikajy.kotlinDemo.test.bytecode

fun main() {
    println(shouldOverrideUrl("http://by.butter.com"))
}

fun shouldOverrideUrl(url: String?): Boolean {
    return analyzeUrl(url ?: return true)
}

fun analyzeUrl(url: String): Boolean {
    return url.isEmpty()
}

