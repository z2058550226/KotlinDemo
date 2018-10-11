package com.suikajy.kotlinDemo;

import java.util.List;

public class JavaMain {
    public static void main(String[] args) {
        var list = List.of("aaa", "bbb", "ccc");
        var listFilter = list.stream().filter(s -> s.equals("ccc")).iterator();
        while (listFilter.hasNext()) {
            System.out.println(listFilter.next());
        }
//        System.out.println(listFilter);
    }
}
