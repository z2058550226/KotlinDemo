package com.suikajy.kotlinDemo.ch5_lambda

// 找出年龄最大的人
data class Person(val name: String, val age: Int)

// 传统的写法
fun findOldest(people: List<Person>) {
    var maxAge = 0
    var oldestPerson: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            oldestPerson = person
        }
    }
    println(oldestPerson)
}

// 大佬的写法
fun findOldest() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    val oldest = people.maxBy { it.age }
    println(oldest)
}

fun salute() = println("Salute!")

fun Person.isAdult() = age >= 21

// 巨佬的写法
fun findOldest2() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    val oldest = people.maxBy(Person::age)
    val oldest1 = people.maxBy({ person: Person -> person.age })
    val oldest2 = people.maxBy() { person: Person -> person.age }
    val oldest3 = people.maxBy { person: Person -> person.age }
    val oldest4 = people.maxBy({ person -> person.age })
    val oldest5 = people.maxBy() { person -> person.age }
    val oldest6 = people.maxBy { person -> person.age }
    val oldest7 = people.maxBy({ it.age })
    val oldest8 = people.maxBy() { it.age }
    val oldest9 = people.maxBy { it.age }
    val getAge = { p: Person -> p.age }
    val oldest10 = people.maxBy(getAge)
    val oldest11 = people.maxBy {
        println(it.name)
        it.age
    }
    val getAge2 = Person::age
    println(oldest)
    val names = people.joinToString(separator = " ") { person -> person.name }
    println(names) // Alice Bob
    run(::salute)
    val createPerson = ::Person
    val person = createPerson("Alice", 33)
    val predicate = person::isAdult// 绑定引用
    println(predicate())// 无需传入接受者对象
    val predicate2 = Person::isAdult// 常规扩展函数引用
    println(predicate2(person))// 要传入接受者对象

    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 }) //[2, 4]

    println(people.filter { it.age > 30 })//[JavaPerson(name=Bob, age=31)]

    println(list.map { it * it }) // [1, 4, 9, 16]

    println(people.filter { it.age > 30 }.map(Person::name)) //[Bob]
}

fun main(args: Array<String>) {
    findOldest2()
}