package com.suikajy.kotlinDemo.ch4_class

class Person

object Payroll {
    val allEmployee = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployee) {
            //...
        }
    }
}

fun main(args: Array<String>) {
    Payroll.allEmployee.add(Person())
    Payroll.calculateSalary()
}