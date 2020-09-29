package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.service.AmClient;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : skorz
 * @since : 29.09.2020, вт
 **/
@Component
public class JsoupAmClientImpl implements AmClient {
    @Override
    public List<Document> getAllWinePages() {
        return null;
    }

    @Override
    public Document getMainPage() {
        return null;
    }
}
