package siarhei.luskanau.doorbell.mp.core.common

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherSet {
    fun ioDispatcher(): CoroutineDispatcher

    fun mainDispatcher(): CoroutineDispatcher
}
