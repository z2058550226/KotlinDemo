package com.suikajy.kotlinDemo.ch11_DSL

import java.lang.AssertionError
import java.time.LocalDate
import java.time.Period

fun main(args: Array<String>) {
    assertTest2()
    amountTest()
}

// 实现断言

fun assertTest() {
    val s = "suika"
    s should startWith("kot")
}

interface Matcher<T> {
    fun test(value: T)
}

infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix))
            throw AssertionError("String $value does not start with $prefix")
    }
}

// 连续中缀实现断言

fun assertTest2() {
    val s = "suikajy"
    s should start with "sui"
}

object start

class StartWrapper(val value: String) {
    infix fun with(prefix: String) {
        if (!value.startsWith(prefix))
            throw AssertionError(
                    "String $value does not start with $prefix")
    }
}

infix fun String.should(x: start): StartWrapper = StartWrapper(this)

// 在基本数据类型上定义量词扩展：处理日期

val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this

fun amountTest() {
    println(1.days.ago)
    println(1.days.fromNow)
}

// 成员扩展函数：为SQL设计的内部DSL

class Column<T>

open class Table {

    fun integer(name: String): Column<Int> {
        return Column()
    }

    fun varchar(name: String, length: Int): Column<String> {
        return Column()
    }

    // 成员扩展函数
    fun <T> Column<T>.primaryKey(): Column<T> {
        return this@primaryKey
    }

    fun Column<Int>.autoIncrement(): Column<Int> {
        return this@autoIncrement
    }
}

object Country : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 50)
}

fun sqlTest() {
    val c = Column<Int>()
    val table= Table()
    table.apply {
        integer("d").autoIncrement()
    }
    c.apply {

    }
}