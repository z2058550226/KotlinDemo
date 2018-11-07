package com.suikajy.kotlinDemo.ch11_DSL

// 使用invoke来支持灵活DSL语法

class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) {
        body()
    }
}

fun main(args: Array<String>) {
    val dependencies = DependencyHandler()
    dependencies.compile("org.jetbrains.kotlin:kotlin-stdlib:1.0.0")

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    }

    // 这里其实被转换成了
    dependencies.invoke({
        this.compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    })

    // 这种方式减少了很多的样板代码，语义简单直接，每个单词定义每个功能。
    // 这里和之前的生成html比起来，他们的区别就是像tr和td这种是一个方法
    // 而dependencies是一个函数式类型的对象（实现了invoke），tr等方法利用带接收者的lambda可以实现
    // lambda的隐式传值，在tr中是自建了一个TR()对象来调用带接收者的lambda
    // 而dependencies这种函数式类型对象则可以利用自己本身(隐含的this)来调用这个带接收者的lambda，
    // 进而实现上述写法

    // 上述写法其实等同于这样写，但如果作为DSL，多了一个apply其实也是没有必要的（语法噪声）
    dependencies.apply {
        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    }
}