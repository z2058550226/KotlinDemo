package com.suikajy.kotlinDemo.ch4_class

import java.io.File
import java.util.Comparator

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

fun main(args: Array<String>) {
    println(CaseInsensitiveFileComparator.compare(
            File("/User"), File("/user"))) // 0
    val file = listOf(File("/Z"), File("/a"))
    println(file.sortedWith(CaseInsensitiveFileComparator))// [\a, \Z]
}