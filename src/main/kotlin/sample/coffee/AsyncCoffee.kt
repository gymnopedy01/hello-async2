package sample.coffee

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger { }

private val employees = Schedulers.newSingle("employee")
fun main() {
    measureTimeMillis {
        Flux.range(1,10).flatMap {
            makeCoffee()
        }.subscribeOn(employees).blockLast()
//        makeCoffee().subscribeOn(employees).block()
    }.let { logger.debug { ">> elapsed : $it ms" } }
}

private fun makeCoffee(): Mono<Unit> {
    return Mono.zip(
        grindCoffee().then(brewCoffee()),
        boilMilk().then(formMilk()),
    ).then(mixCoffeeAndMilk())
}

private fun grindCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피갈기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employees)
        .doOnNext { logger.debug { "> 커피가루" } }
}

private fun brewCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피내리기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employees)
        .doOnNext { logger.debug { "> 커피원액" } }
}

private fun boilMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유끓이기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employees)
        .doOnNext { logger.debug { "> 데운우유" } }
}

private fun formMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유거품내기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employees)
        .doOnNext { logger.debug { "> 거품우유" } }
}

private fun mixCoffeeAndMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피와 우유 섞기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employees)
        .doOnNext { logger.debug { "> 까페라떼" } }
}