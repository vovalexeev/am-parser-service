package com.wine.to.up.am.parser.service.controller

import com.google.protobuf.ByteString
import com.wine.to.up.commonlib.messaging.KafkaMessageSender
import com.wine.to.up.demo.service.api.dto.DemoServiceMessage
import com.wine.to.up.demo.service.api.message.KafkaMessageHeaderOuterClass.KafkaMessageHeader
import com.wine.to.up.demo.service.api.message.KafkaMessageSentEventOuterClass.KafkaMessageSentEvent
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * REST controller of the service
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
@Validated
@Slf4j
class KafkaController @Autowired constructor(
        /**
         * Service for sending messages
         */
        private val kafkaSendMessageService: KafkaMessageSender<KafkaMessageSentEvent>) {
    private val executorService = Executors.newFixedThreadPool(3)

    /**
     * Sends messages into the topic "test".
     * In fact now this service listen to that topic too. That means that it causes sending and reading messages
     */
    @PostMapping(value = ["/send"])
    fun sendMessage(@RequestBody message: String?) {
        sendMessageWithHeaders(DemoServiceMessage(emptyMap(), message))
    }

    /**
     * See [.sendMessage]
     * Sends message with headers
     */
    @PostMapping(value = ["/send/headers"])
    fun sendMessageWithHeaders(@RequestBody message: DemoServiceMessage) {
        val counter = AtomicInteger(0)
        val event = KafkaMessageSentEvent.newBuilder()
                .addAllHeaders(message.headers.entries.stream()
                        .map { entry: Map.Entry<String?, ByteArray?> ->
                            KafkaMessageHeader.newBuilder()
                                    .setKey(entry.key)
                                    .setValue(ByteString.copyFrom(entry.value))
                                    .build()
                        }
                        .collect(Collectors.toList()))
                .setMessage(message.message)
                .build()
        val sent = Stream.iterate(1, { v: Int -> v + 1 })
                .limit(3)
                .map { n: Int? ->
                    executorService.submit<Int> {
                        val numOfMessages = 10
                        for (j in 0 until numOfMessages) {
                            kafkaSendMessageService.sendMessage(event)
                            counter.incrementAndGet()
                        }
                        numOfMessages
                    }
                }
                .map { f: Future<Int> ->
                    try {
                        return@map f.get()
                    } catch (e: InterruptedException) {
                        log.error("Error while sending in Kafka ", e)
                        return@map 0
                    } catch (e: ExecutionException) {
                        log.error("Error while sending in Kafka ", e)
                        return@map 0
                    }
                }
                .mapToInt { obj: Int -> obj }
                .sum()
        log.info("Sent: $sent")
    }
}