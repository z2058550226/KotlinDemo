package com.suikajy.kotlinDemo.ch2_basics

import java.io.BufferedReader
import java.lang.NumberFormatException
import java.util.*

val oneToTen = 1..10 //定义一个区间常量

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 5 == 0 -> "Fizz"
    i % 3 == 0 -> "Buzz"
    else -> "$i"
}

fun main(args: Array<String>) {
//    for (i in 1..100) println(fizzBuzz(i))
//    for(i in 100 downTo 1 step 2) println(fizzBuzz(i))
//    for (i in 1 until 101) println(fizzBuzz(i))
    test1()
    test2()
    test3()
}

fun test1() {
    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}

fun test2() {
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    in '0'..'9' -> "It's a digit!"
    else -> "I don't know..."
}

fun test3() {
    println("Kotlin" in "Java".."Scala")
    println("Kotlin" in setOf("Java", "Scala"))

}

fun test4() {
    val num = 100
    val percentage =
            if (num in 0..100) {
                num
            } else {
                throw IllegalArgumentException(
                        "A percentage value must be between O and 100: $num")
            }
}

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun readNumberAndPrint(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}

