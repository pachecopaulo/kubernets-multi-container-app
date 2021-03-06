package com.study.ks8.config

import com.study.ks8.queue.ValuesMessageSubscriber
import com.study.ks8.queue.MessagePublisher
import com.study.ks8.queue.RedisMessagePublisher
import com.study.ks8.repository.RedisRepository
import com.study.ks8.repository.ValuesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import javax.annotation.Resource

@Configuration
@Import(DatabaseConfiguration::class)
class SubscriberConfiguration {

    @Resource
    private lateinit var redisRepository: RedisRepository

    @Resource
    private lateinit var valuesRepository: ValuesRepository

    @Resource(name = "redisConnectionFactoryConfig")
    private lateinit var redisConnectionFactory: RedisConnectionFactory

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @Bean
    fun messageListener(): MessageListenerAdapter =
        MessageListenerAdapter(ValuesMessageSubscriber(redisRepository, valuesRepository))

    @Bean
    fun redisContainer(): RedisMessageListenerContainer =
        RedisMessageListenerContainer().apply {
            setConnectionFactory(redisConnectionFactory)
            addMessageListener(messageListener(), topic())
        }

    @Bean
    fun redisPublisher(): MessagePublisher =
            RedisMessagePublisher(redisTemplate, topic())

    @Bean
    fun topic(): ChannelTopic = ChannelTopic("messageQueue")
}