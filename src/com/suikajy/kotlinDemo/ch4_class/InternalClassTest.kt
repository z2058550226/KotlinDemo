package com.suikajy.kotlinDemo.ch4_class

import java.io.Serializable

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun setCurrentState(state: State)
}

class Outer {
    inner class Inner {
        fun getOuterReference() = this@Outer
    }
}