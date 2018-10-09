package com.suikajy.kotlinDemo.ch5_lambda

import java.io.File

fun main(args: Array<String>) {
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())// 5050
}

fun File.isInsideHiddenDirectory() =
        generateSequence(this) { it.parentFile }
                .any { it.isHidden }