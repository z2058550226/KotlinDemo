package com.suikajy.kotlinDemo.coroutines.understanding_coroutines.ch3_create_coroutine;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SnippetJava {
    public static void realSuspend(){
//        ContinuationKt.
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("sss");
//        final ExecutorService executorService = Executors.newFixedThreadPool(3);
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().isDaemon());
//                System.out.println(executorService.isShutdown());
//
//            }
//        });
//        executorService.shutdown();
        Observable.create(observableEmitter -> {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().isDaemon());
            observableEmitter.onNext("ss");
        }).subscribeOn(Schedulers.io())
                .subscribe(System.out::println);
        Thread.sleep(2000);
        System.out.println("eee");
    }
}
