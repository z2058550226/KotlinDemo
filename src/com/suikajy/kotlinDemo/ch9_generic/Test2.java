package com.suikajy.kotlinDemo.ch9_generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test2<T> {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        JudgmentFunction<ArrayList> function = isHaveThreeElement();
        System.out.println(function.isHaveThreeElement(list));
    }

    // 利用泛型的可擦除性质，为所有类型定义了一个对象，用来返回它本身
    // 这种模式叫做泛型单例工厂
    static UnaryFunction<Object> IDENTITY_FUNCTION = arg -> arg;

    // 这个函数其实整体实现了一个逆变的过程。
    @SuppressWarnings("unchecked")
    static <T> UnaryFunction<T> identityFunction() {
        // 恒等函数很特殊，这种函数因为是返回对象本身，所以可以放心强转
        // 这个强转是点睛之笔，假设A是B的父类，这种强转相当于可以把泛型为A的对象
        // 强转成泛型为B的对象，Android的高版本的findViewById()就是基于这个原理
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

    // 举一反三，可以为所有集合类型判断元素是否大于三
    static JudgmentFunction<List> FUN = list -> list.size() > 2;

    static <T extends List> JudgmentFunction<T> isHaveThreeElement() {
        return (JudgmentFunction<T>) FUN;
    }

    static <T> void foo(T t) {
        UnaryFunction<T> sameT = identityFunction();
        T apply = sameT.apply(t);

    }
}

interface UnaryFunction<T> {
    T apply(T arg);
}

interface JudgmentFunction<T extends List> {
    boolean isHaveThreeElement(T t);
}



