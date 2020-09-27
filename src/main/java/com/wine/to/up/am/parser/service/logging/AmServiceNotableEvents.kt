package com.wine.to.up.am.parser.service.logging

import com.wine.to.up.commonlib.logging.NotableEvent

enum class AmServiceNotableEvents(private val template: String) : NotableEvent {
    SOME_DEMO_EVENT("Something happened");

    override fun getTemplate(): String {
        return template
    }

    override fun getName(): String {
        return name
    }
}