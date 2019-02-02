package com.study.ks8.controller

import com.study.ks8.service.EventService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity.status
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

@RestController
@RequestMapping("/values")
class EventController(private val eventService: EventService) {

    @GetMapping("/all")
    fun getAllValues() =
        eventService.getValuesStoredInPostgres()

    @GetMapping("/current")
    fun getCurrentValue() =
        eventService.getValuesStoredInRedis()

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun storeValue(@RequestBody request: Map<String, String>): ResponseEntity<String> =
        request
            .map { it.value }
            .firstOrNull()
            ?.toIntOrNull()
            ?.let {
                when (it > MAX_INDEX) {
                    true -> status(UNPROCESSABLE_ENTITY).body("Index $MAX_INDEX is too high")
                    else -> {
                        eventService.publishEvent(it.toString())
                        status(OK).build()
                    }
                }
            } ?: status(BAD_REQUEST).body("Invalid request payload")

    companion object {
        private const val MAX_INDEX = 40
    }
}