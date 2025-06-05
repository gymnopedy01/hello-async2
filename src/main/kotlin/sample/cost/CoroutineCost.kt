package sample.cost

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

// thread   : count: 1000000000, elapsed: 19789 ms
// reactor  : count: 1000000000, elapsed: 10404 ms
// coroutine: count: 1000000000, elapsed: 4973 ms


fun main() {
    val count = AtomicLong()
    measureTimeMillis {
        runBlocking {
            repeat(10_000) {
                launch {
                    repeat(100_000) {
                        count.addAndGet(1)
                    }

                }
            }

        }
    }.let { println("count: $count, elapsed: $it ms") }

}