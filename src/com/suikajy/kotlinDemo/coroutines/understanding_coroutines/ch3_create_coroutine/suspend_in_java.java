package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class suspend_in_java {
    public static void main(String[] args) {
        Object result = SnippetKt.notSuspend(new Continuation<Integer>() {
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(Object o) {
                System.out.println("resume with: " + o);
            }
        });
        System.out.println("result: " + result);
    }
}
