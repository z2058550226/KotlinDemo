package com.suikajy.kotlinDemo.ch10_annotation_reflect

import com.suikajy.kotlinDemo.ch10_annotation_reflect.bean.Dog
import org.junit.Test
import kotlin.reflect.full.memberProperties

class PersonRT(val name: String, val age: Int)

class TestRT {
    @Test
    fun personTest() {
        val person = PersonRT("Alice", 29)
        val memberProperty = PersonRT::age
        // 成员属性使用get需要传递对象
        println(memberProperty.get(person))
    }
}

fun main(args: Array<String>) {
    val kClz = Dog::class
    val clz = Dog::class.java
    val kProperty = Dog::javaClass
    val doge = Dog("d", "n")
    // 这里等价于java的getClass()
    val jClzRunTime = doge.javaClass
    // 用kotlin扩展属性获取运行时对象的KClass
    val kClzRunTime = doge.javaClass.kotlin

    // 反射获取类名
    println(kClzRunTime.simpleName)
    // 反射获取所有成员属性
    kClzRunTime.memberProperties.forEach { println(it.name) }
    val members = kClzRunTime.members

    // 反射获取函数对象
    val kFunction = ::foo
    // call是父类KCallable中的方法，接收可变参数
    kFunction.call(1)
    // invoke是子类KFunction的方法，但KFunctionN是编译器合成类，这里无法查看源码
    // invoke比call安全，因为它明确指定了参数个数，错误写法会报编译器异常
    kFunction.invoke(23)
    val sumFunc = ::sum
    // 可以省略invoke调用
    println(sumFunc.invoke(3, 3) + sumFunc(3, 1))

    val kProperty0 = ::counter
    // KProperty也是KCallable的子类，调用它的call方法相当于调用getter
    println(kProperty0.call())
    // 如果要调用属性的getter，更好的方式是调用get方法，顶层属性是无参的
    kProperty0.setter.call(33)
    println(kProperty0.get())
}

fun foo(x: Int) = println(x)

fun sum(x: Int, y: Int) = x + y

var counter = 0