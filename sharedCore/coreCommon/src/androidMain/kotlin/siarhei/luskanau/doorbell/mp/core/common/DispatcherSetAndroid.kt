package siarhei.luskanau.doorbell.mp.core.common

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Single

@Single
internal class DispatcherSetAndroid : DispatcherSet {
    override fun ioDispatcher() = Dispatchers.IO
    override fun mainDispatcher() = Dispatchers.Main
}
