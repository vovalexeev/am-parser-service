package com.wine.to.up.am.parser.service.controller

import com.wine.to.up.am.parser.service.repository.MessageRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller of the service
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
@Validated
@Slf4j
class MessageController(
        val messageRepository: MessageRepository) {

    @GetMapping
    fun getSentMessages(): List<String?>? = messageRepository.findDistinctContent()
}