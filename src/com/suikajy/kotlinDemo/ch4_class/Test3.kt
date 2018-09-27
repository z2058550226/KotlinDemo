package com.suikajy.kotlinDemo.ch4_class

class UserT3(val name: String) {
    var address: String = "unspecified"
        set(value) {
            println("""
        Address was changed for $name:
        "$field" -> "$value"
            """.trimIndent())
            field = value
        }
}

fun main(args: Array<String>) {
    val userT3 = UserT3("Alice")
    userT3.address = "Elsenheimerstrasse 47, 80687 Muenchen"
}