package com.suikajy.kotlinDemo.ch9_generic

val authors = listOf("Dmitry", "Svetlana")

val readers: MutableList<String> = mutableListOf()

val readers2 = mutableListOf<String>()

val <T> List<T>.penultimate: T
    get() = this[size - 2]