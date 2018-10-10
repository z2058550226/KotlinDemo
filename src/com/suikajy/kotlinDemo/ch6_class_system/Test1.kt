package com.suikajy.kotlinDemo.ch6_class_system

import java.lang.IllegalArgumentException

fun strLenSafe(s: String?): Int =
        if (s != null) s.length else 0

fun strLenSafe2(s: String?): Int =
        s?.length ?: 0

fun main(args: Array<String>) {
    val s: String? = null
    val result = s?.toUpperCase()
    val result2 = if (s != null) s.toUpperCase() else null

    val email: String? = null
    email?.let { sendEmailTo(it) }
    // 这里泛型T被推导成Any?
    printHashCode(null)

    s?.lettt { println("it : $it") }
}

class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val person = other as? Person ?: return false

        if (name != person.name) return false
        if (company != person.company) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (company?.hashCode() ?: 0)
        return result
    }
}

fun Person.countryName(): String {
    val country = this.company?.address?.country
    return if (country != null) country else "Unknown"
}

fun Person.countryName2(): String =
        company?.address?.country ?: "Unknown"

fun foo(s: String?) {
    val t: String = s ?: ""
}

fun foo2(s: String?) {
    val t: String = if (s != null) s else ""
}

fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address")
//    val address = person.company?.address ?: return
    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun ignoreNull(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun sendEmailTo(email: String) { /*...*/
}

fun verifyUserInput(input: String?) {
    // 这里不需要安全调用
    if (input.isNullOrBlank()) {
        println("please fill in the required fields")
    }
}

fun <T> printHashCode(t: T) {
    // 因为泛型默认是可空类型，所以要使用安全调用
    println(t?.hashCode())
}

fun <T : Any> printHashCode2(t: T) {
    // 指定了非空上界，泛型现在是非空类型。
    println(t.hashCode())
}

fun yellAt(javaPerson: JavaPerson) {
    println(javaPerson.name.toUpperCase() + "!!!")
}

fun yellAt2(javaPerson: JavaPerson) {
    println((javaPerson.name ?: "AnyOne").toUpperCase() + "!!!")
    //Error:(101, 17) Kotlin: Type mismatch: inferred type is String! but Int was expected
    val s: String? = javaPerson.name
    val s2: String = javaPerson.name
}

fun <T, R> T.lettt(block: (T) -> R) {
    block(this)
}
