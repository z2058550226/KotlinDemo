package com.suikajy.kotlinDemo.stl

import kotlin.random.Random


fun main() {
    val arr = mutableListOf(10, 20, 30, 40)
    println(getRandAdType2Show(arr))
}

fun getRandAdType2Show(mProbabilityList: List<Int>): Type {
    val adTypes = Type.values()
    for (i in mProbabilityList.indices) {
        if (i >= adTypes.size - 1) break
        val sum = mProbabilityList.takeLast(mProbabilityList.size - i).sum()
        val random = Random.nextInt(sum)
        println("----------------------------")
        println("sum = $sum")
        println("random = $random")
        println("i = $i")
        println("mProbabilityList[i] = ${mProbabilityList[i]}")
        if (random < mProbabilityList[i]) {
            return adTypes[i]
        }
    }
    return Type.NONE
}

enum class Type {
    A, B, C, D, NONE
}