package com.suikajy.kotlinDemo.ch4_class

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval3(expr: Expr): Int =
        when (expr) {
            is Expr.Num -> expr.value
            is Expr.Sum -> eval3(expr.left) + eval3(expr.right)
        }