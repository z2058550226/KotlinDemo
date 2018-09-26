package com.suikajy.kotlinDemo.ch3_function

val UNIX_LINE_SEPARATOR = "\n"
const val UNIX_LINE_SEPARATOR2 = "\n"


var opCount = 0

fun performOperation() {
    opCount++
}

fun main(args: Array<String>) {
    for (i in 1..100) performOperation()
    println("Operation performed $opCount times.")
    val s = "$opCount"
    println(s.lastChar())
}