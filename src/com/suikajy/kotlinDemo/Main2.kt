package com.suikajy.kotlinDemo

import java.net.URLConnection

fun main() {
    val last = "fdjsl.txt".split('.').last()
    println(last)
    val guessContentTypeFromName = URLConnection.guessContentTypeFromName("")
    println(guessContentTypeFromName)
}