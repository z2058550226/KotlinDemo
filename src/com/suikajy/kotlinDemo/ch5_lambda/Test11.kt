package com.suikajy.kotlinDemo.ch5_lambda

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know all the alphabet!")
    return result.toString()
}

fun alphabet2(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {
        for (letter in 'A'..'Z') {
            // 显式地声明this指代stringBuilder
            this.append(letter)
        }
        // 可以省略this
        append("\nNow I know all the alphabet!")
        // 返回值
        this.toString()
    }
}

fun alphabet3() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know all the alphabet!")
    toString()
}

fun alphabet4() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know all the alphabet!")
}.toString()

fun alphabet5() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know all the alphabet!")
}

fun main(args: Array<String>) {
    println(alphabet()) // ABCDEFGHIJKLMNOPQRSTUVWXYZ   Now I know all the alphabet!
}