package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine

import kotlin.coroutines.*

fun main() {
// CoroutineContext.Element
    suspend {
        // 协程体内部获取Context中的值，可以使用全局对象coroutineContext。
        // 这里coroutineContext是一个语法，但用起来像是全局对象，作用就是拿到当前协程体的context
        // 这里的get重载没有类型转换，是因为每个类型的Element在context中只有一个对象，又因为CoroutineContext.Key中的泛型是伴生对象的父类中的泛型
        // ，所以可以拿出来进行类型判断（参考retrofit的gson convertor反射拿到运行时泛型）
        val name = coroutineContext[CoroutineName]?.name
        println(name)
        throw IllegalAccessException("suika")
    }.startCoroutine(object : Continuation<Int> {
        // 协程中的每一个元素需要用它的伴生对象作为Key值，这保证了上下文中每个类型的Element只有一个
        // 下面是重复添加CoroutineName这个Element的代码，结果是后来者覆盖
        override val context: CoroutineContext
            get() = CoroutineName("suika") +CoroutineName("ss")+ CoroutineExceptionHandler {
                println("on error #1")
            }

        override fun resumeWith(result: Result<Int>) {
            result.onFailure {
                context[CoroutineExceptionHandler]?.onError(it)
            }
        }
    })
}

class CoroutineName(val name: String) : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<CoroutineName>
}

class CoroutineExceptionHandler(private val onErrorAction: (Throwable) -> Unit) : AbstractCoroutineContextElement(ErrKey) {
    companion object ErrKey : CoroutineContext.Key<CoroutineExceptionHandler>

    fun onError(t: Throwable) {
        t.printStackTrace()
        onErrorAction(t)
    }
}