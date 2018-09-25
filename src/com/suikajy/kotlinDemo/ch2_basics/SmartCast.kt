package com.suikajy.kotlinDemo.ch2_basics

import java.lang.IllegalArgumentException

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun main(args: Array<String>) {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun eval(expr: Expr): Int {
    if (expr is Num) {
        val n = expr as Num // 这里的强制转换是多余的
        return n.value
    }
    if (expr is Sum) {
        // 变量expr被智能转换为Sum对象
        // 在IDE中会使用高亮背景来表示这个对象经过了类型检查
        return eval(expr.left) + eval(expr.right)
    }
    throw IllegalArgumentException("Unknown Expression")
}

fun eval2(expr: Expr): Int =
        if (expr is Num) {
            expr.value
        } else if (expr is Sum) {
            eval2(expr.left) + eval2(expr.right)
        } else {
            throw IllegalArgumentException("Unknown Expression")
        }

fun eval3(expr: Expr): Int =
        when (expr) {
            is Num -> expr.value
            is Sum -> eval3(expr.left) + eval3(expr.right)
            else -> throw IllegalArgumentException("Unknown Expression")
        }

fun evalWithLogging(e: Expr): Int =
        when (e) {
            is Num -> {
                println(e.value)
                e.value
            }
            is Sum -> {
                val left = evalWithLogging(e.left)
                val right = evalWithLogging(e.right)
                println("sum: $left + $right")
                left + right
            }
            else -> throw IllegalArgumentException("Unknown Expression")
        }