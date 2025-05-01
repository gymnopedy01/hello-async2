package sample.coffee

import mu.KotlinLogging
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

fun main() {
    measureTimeMillis {
        repeat(10) {
            makeCoffee()
        }
//        makeCoffee()
    }.let { logger.debug { ">> elapsed: $it ms" } }
}


private fun makeCoffee() {
    grindCoffee()
    brewCoffee()
    boilMilk()
    formMilk()
    mixCoffeeAndMilk()
}

private fun grindCoffee() {
    logger.debug { "커피갈기" }
    Thread.sleep(1000)
    logger.debug { "> 커피가루" }
}

private fun brewCoffee() {
    logger.debug { "커피내리기" }
    Thread.sleep(1000)
    logger.debug { "> 커피원액" }
}

private fun boilMilk() {
    logger.debug { "우유끓이기" }
    Thread.sleep(1000)
    logger.debug { "> 데운우유" }
}

private fun formMilk() {
    logger.debug { "우유거품내기" }
    Thread.sleep(1000)
    logger.debug { "> 거품우유" }
}

private fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    Thread.sleep(1000)
    logger.debug { "> 까페라떼" }
}