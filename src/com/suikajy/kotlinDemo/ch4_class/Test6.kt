package com.suikajy.kotlinDemo.ch4_class

class DelegatingCollection1<T> : Collection<T> {

    private val innerList = arrayListOf<T>()

    override val size: Int = innerList.size
    override fun contains(element: T) = innerList.contains(element)
    override fun containsAll(elements: Collection<T>) = innerList.containsAll(elements)
    override fun isEmpty() = innerList.isEmpty()
    override fun iterator() = innerList.iterator()
}

class DelegatingCollection2<T>(
        innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList

class CountingSet<T>(
        val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main(args: Array<String>) {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
    // 3 objects were added, 2 remain
}