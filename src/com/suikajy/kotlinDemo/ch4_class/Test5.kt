package com.suikajy.kotlinDemo.ch4_class

class Client1(val name: String, val postalCode: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client1

        if (name != other.name) return false
        if (postalCode != other.postalCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }

    override fun toString(): String {
        return "Client1(name='$name', postalCode=$postalCode)"
    }

    fun copy(name: String = this.name,
             postalCode: Int = this.postalCode) =
            Client1(name, postalCode)
}

data class Client2(val name: String, val postalCode: Int)


fun main(args: Array<String>) {
    val client = Client2("Alice", 2416465)
    println(client)

    val client1 = Client2("Alice", 342562)
    val client2 = Client2("Alice", 342562)
    // 在kotlin中==比较的不是引用，而是内容，这里会被编译成equals函数调用
    println(client1 == client2) // false

    val client3 = Client1("Bob", 561666)
    val client4 = client3.copy(postalCode = 311162)
    println(client4)

    val client5 = Client2("suikajy",464654)
    val client6 = client5.copy()
    println(client6)
}