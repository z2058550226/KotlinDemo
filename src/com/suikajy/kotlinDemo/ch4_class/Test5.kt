package com.suikajy.kotlinDemo.ch4_class

class Client(val name: String, val postalCode: Int) {
    override fun toString(): String = "Client(name='$name', postalCode=$postalCode)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (postalCode != other.postalCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }


}

fun main(args: Array<String>) {
    val client =Client("Alice",2416465)
    println(client)

    val client1 = Client("Alice",342562)
    val client2 = Client("Alice",342562)
    // 在kotlin中==比较的不是引用，而是内容，这里会被编译成equals函数调用
    println(client1==client2) // false
}