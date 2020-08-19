package com.suikajy.kotlinDemo.test.init

open class Parent {

    init {
        println("parent init called")
    }
}

class Child : Parent() {
    init {
        println("Child init called")
    }
}

fun main() {
    Child()
}