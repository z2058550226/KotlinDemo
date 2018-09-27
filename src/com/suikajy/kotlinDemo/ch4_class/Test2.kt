package com.suikajy.kotlinDemo.ch4_class

class Context

open class ViewAndroid {
    constructor(context: Context)

    constructor(context: Context, style: Int)
}

class ButtonAndroid : ViewAndroid {
    constructor(context: Context) : super(context)

    constructor(context: Context, style: Int) : super(context, style)

    constructor(context: Context, tag: String) : this(context)
}

interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User {

}

class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore("@")
}

class FacebookUser(val accountId: Int) : User {
    override val nickname = getFacebookName(accountId)
}

fun getFacebookName(accountId: Int): String {
    return ""
}