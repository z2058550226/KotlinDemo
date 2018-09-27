package com.suikajy.kotlinDemo.ch4_class

class LengthCounter {
    var counter: Int = 0
        private set // 不能在类外部修改这个属性

    fun addWord(word: String) {
        counter += word.length
    }
}