package com.suikajy.kotlinDemo.ch5_lambda

fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach { println("$prefix $it") }
}