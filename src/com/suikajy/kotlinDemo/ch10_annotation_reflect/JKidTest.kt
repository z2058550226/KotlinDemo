package com.suikajy.kotlinDemo.ch10_annotation_reflect

import com.suikajy.kotlinDemo.library.jkid.serialization.serialize

@JsonName(name = "name", nickName = "nick")
data class Person(val name: String, val age: Int)

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    println(serialize(person))
}

annotation class JsonName(val name: String, val nickName: String)