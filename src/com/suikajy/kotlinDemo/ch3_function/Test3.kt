package com.suikajy.kotlinDemo.ch3_function

var opCount = 0

fun performOperation() {
    opCount++
}

fun main(args: Array<String>) {
    for (i in 1..100) performOperation()
    println("Operation performed $opCount times.")
}