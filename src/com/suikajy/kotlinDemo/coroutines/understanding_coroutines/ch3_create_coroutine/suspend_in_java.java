package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class suspend_in_java {
    public static void main(String[] args) throws InterruptedException {
        final long st = System.currentTimeMillis();
        Object result = SnippetKt.realSuspend(new Continuation<Integer>() {
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(Object o) {
                System.out.println("resumeWith " + Thread.currentThread().toString());
                System.out.println("resume with: " + o);
            }
        });
        System.out.println("result: " + result);
        Thread.sleep(3000L);
        System.out.println("main spend: " + (System.currentTimeMillis() - st));
    }
}
