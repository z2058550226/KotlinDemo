package com.suikajy.kotlinDemo.ch10_annotation_reflect

data class Author(val name: String)

data class Book(val title: String, var author: Author)

fun main(args: Array<String>) {
    val json = """{"name":"doge","nickName":"ddd"}"""
    val json2 = """{"title":"Catch-22","author":{"name":"J.Heller"}}"""

}

class Token {

}

interface JsonObject {
    fun setSimpleProperty(propertyName: String, value: Any?)

    fun createObject(propertyName: String): JsonObject

    fun createArray(propertyName: String): JsonObject
}

