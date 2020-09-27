package com.wine.to.up.am.parser.service.configuration

import com.wine.to.up.am.parser.service.components.AmServiceMetricsCollector
import com.wine.to.up.am.parser.service.messaging.TestTopicKafkaMessageHandler
import com.wine.to.up.am.parser.service.messaging.serialization.EventDeserializer
import com.wine.to.up.am.parser.service.messaging.serialization.EventSerializer
import com.wine.to.up.commonlib.messaging.BaseKafkaHandler
import com.wine.to.up.commonlib.messaging.KafkaMessageSender
import com.wine.to.up.demo.service.api.DemoServiceApiProperties
import com.wine.to.up.demo.service.api.message.KafkaMessageSentEventOuterClass.KafkaMessageSentEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import java.util.*

@Configuration
class KafkaConfiguration {
    /**
     * List of kafka servers
     */
    @Value("\${spring.kafka.bootstrap-server}")
    private val brokers: String? = null

    /**
     * Application consumer group id
     */
    @Value("\${spring.kafka.consumer.group-id}")
    private val applicationConsumerGroupId: String? = null

    /**
     * Creating general producer properties. Common for all the producers
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun producerProperties(): Properties {
        val properties = Properties()
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        return properties
    }

    /**
     * Creating general consumer properties. Common for all the consumers
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun consumerProperties(): Properties {
        val properties = Properties()
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, applicationConsumerGroupId)
        //in case of consumer crashing, new consumer will read all messages from committed offset
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.name.toLowerCase())
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        return properties
    }

    /**
     * Creates consumer based on general properties.
     *
     *
     * Uses custom deserializer as the messages within single topic should be the same type. And
     * the messages in different topics can have different types and require different deserializers
     *
     *
     * Binds the consumer of the topic with the object which is responsible for handling messages from
     * this topic
     *
     *
     * From now on all the messages consumed from given topic will be delegate
     * to [KafkaMessageHandler.handle] of the given handler
     *
     * @param consumerProperties is the general consumer properties. [.consumerProperties]
     * @param handler            which is responsible for handling messages from this topic
     */
    @Bean
    fun testTopicMessagesHandler(consumerProperties: Properties,
                                 demoServiceApiProperties: DemoServiceApiProperties,
                                 handler: TestTopicKafkaMessageHandler?): BaseKafkaHandler<KafkaMessageSentEvent> {
        // set appropriate deserializer for value
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EventDeserializer::class.java.name)

        // bind consumer with topic name and with appropriate handler
        return BaseKafkaHandler(demoServiceApiProperties.messageSentEventsTopicName, KafkaConsumer(consumerProperties), handler)
    }

    /**
     * Creates sender based on general properties. It helps to send single message to designated topic.
     *
     *
     * Uses custom serializer as the messages within single topic should be the same type. And
     * the messages in different topics can have different types and require different serializers
     *
     * @param producerProperties       is the general producer properties. [.producerProperties]
     * @param demoServiceApiProperties class containing the values of the given service's API properties (in this particular case topic name)
     * @param metricsCollector         class encapsulating the logic of the metrics collecting and publishing
     */
    @Bean
    fun testTopicKafkaMessageSender(producerProperties: Properties,
                                    demoServiceApiProperties: DemoServiceApiProperties,
                                    metricsCollector: AmServiceMetricsCollector?): KafkaMessageSender<KafkaMessageSentEvent> {
        // set appropriate serializer for value
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer::class.java.name)
        return KafkaMessageSender(KafkaProducer(producerProperties), demoServiceApiProperties.messageSentEventsTopicName, metricsCollector)
    }
}