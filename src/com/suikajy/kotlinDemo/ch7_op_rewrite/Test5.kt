package com.suikajy.kotlinDemo.ch7_op_rewrite

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class PersonExx(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    //    var age: Int by ObservalbeProperty(age, changeSupport)
//    var salary: Int by ObservalbeProperty(salary, changeSupport)
    var age: Int by Delegates.observable(age) { property, oldValue, newValue ->
        println("property = ${property.name}")
        println("oldValue = $oldValue")
        println("newValue = $newValue")
    }

    var salary: Int by Delegates.observable(salary) { property, oldValue, newValue ->
        println("property = ${property.name}")
        println("oldValue = $oldValue")
        println("newValue = $newValue")
    }
}

class ObservalbeProperty(
        var propValue: Int, val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p: PersonExx, prop: KProperty<*>): Int = propValue

    //这里的KProperty相当于Java中的Field类
    operator fun setValue(p: PersonExx, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person2 {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String
        get() = _attributes["name"]!!
}

class Person3 {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }
    // 这里其实隐藏了_attributes.getValue(p, prop)的调用
    // 这里会被编译为_attributes[prop.name]
    val name: String by _attributes
}

fun main(args: Array<String>) {
    val person = PersonExx("sss", 3, 1)
    person.age = 11
    //property = age
    //oldValue = 3
    //newValue = 11
}