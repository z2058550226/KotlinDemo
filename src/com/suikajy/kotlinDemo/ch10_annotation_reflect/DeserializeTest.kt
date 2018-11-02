package com.suikajy.kotlinDemo.ch10_annotation_reflect

import com.suikajy.kotlinDemo.library.jkid.deserialization.JKidException
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaType

data class Author(val name: String)

data class Book(val title: String, var author: Author)

fun main(args: Array<String>) {
    val json = """{"name":"doge","nickName":"ddd"}"""
    val json2 = """{"title":"Catch-22","author":{"name":"J.Heller"}}"""

}

inline fun <reified T : Any> deserialize(json: String): T {
    return deserialize<T>()
}

fun <T : Any> deserialize(): T {
    TODO()
}

class Token {

}

interface JsonObject {
    fun setSimpleProperty(propertyName: String, value: Any?)

    fun createObject(propertyName: String): JsonObject

    fun createArray(propertyName: String): JsonObject
}

interface Seed : JsonObject {
    fun spawn(): Any?

    fun createCompositeProperty(propertyName: String, isList: Boolean): JsonObject

    override fun createObject(propertyName: String) = createCompositeProperty(propertyName, false)

    override fun createArray(propertyName: String) = createCompositeProperty(propertyName, true)
}

class ObjectSeed<out T : Any>(targetClass: KClass<T>,
                              val classInfoCache: ClassInfoCache
) : Seed {

    private val classInfo: ClassInfo<T> = classInfoCache[targetClass]

    private val valueArguments = mutableMapOf<KParameter, Any?>()

    private val seedArguments = mutableMapOf<KParameter, Seed>()

    private val arguments: Map<KParameter, Any?>
        get() = valueArguments +
                seedArguments.mapValues { it.value.spawn() }

    override fun createCompositeProperty(
            propertyName: String, isList: Boolean
    ): Seed {
        val param =
                classInfo.getConstructorParameter(propertyName)
        // 如果指定了要反序列化成的类，则按注解指定的类反序列化
        val deserializeAs =
                classInfo.getDeserializeClass(propertyName)
        val seed = createSeedForType(
                deserializeAs ?: param.type.javaType, isList
        )
        return seed.apply { seedArguments[param] = this }
    }

    override fun setSimpleProperty(propertyName: String, value: Any?) {
        val param =
                classInfo.getConstructorParameter(propertyName)
        valueArguments[param] =
                classInfo.deserializeConstructorArgument(param, value)
    }

    override fun spawn(): T = classInfo.createInstance(arguments)

}

fun Seed.createSeedForType(paramType: Type, isList: Boolean): Seed {
    TODO()
}

class ClassInfo<T : Any>(val cls: KClass<T>) {

    private val constructor = cls.primaryConstructor!!

    private val jsonNameToParamMap = hashMapOf<String, KParameter>()
    private val paramToSerializerMap =
            hashMapOf<KParameter, ValueSerializer<out Any?>>()
    private val jsonNameToDeserializeClassMap =
            hashMapOf<String, Class<out Any>?>()

    init {
        constructor.parameters.forEach { cacheDataForParameter(cls, it) }
    }

    private fun cacheDataForParameter(cls: KClass<T>, it: KParameter) {

    }

    fun getConstructorParameter(propertyName: String): KParameter =
            jsonNameToParamMap[propertyName]!!

    fun deserializeConstructorArgument(
            param: KParameter, value: Any?): Any? {
        val serializer = paramToSerializerMap[param]
        if (serializer != null) return serializer.fromJsonValue(value)

        validateArgumentType(param, value)
        return value
    }

    private fun validateArgumentType(param: KParameter, value: Any?) {
        if (value == null && !param.type.isMarkedNullable) {
            throw JKidException("Received null value for non-null parameter ${param.name}")
        }
        if (value != null && value.javaClass != param.type.javaType) {
            throw JKidException("Type mismatch for parameter ${param.name}: " +
                    "expected ${param.type.javaType}, found ${value.javaClass}")
        }
    }

    fun getDeserializeClass(propertyName: String): Type? {
        TODO()
    }

    fun createInstance(arguments: Map<KParameter, Any?>): T {
        ensureAllParametersPresent(arguments)
        // 反序列化的核心：通过参数与参数值组成的map来调用构造函数
        return constructor.callBy(arguments)
    }

    private fun ensureAllParametersPresent(arguments: Map<KParameter, Any?>) {
        for (param in constructor.parameters) {
            if (arguments[param] == null && !param.isOptional && !param.type.isMarkedNullable) {
                throw JKidException("Missing value for parameter ${param.name}")
            }
        }
    }
}

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

object ByteSerializer : ValueSerializer<Byte> {
    override fun toJsonValue(value: Byte): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromJsonValue(jsonValue: Any?): Byte {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

object BooleanSerializer : ValueSerializer<Boolean> {
    override fun fromJsonValue(jsonValue: Any?): Boolean {
        if (jsonValue !is Boolean) throw JKidException("Boolean expected")
        return jsonValue
    }

    override fun toJsonValue(value: Boolean): Any? {
        return value
    }

}

fun serializerForType(type: Type): ValueSerializer<out Any?>? =
        when (type) {
            Byte::class.java -> ByteSerializer
            Boolean::class.java -> BooleanSerializer
            else -> null
        }

// 为了减少反射操作的开销，所以事先缓存起来。
class ClassInfoCache {

    private val cacheData = mutableMapOf<KClass<*>, ClassInfo<*>>()

    operator fun <T : Any> get(targetClass: KClass<T>): ClassInfo<T> =
            cacheData.getOrPut(targetClass) { ClassInfo(targetClass) } as ClassInfo<T>


}


