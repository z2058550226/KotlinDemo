package com.suikajy.kotlinDemo.ch11_DSL

import org.junit.Test
import kotlin.test.assertEquals

class Test1 {

    // 常规的lambda用法
    fun buildString(buildAction: (StringBuilder) -> Unit): String {
        val sb = StringBuilder()
        buildAction(sb)
        return sb.toString()
    }

    @Test
    fun testBuildString1() {
        // 这种用法很不DSL，因为要使用it.来指定append的调用者
        // DSL语言最好是将被构造者（这里被构造的String对象）默认当成lambda内上下文环境
        val string = buildString {
            it.append("sui")
            it.append("ka")
        }
        assertEquals(string, "suika")
    }

    fun buildString2(buildAction: StringBuilder.() -> Unit): String {
        val sb = StringBuilder()
        sb.buildAction()
        return sb.toString()
    }

    @Test
    fun testBuildString2() {
        val string = buildString2 {
            append("sui")
            append("ka")
        }
        string should "suika"
    }

    infix fun String.should(comparedStr: String) {
        assertEquals(this, comparedStr)
    }
}

@HTMLTag
open class Tag {
    fun table(init: TABLE.() -> Unit) {
        TABLE().init()
    }
}

@HTMLTag
class TABLE : Tag() {
    fun tr(init: TR.() -> Unit) {
        TR().init()
    }
}

@HTMLTag
class TR : Tag() {
    fun td(init: TD.() -> Unit) {
        TD().init()
    }
}

@HTMLTag
class TD : Tag()

fun createHTML(): Tag {
    return Tag()
}

@DslMarker
@Target(AnnotationTarget.CLASS)
annotation class HTMLTag

fun createSimpleTable() = createHTML()
        .table {
            tr {
                td {
                }
            }
        }

fun main(args: Array<String>) {
//    createSimpleTable()
    var a: (() -> Unit)? = { println("test") }
    println(a?.invoke())
}

typealias OscarWinner = Map<String, String>

fun typeAliasTest1(oscarWinner: OscarWinner) {

}

fun typeAliasTest2(oscarWinner: Map<String, String>) {

}

operator fun Int.invoke() {
    println(this)
}

class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name")
    }
}

fun typeAliasTest3() {
    val oscarWinner: OscarWinner = mapOf("a" to "b")
    typeAliasTest2(oscarWinner)
    val s = "suikajy"
    // invoke约定的例子
    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmitry")
    // 也可以直接这么写
    Greeter("11")("Dmitry")
    // invoke约定的一种会出乱子的写法
    1()
}




