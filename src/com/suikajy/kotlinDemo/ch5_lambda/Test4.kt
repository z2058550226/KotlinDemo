package com.suikajy.kotlinDemo.ch5_lambda

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 21), Person("Bob", 31))
    // 是否所有元素满足函数式条件，相当于离散数学中的任意∀
    println(people.all(canBeInClub27)) // false
    // 是否有某个元素满足函数式条件，相当于离散中的存在∃
    println(people.any(canBeInClub27)) // true
    // 判断集合中有多少元素满足条件
    println(people.count(canBeInClub27)) // 1
    // count也可以用size属性获取，但会创建中间对象效率低，自行取舍
    println(people.filter(canBeInClub27).size) // 和上句等效
    // 找出第一个符合条件的元素
    println(people.find(canBeInClub27)) // JavaPerson(name=Alice, age=21)
}