package com.suikajy.kotlinDemo.ch4_class

class User1(val nickname: String)

class User2 constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }
}

class User3(_nickname: String) {
    val nickname = _nickname
}

class User4(val nickname: String, val isSubscribed: Boolean = true)

open class User5(nickname: String)

class TwitterUser(nickname: String) :User5(nickname)

fun main(args: Array<String>) {
    val alice = User4("Alice")
    println(alice.isSubscribed)
    val bob = User4("Bob", false)
    println(bob.isSubscribed)
    val carol = User4("Carol", isSubscribed = false)
    println(carol.isSubscribed)
}