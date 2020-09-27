package com.wine.to.up.am.parser.service.messaging.serialization

import com.google.protobuf.InvalidProtocolBufferException
import com.wine.to.up.demo.service.api.message.KafkaMessageSentEventOuterClass.KafkaMessageSentEvent
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.apache.kafka.common.serialization.Deserializer

/**
 * Deserializer for [KafkaMessageSentEvent]
 */
@Slf4j
class EventDeserializer : Deserializer<KafkaMessageSentEvent?> {
    /**
     * {@inheritDoc}
     */
    override fun deserialize(topic: String, bytes: ByteArray): KafkaMessageSentEvent? {
        return try {
            KafkaMessageSentEvent.parseFrom(bytes)
        } catch (e: InvalidProtocolBufferException) {
            log.error("Failed to deserialize message from topic: {}. {}", topic, e)
            null
        }
    }
}