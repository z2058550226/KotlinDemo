package com.suikajy.kotlinDemo.ch10_annotation_reflect

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
fun remove(index: Int) {
}

class HasTempFolder {

    @get:Rule
    val folder = TemporaryFolder()

    @Test
    fun testUsingTempFolder() {
        println("asdf")
    }

}

class TemporaryFolder : TestRule {
    override fun apply(p0: Statement, p1: Description?): Statement {
        return MyStatement(p0)
    }
}

class MyStatement(private val base: Statement) : Statement() {
    override fun evaluate() {
        println("before...sth..sth")
        try {
            base.evaluate()
        } finally {
            println("after...sth..sth")
        }
    }
}


