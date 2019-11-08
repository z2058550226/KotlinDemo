package com.suikajy.kotlinDemo.delegate

fun main() {
    val testClass = TestClass()
    testClass.delValue = "sss"
    println(testClass.delValue)

    println("---")

    val cache: Cache = CacheImpl()
    cache.appId = "suika"
    println(cache.appId)
    cache.appId = "suika2"
    println(cache.appId)

    cache.saveAppId("aa")
}

class TestClass {
    var delValue: String? by TDel()

    fun testSet(appid: String) {
        println("set: $appid")
    }
}

interface Cache {
    var appId: String?

    fun saveAppId(appid: String)
}

internal class CacheImpl : Cache {
    private val testClass = TestClass()
    override var appId: String?
        get() = testClass.delValue
        set(value) {
            testClass.delValue = value
        }

    override fun saveAppId(appid: String) = testClass.testSet(appid)

}