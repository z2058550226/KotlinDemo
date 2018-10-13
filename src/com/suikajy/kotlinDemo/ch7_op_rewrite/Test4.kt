package com.suikajy.kotlinDemo.ch7_op_rewrite

class PersonEx(val name: String) {
    //    private var _emails: List<Email>? = null
//    val emails: List<Email>
//        get() {
//            if (_emails == null) {
//                _emails = loadEmails(this)
//            }
//            return _emails!!
//        }
    val emails by lazy { loadEmails(this) }
}

class Email(val title: String)

fun loadEmails(person: PersonEx): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(Email("em1"), Email("em2"))
}

fun main(args: Array<String>) {

}