package com.suikajy.kotlinDemo.ch3_function;

public class Test2 {

public static void main(String[] args) {
    C c = new C(1);
    C[] arr = new C[1];
    arr[0] = c;
    A a = new A();
    a.setInterface(() -> arr[0].var = 2);
    a.b.onClick();
    System.out.println(c.var);
    System.out.println(arr[0].var);
}

static class C {
    int var = 1;

    public C(int var) {
        this.var = var;
    }
}

static class A {

    B b;

    void setInterface(B b) {
        this.b = b;
    }
}

interface B {
    void onClick();
}
}
