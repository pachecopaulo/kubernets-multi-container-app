package com.study.ks8.service

import com.study.ks8.queue.RedisMessagePublisher
import com.study.ks8.repository.RedisRepository
import com.study.ks8.repository.ValuesRepository
import org.springframework.stereotype.Service

@Service
class EventService(
    private val valuesRepository: ValuesRepository,
    private val redisRepository: RedisRepository,
    private val redisMessagePublisher: RedisMessagePublisher
) {
    fun getValuesStoredInPostgres() =
        valuesRepository.findAll()
            .map { it.number }

    fun getValuesStoredInRedis() =
        redisRepository.getAll()

    fun publishEvent(message: String) =
        redisMessagePublisher.publish(message)
}