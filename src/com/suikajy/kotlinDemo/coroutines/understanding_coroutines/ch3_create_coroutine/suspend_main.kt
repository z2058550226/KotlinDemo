package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlinx.coroutines.delay

// 支持主函数添加挂起关键字
suspend fun main() {
    delay(1000)
}