package com.suikajy.kotlinDemo.ch1_definition_and_purpose

//数据类
data class Person(val name: String,
                  val age: Int? = null)// 可空类型（Int?）实参的默认值

//顶层函数
fun main(args: Array<String>) {
    val persons = listOf(Person("Alice"),
            Person("Bob", 29)) // 命名参数
    val oldest = persons.maxBy { it.age ?: 0 } // lambda表达式；Elvis/爱偶维斯/运算符
    println("The oldest is $oldest")// 字符串模板

    // output : The oldest is JavaPerson(name=Bob, age=29)
    // 输出利用了自动生成的toString方法
}

//fun findAlice() = findPerson{it.name == "Alice"}