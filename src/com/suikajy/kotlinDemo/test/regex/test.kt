package com.suikajy.kotlinDemo.test.regex

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

fun main() {
    Observable.interval(0, 1, TimeUnit.SECONDS)
            .takeUntil {
                it > 5
            }.subscribe(object : Observer<Long> {
                override fun onComplete() {
                    println("onComplete:  ${System.currentTimeMillis()}")
                }

                override fun onSubscribe(p0: Disposable?) {
                }

                override fun onNext(p0: Long?) {
                    println("onNext: p0:$p0, time:${System.currentTimeMillis()}")
                }

                override fun onError(p0: Throwable?) {
                }

            })
    sleep(10000)
}

fun String.isPhoneNum(): Boolean {
    return Regex("""^[1][3-9]\d{9}$""") matches this
}