package com.suikajy.kotlinDemo.ch5_lambda

import com.suikajy.kotlinDemo.ch5_lambda.Test9.postponeComputation

fun createRunnable(): Runnable {
    //使用SAM构造方法
    return Runnable { println(42) }
}

fun main(args: Array<String>) {
    postponeComputation(1000) { println(42) }
    postponeComputation(1000, object : Runnable {
        override fun run() {

        }
    })
    val onClickListener = Test9.OnClickListener {

    }

}