package com.study.ks8.queue

import com.study.ks8.entity.Values
import com.study.ks8.entity.Values.Companion.generateFibBasedUntilIndex
import com.study.ks8.repository.RedisRepository
import com.study.ks8.repository.ValuesRepository
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener

class ValuesMessageSubscriber(
    private val redisRepository: RedisRepository,
    private val valuesRepository: ValuesRepository
) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        message.toString()
            .toIntOrNull()
            ?.let { index ->
                generateFibBasedUntilIndex(index = index)
                    .let { fibValue ->
                        val param = Pair(index.toString(), fibValue.toString())
                        redisRepository.persist(message = param)
                        valuesRepository.save(Values(number = index))
                    }
            }
    }
}