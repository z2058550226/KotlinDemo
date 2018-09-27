package com.suikajy.kotlinDemo.ch4_class

interface Clickable {
    fun click()
    fun showOff() = println("I'm Clickable")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I'm Focusable")
}

class Button : Clickable, Focusable {
    override fun showOff() {
        super<Focusable>.showOff()
        super<Clickable>.showOff()
    }

    override fun click() = println("I was clicked")
}

open class RichButton : Clickable {
    // final方法，不能重写
    fun disable() {}

    // open方法可以重写
    open fun animate() {}

    // override方法默认是open的，如果想禁止重写就要显式标上final
    final override fun click() {}
}

//声明抽象类，和Java声明类似
abstract class Animated {
    //抽象函数默认open
    abstract fun animate()

    //非抽象函数默认不是open的，可以显式加上
    open fun stopAnimating() {}

    fun animateTwice() {}
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk")
}
// 一个错误的例子
//fun TalkativeButton.giveSpeech() {// 一个public的函数暴露了internal的接收者类型TalkativeButton，这里应该加上internal
//    yell() // 外部无法访问接受者对象的私有成员
//    whisper() // 外部无法访问接受者对象的受保护成员
//}

fun main(args: Array<String>) {
    Button().click()// I was clicked
    Button().showOff()
}