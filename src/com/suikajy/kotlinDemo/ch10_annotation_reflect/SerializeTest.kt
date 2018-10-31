package com.suikajy.kotlinDemo.ch10_annotation_reflect

import com.suikajy.kotlinDemo.library.jkid.findAnnotation
import com.suikajy.kotlinDemo.library.jkid.joinToStringBuilder
import kotlin.reflect.full.memberProperties

// 这里必须要显示指明注解作用范围。
@Target(AnnotationTarget.PROPERTY)
annotation class MyJsonExclude

@Target(AnnotationTarget.PROPERTY)
annotation class MyJsonName(val name: String)

class PersonTest(@MyJsonExclude val name: String, @MyJsonName("changedAge") val age: Int)

fun main(args: Array<String>) {
    val person = PersonTest("suikajy", 17)
    println(mySerialize(person))
}

fun mySerialize(obj: Any): String {
    val stringBuilder = StringBuilder()
    stringBuilder.mySerializeObj(obj)
    return stringBuilder.toString()
}

fun StringBuilder.mySerializeObj(obj: Any) {
    val kClass = obj.javaClass.kotlin
    val properties = kClass.memberProperties

    properties.filter {
        it.findAnnotation<MyJsonExclude>() == null
    }
            .joinToStringBuilder(this,
                    prefix = "{",
                    postfix = "}") {
                val jsonName = it.findAnnotation<MyJsonName>()
                val propName = jsonName?.name ?: it.name
                append("\"$propName\"")
                append(": ")
                mySerializePropertyValue(it.get(obj))
            }
}

fun StringBuilder.mySerializePropertyValue(value: Any?) {
    when (value) {
        is String -> append("\"$value\"")
        is Number -> append("$value")
        else -> throw IllegalArgumentException("not imp")
    }
}