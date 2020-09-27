package com.wine.to.up.am.parser.service.controller

import com.wine.to.up.am.parser.service.domain.entity.Message
import com.wine.to.up.am.parser.service.repository.MessageRepository
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
@Slf4j
class TestController {
    @Autowired
    var messageRepository: MessageRepository? = null
    @GetMapping
    fun test(@RequestParam message: String?) {
        log.info("Test controller: {}", message)
        messageRepository!!.save(Message(message))
    }
}