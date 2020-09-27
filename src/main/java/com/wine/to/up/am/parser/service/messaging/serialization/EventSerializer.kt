package com.wine.to.up.am.parser.service.messaging.serialization

import com.wine.to.up.demo.service.api.message.KafkaMessageSentEventOuterClass.KafkaMessageSentEvent
import org.apache.kafka.common.serialization.Serializer

/**
 * Serializer for [KafkaMessageSentEvent]
 */
class EventSerializer : Serializer<KafkaMessageSentEvent> {
    /**
     * {@inheritDoc}
     */
    override fun serialize(topic: String, data: KafkaMessageSentEvent): ByteArray {
        return data.toByteArray()
    }
}