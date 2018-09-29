package com.suikajy.kotlinDemo.ch4_class

interface OnClickListener {
    fun onClick()
}

class Window {
    lateinit var onClickListener: OnClickListener
}

fun main(args: Array<String>) {
    val window = Window()
    window.onClickListener = object : OnClickListener {
        override fun onClick() {

        }
    }
}

fun countClicks(window: Window) {
    var clickCount = 0
    window.onClickListener = object : OnClickListener {
        override fun onClick() {
            clickCount++
        }
    }
}