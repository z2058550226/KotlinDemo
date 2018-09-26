package com.suikajy.kotlinDemo.ch3_function

import java.lang.IllegalArgumentException

class User(val id: Int, val name: String, val address: String)

// 第一版，硬写
fun saveUser1(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.address}: empty address")
    }
}

// 第二版，提取公共部分为一个函数
fun saveUser2(user: User) {
    validateOut(user, user.name, "Name")
    validateOut(user, user.address, "Address")
}

fun validateOut(user: User, value: String, fieldName: String) {
    if (value.isEmpty())
        throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
}

// 第三版，使用局部函数，减少了冗余的传参，直接利用局部变量
fun saveUser3(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty())
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}

// 第四版，提取逻辑到扩展函数
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty())
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser4(user: User){
    user.validateBeforeSave()
    // 保存到数据库
}
