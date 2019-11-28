package com.suikajy.kotlinDemo.test.reflection

object Routers {
    val path = "path"

    fun loadRouter(routerMap: MutableMap<String, Class<*>>) {
        routerMap.put("/testa", String::class.java)
        routerMap.put("/testb", Int::class.java)
    }
}

fun main() {
    val kClz = Class.forName("com.suikajy.kotlinDemo.test.reflection.Routers").kotlin
    val sampleMap = mutableMapOf("test" to Long::class.java)
    kClz.members.forEach {
        println(it.name)
        if (it == Routers::loadRouter) {
            it.callBy(mapOf(it.parameters[0] to sampleMap))
        }
    }
    println(sampleMap)
}