package com.suikajy.kotlinDemo.coroutines.understanding_coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

public class TestCoroutine {
    public static void main(String[] args) {
        Continuation<Integer> continuation = new Continuation<Integer>() {
            @Override
            public CoroutineContext getContext() {
                return null;
            }

            @Override
            public void resumeWith(Object o) {

            }
        };
    }
}
