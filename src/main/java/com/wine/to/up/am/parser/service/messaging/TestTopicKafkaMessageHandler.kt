package com.wine.to.up.am.parser.service.messaging

import com.wine.to.up.am.parser.service.domain.entity.Message
import com.wine.to.up.am.parser.service.repository.MessageRepository
import com.wine.to.up.commonlib.messaging.KafkaMessageHandler
import com.wine.to.up.demo.service.api.message.KafkaMessageSentEventOuterClass.KafkaMessageSentEvent
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
@Slf4j
class TestTopicKafkaMessageHandler @Autowired constructor(
        private val messageRepository: MessageRepository) : KafkaMessageHandler<KafkaMessageSentEvent> {
    private val counter = AtomicInteger(0)

    override fun handle(message: KafkaMessageSentEvent) {
        counter.incrementAndGet()
        log.info("Message received message of type {}, number of messages: {}", message.javaClass.simpleName, counter.get())
        messageRepository.save(Message(message.message))
    }
}