package com.suikajy.kotlinDemo.ch10_annotation_reflect

import com.suikajy.kotlinDemo.library.jkid.JsonExclude
import com.suikajy.kotlinDemo.library.jkid.deserialization.deserialize
import com.suikajy.kotlinDemo.library.jkid.serialization.serialize

@JsonName(name = "name", nickName = "nick")
data class Person(@JsonExclude val name: String, val age: Int)

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    println(serialize(person))
    val json = """{"title":"Catch-22","author":{"name":"J.Heller"}}"""
    val deserializeBook = deserialize<Book>(json)
    println(deserializeBook)
    deserializeBook.author= Author("suikajy")
    println(deserializeBook)
    val json2 = """{"name":"doge","nickName":"ddd"}"""

}

annotation class JsonName(val name: String, val nickName: String)



