package com.suikajy.kotlinDemo.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

object ErrorHandleScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = executorService.asCoroutineDispatcher() + job + errorHandler()

    private val executorService = Executors.newSingleThreadExecutor()
    private val job: Job = Job()

    private fun errorHandler() = CoroutineExceptionHandler { _, throwable ->
        println("error handler: $throwable")
    }

    fun destroy() {
        executorService.shutdown()
        job.cancel()
    }
}