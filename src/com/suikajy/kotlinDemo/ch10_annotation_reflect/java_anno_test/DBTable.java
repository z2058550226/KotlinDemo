package com.suikajy.kotlinDemo.ch10_annotation_reflect.java_anno_test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Applies to classes only
public @interface DBTable {

    String name() default "";

}
