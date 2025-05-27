package sample.coffee

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

suspend fun main() {
    logger.info { "start" }         // Main 에서실행
    delay(1000)
    logger.info { "done" }          // 다른 쓰레드(DefaultExecutor) 에서 실행
}