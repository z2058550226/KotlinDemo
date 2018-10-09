package com.suikajy.kotlinDemo.ch5_lambda

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 21), Person("Bob", 31))
    // 会创建两个中间集合，如果集合很大会影响效率
    println(people.map { it.name }.filter { it.startsWith("A") })
    // 序列可以避免创建中间对象
    println(people.asSequence() // 把集合转换为一个序列
            .map { it.name } // 这里的map是序列接口的扩展函数，它其实是一个序列的装饰者，它只是进行了一层包装，真正执行是在调用toList时执行
            .filter { it.startsWith("A") } // 同样是一个装饰者
            .toList() // 最后调用这个集合的迭代时会对每个元素进行上述操作，然后加工后的元素会留在序列中，遍历之后添加到结果List里面
    ) // [Alice]

    // 序列有时可以跳过不必要的运算
    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 }) // 4

    // 序列的顺序可能会影响性能
    println(people.asSequence().map(Person::name).filter { it.length < 4 }.toList())
    // 先过滤后映射则可以明显减少运算次数
    println(people.asSequence().filter { it.name.length < 4 }.map(Person::name).toList())
}