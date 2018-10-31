package com.suikajy.kotlinDemo.ch10_annotation_reflect.java_anno_test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {

    int id() default -1;

    String description() default "";

}///:~
