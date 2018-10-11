package com.suikajy.kotlinDemo.ch7_op_rewrite

import java.math.BigDecimal

data class Point(val x: Int, val y: Int)

data class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, { person -> person.lastName }, Person::firstName)
    }
}

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.times(scale: Double) =
        Point((x * scale).toInt(), (y * scale).toInt())

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}

operator fun Point.unaryMinus() = Point(-x, -y)

operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun main(args: Array<String>) {
    val p = Point(10, 20)
    println(-p) // Point(x=-10, y=-20)

    var bd = BigDecimal.ZERO
    println(bd++) // 0
    println(++bd) // 2

    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2) // false
}

private fun test1() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)// Point(x=40, y=60)

    println(p1 * 1.5) // Point(x=15, y=30)

    println('a' * 3) // aaa

    println(0x0F and 0xF0) // 0

    println(0x0f or 0xF0) // 255

    println(0x1 shl 4) // 16

    var p3 = Point(1, 2)
    p3 += Point(3, 4) // Point(x=4, y=6)
    println(p3)

    val numbers = arrayListOf<Int>()
    // 这里因为是val，所以实际走的是plus而不是plusAssign，也就是先加后赋值
    numbers += 42
    println(numbers) // [42]

    val list = arrayListOf(1, 2)
    // 调用plusAssign更改可变集合
    list += 3
    // 调用plus生成新的不可变集合，底层是java.lang.ArrayList
    val newList = list + listOf(4, 5)
    println(list) // [1, 2, 3]
    println(newList) // [1, 2, 3, 4, 5]
}

