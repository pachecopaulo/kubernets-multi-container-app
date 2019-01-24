package com.study.ks8

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
class DockerAwsAppApplication

fun main(args: Array<String>) {
    runApplication<DockerAwsAppApplication>(*args)
}
