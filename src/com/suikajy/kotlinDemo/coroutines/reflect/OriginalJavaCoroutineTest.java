package com.suikajy.kotlinDemo.coroutines.reflect;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OriginalJavaCoroutineTest {

    public static void main(String[] args) {
        TSB tsb = new TSB();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorCoroutineDispatcher dispatcher = ExecutorsKt.from(executorService);
        Continuation<Unit> unitContinuation = new Continuation<Unit>() {
            @Override
            public CoroutineContext getContext() {
                return dispatcher;
            }

            @Override
            public void resumeWith(Object o) {

            }
        };
        String re = tsb.suspendFunction(unitContinuation);
        System.out.println("re: " + re);

        try {
            Object o = BuildersKt.runBlocking(dispatcher, new Function2<CoroutineScope, Continuation<? super Object>, Object>() {
                @Override
                public Object invoke(CoroutineScope coroutineScope, Continuation<? super Object> continuation) {
                    Continuation<Unit> unitContinuation = new Continuation<Unit>() {
                        @Override
                        public CoroutineContext getContext() {
                            return continuation.getContext();
                        }

                        @Override
                        public void resumeWith(Object o) {

                        }
                    };
                    TSA tsa = new TSA();
                    return tsa.suspendAnnotationTest(unitContinuation);
//                    return tsb.suspendFunction(unitContinuation);
                }
            });
            System.out.println(o);
            dispatcher.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class TSB {
        String suspendFunction(Continuation<Unit> continuation) {
            DelayKt.delay(2000, continuation);
            return "test ss suspend";
        }
    }
}
