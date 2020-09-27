package com.wine.to.up.am.parser.service.messaging

import com.wine.to.up.commonlib.messaging.KafkaMessageHandler
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
@Slf4j
class SmopikTopicKafkaMessageHandler : KafkaMessageHandler<String?> {
    private val counter = AtomicInteger(0)
    override fun handle(message: String?) {
        counter.incrementAndGet()
        log.info("MMMMMMMMMmmmmmmmmessage received from test topic: test, number of messages: {}", counter.get())
    }
}