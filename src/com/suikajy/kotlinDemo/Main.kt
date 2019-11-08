package com.suikajy.kotlinDemo

import kotlinx.coroutines.GlobalScope
import com.suikajy.kotlinDemo.ch3_function.lastChar as last

val map = mutableMapOf<Int, String>()

fun main(args: Array<String>) {
    val s = "123456789"
    println(s.substring(0, 4))
    println(1.2e-10 + 2)

    map[1] = "one"

    val arr = arrayListOf(1, 2, 3)
    val joinToString = arr.joinToString { "num: $it" }
    println(joinToString)

    listOf(1, 2, 3).apply {
        filter { it == 2 }.forEach(::println)
    }

    val uri = U(null)
    val set = listOf("a", "b", null)
    println(set.filterNotNull())
    val stackTrace = Throwable().stackTrace

    System.err.println(wrapLogMessage("suika"))

    println("""eee
        sfldj
    """)
}

private const val CALL_STACK_INDEX = 1

private fun wrapLogMessage(message: String): String {
    val stackTrace = Thread.currentThread().stackTrace
    check(stackTrace.size > CALL_STACK_INDEX) { "Synthetic stacktrace didn't have enough elements: are you using proguard?" }
    val className = stackTrace[CALL_STACK_INDEX].fileName
    val methodName = stackTrace[CALL_STACK_INDEX].methodName
    val lineNumber = stackTrace[CALL_STACK_INDEX].lineNumber
    return "[$methodName($className:$lineNumber)]--  $message"
}

data class U(val schemes: String?)