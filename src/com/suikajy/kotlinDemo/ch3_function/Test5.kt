package com.suikajy.kotlinDemo.ch3_function

const val path = "/Users/yole/kotlin-book/chapter.adoc"

fun main(args: Array<String>) {
    parsePath(path) // Dir: /Users/yole/kotlin-book, name: chapter, ext: adoc
    parsePathWithRegex(path) // Dir: /Users/yole/kotlin-book, name: chapter, ext: adoc
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathWithRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory,fileName,extension) = matchResult.destructured
        println("Dir: $directory, name: $fileName, ext: $extension")
    }
}