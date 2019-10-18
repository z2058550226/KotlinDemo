package com.suikajy.kotlinDemo.coroutines.reflect;

import java.util.concurrent.CompletableFuture;

public class CoroutineJavaSupportTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1# " + System.currentTimeMillis());
        TestSuspendClass testSuspendClass = new TestSuspendClass();
        CompletableFuture<String> future = testSuspendClass.t1ForJava();
        Thread.sleep(3000);
        String join = future.join();
        String join2 = future.join();
        System.out.println("2# " + System.currentTimeMillis());
        System.out.println(join);
        System.out.println(join2);
    }
}
