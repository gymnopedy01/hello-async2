package sample.cost

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    val latcher = CountDownLatch(10_000)
    val count = AtomicLong()
    //부하가 많이 걸리는 작업(덧셈연산 10만번)을 10,000개의 쓰레드로 작업하여 측정
    measureTimeMillis {
        repeat(10_000) {
            thread {
                repeat(100_000) {
                    count.addAndGet(1)
                }
                latcher.countDown()
            }
        }
        latcher.await()
    }.let { println("count: $count, elapsed: $it ms") }
}