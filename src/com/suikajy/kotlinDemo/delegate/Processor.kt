package com.suikajy.kotlinDemo.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Processor {
    private var outputDirty = false

    private inner class SeekProgress : ReadWriteProperty<Any, Float> {
        private var field = 0f
        override fun getValue(thisRef: Any, property: KProperty<*>): Float {
            println("processor get value $field, ${property.name}")
            return field
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
            println("processor set value $value, ${property.name}")
            field = value
            outputDirty = true
        }
    }

    var redden by SeekProgress()
    var smooth by SeekProgress()
    var whiten by SeekProgress()

    fun foo() {
        println("foo")
    }
}