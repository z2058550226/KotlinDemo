import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() = runBlocking {
    val sampleData = getSampleData()
    val (context, beanList) = sampleData
    beanList.forEach {
        it.id = Random.nextInt(10)
        it.name = it.id.toString()
    }
    println(context)
    println(sampleData)

    val c: Char = '永'
    println()
    println(0x0003)
}

private suspend fun getSampleData(): Wrapper {
    delay(1000)
    return Wrapper("3", listOf(Bean(1, "one"), Bean(2, "two"), Bean(3, "three")))
}

private data class Bean(var id: Int, var name: String)

private data class Wrapper(val context: String, val bean: List<Bean>)