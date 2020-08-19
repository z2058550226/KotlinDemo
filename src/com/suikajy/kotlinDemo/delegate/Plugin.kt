package com.suikajy.kotlinDemo.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

class Plugin {
    val processor = Processor()
    var redden by InnerProcessor()
    var smooth: Float = 0f
        set(value) {
            field = value
            processor.smooth = value
        }
    var whiten: Float = 0f
        set(value) {
            field = value
            processor.whiten = value
        }

    @Suppress("UNCHECKED_CAST")
    private inner class InnerProcessor : ReadWriteProperty<Plugin, Float> {
        override fun getValue(thisRef: Plugin, property: KProperty<*>): Float {
            return processor::class.declaredMemberProperties.find { it.name == property.name }!!.let {
                (it as KProperty1<Processor, Float>).get(processor)
            }
        }

        override fun setValue(thisRef: Plugin, property: KProperty<*>, value: Float) {
            processor::class.declaredMemberProperties.find { it.name == property.name }!!.let {
                (it as KMutableProperty1<Processor, Float>).set(processor, value)
            }
        }
    }
}

fun main() {
    val plugin = Plugin()
    plugin.redden = 120f
    println(plugin.redden)
}