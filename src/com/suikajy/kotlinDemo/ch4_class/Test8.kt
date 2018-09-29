package com.suikajy.kotlinDemo.ch4_class

import java.io.File
import java.util.Comparator

interface JsonFactory<T> {
    fun fromJson(json: String): T
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

data class Person1(val name: String) {
    object NameComparator : Comparator<Person1> {
        override fun compare(o1: Person1, o2: Person1) = o1.name.compareTo(o2.name)
    }

    companion object Loader : JsonFactory<Person1> {
        override fun fromJson(json: String): Person1 = Person1("")
    }
}

fun <T> fromJson(jsonFactory: JsonFactory<T>): T?{
    return null
}

fun Person1.Loader.fromJson2(text:String){
    Person1.fromJson2("")
}

fun main(args: Array<String>) {
    println(CaseInsensitiveFileComparator.compare(
            File("/User"), File("/user"))) // 0
    val file = listOf(File("/Z"), File("/a"))
    println(file.sortedWith(CaseInsensitiveFileComparator))// [\a, \Z]

    val person1 = listOf(Person1("Bob"), Person1("Alice"))
    println(person1.sortedWith(Person1.NameComparator))
    val newInstance = A.newInstance()
    newInstance.say()

    val personFromJson1 = Person1.Loader.fromJson("")
    val personFromJson2 = Person1.fromJson("")

    fromJson(Person1)
}

class A private constructor() {
    companion object {
        fun newInstance() = A()
    }

    fun say() = println("aa")
}