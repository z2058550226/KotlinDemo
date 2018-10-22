package com.suikajy.kotlinDemo.ch10_annotation_reflect;

public class Demo {

    public static int add1() {
        return 1;
    }

    @JsonName(value = {"s", "dd"}, number = 12)
    public static int add2() {
        return 2;
    }

    public @interface JsonName {
        String[] value();

        int number() default 1;

        Class<?> cl() default Demo.class;
    }

    interface IApi{
        void api();
    }
}
