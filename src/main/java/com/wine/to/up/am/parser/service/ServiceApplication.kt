package com.wine.to.up.am.parser.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@ComponentScan("com.wine.to.up")
@EnableSwagger2
class ServiceApplication

fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}
