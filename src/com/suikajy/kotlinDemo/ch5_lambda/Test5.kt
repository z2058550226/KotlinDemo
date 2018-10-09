package com.suikajy.kotlinDemo.ch5_lambda

fun main(args: Array<String>) {
val people = listOf(Person("Alice", 21), Person("Bob", 31), Person("Suikajy", 21))
// 根据年龄分组，键值为年龄，类型为Int
println(people.groupBy { it.age }) // {21=[Person(name=Alice, age=21), Person(name=Suikajy, age=21)], 31=[Person(name=Bob, age=31)]}

val list = listOf("a", "ab", "b")
// 根据首字母分组
println(list.groupBy(String::first)) // {a=[a, ab], b=[b]}
}