package com.suikajy.kotlinDemo.ch9_generic

import java.lang.reflect.ParameterizedType

fun main() {
    val a = A()
    val parameterizedType = a.javaClass.genericInterfaces[0] as ParameterizedType
    val clz: Class<*> = parameterizedType.actualTypeArguments[0] as Class<*>
    println(clz)
}

interface Key<T>

class A : Key<String>