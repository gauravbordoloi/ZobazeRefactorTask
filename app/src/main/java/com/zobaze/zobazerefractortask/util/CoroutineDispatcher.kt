package com.zobaze.zobazerefractortask.util

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * optimized to perform network I/O outside of the main thread.
 */
fun CoroutineScope.launchIO(
    exceptionHandler: (suspend (Throwable) -> Unit)? = null,
    block: suspend (CoroutineScope) -> Unit
): Job {
    val handler = CoroutineExceptionHandler { _, throwable ->
        if (exceptionHandler != null) {
            launch {
                exceptionHandler(throwable)
            }
        } else {
            throw throwable
        }
    }
    return launch(Dispatchers.IO + handler) {
        block(this)
    }
}