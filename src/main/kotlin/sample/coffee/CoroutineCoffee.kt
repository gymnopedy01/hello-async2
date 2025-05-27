package sample.coffee

import kotlinx.coroutines.*
import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

private val worker = newSingleThreadContext("employee")

fun main() {
    measureTimeMillis {
        runBlocking {
            repeat(10) {
                launch(worker) {
                    makeCoffee()
                }

            }
        }
    }.let { logger.debug { ">> elapsed: $it ms" } }
    // Mono.just(1).block() // 기능이 같음
}

private suspend fun makeCoffee() {
    coroutineScope {
        launch {
            grindCoffee()
            brewCoffee()
        }
        launch {
            boilMilk()
            formMilk()
        }
    }
    mixCoffeeAndMilk()
}

private suspend fun grindCoffee() {
    logger.debug { "커피갈기" }
    delay(1000)
    logger.debug { "> 커피가루" }
}

private suspend fun brewCoffee() {
    logger.debug { "커피내리기" }
    delay(1000)
    logger.debug { "> 커피원액" }
}

private suspend fun boilMilk() {
    logger.debug { "우유끓이기" }
    delay(1000)
    logger.debug { "> 데운우유" }
}

private suspend fun formMilk() {
    logger.debug { "우유거품내기" }
    delay(1000)
    logger.debug { "> 거품우유" }
}

private suspend fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    delay(1000)
    logger.debug { "> 까페라떼" }
}