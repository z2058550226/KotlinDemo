package com.suikajy.kotlinDemo.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TDel<T> : ReadWriteProperty<Any?, T?> {
    var a: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        println("getValue() thisRef:$thisRef, property: $property")
        return a
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        println("setValue() thisRef:$thisRef, property: $property, value: $value")
        a = value
    }
}