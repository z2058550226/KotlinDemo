package com.suikajy.kotlinDemo.ch10_annotation_reflect;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest1 {
    
    public static void main(String[] args) {
        Calculator c = new Calculator();
        //测试 add()方法
        int result = c.add(1, 2);
        if(result == 3){
            System.out.println("add()方法正确");
        }

        //测试 sub()方法
        int result2 = c.sub(2, 1);
        if(result2 == 1){
            System.out.println("sub()方法正确");
        }
    }

    @Test
    //测试 add()方法
    public void testAdd(){
        Calculator c = new Calculator();
        int result = c.add(1, 2);
        Assert.assertEquals(result, 3);
    }

    @Test
    //测试 sub()方法
    public void testSub(){
        Calculator c = new Calculator();
        int result = c.sub(2, 1);
        Assert.assertEquals(result, 1);
    }
    
}
