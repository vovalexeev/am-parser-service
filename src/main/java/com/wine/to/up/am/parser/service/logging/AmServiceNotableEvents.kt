package com.wine.to.up.am.parser.service.logging;

import com.wine.to.up.commonlib.logging.NotableEvent;

public enum AmServiceNotableEvents implements NotableEvent {

    SOME_DEMO_EVENT("Something happened");

    private final String template;

    AmServiceNotableEvents(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    @Override
    public String getName() {
        return name();
    }


}
