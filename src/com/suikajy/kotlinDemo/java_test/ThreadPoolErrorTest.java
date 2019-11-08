package com.suikajy.kotlinDemo.java_test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolErrorTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
//            try {
//                throw new Exception("test exception");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            return;
        };
        for (int i = 0; i < 100; i++) {
            es.execute(runnable);
        }
        es.shutdown();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("o wa ri");

    }
}
