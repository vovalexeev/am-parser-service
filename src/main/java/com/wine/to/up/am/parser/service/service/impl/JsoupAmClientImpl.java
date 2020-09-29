package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

/**
 * @author : skorz
 * @since : 29.09.2020, вт
 **/
@Component
@Slf4j
public class JsoupAmClientImpl implements AmClient {

    @Value(value = "${am.site.base-url}")
    private String baseUrl;

    @Value(value = "${am.site.user-agent}")
    private String userAgent;

    @Value(value = "${am.site.refferer}")
    private String refferer;

    @Autowired
    private AmParserService amParserService;

    @Override
    public List<Document> getAllWinePages() {
//        Long pagesAmount = amParserService.getCatalogPagesAmount()
        return null;
    }

    @Override
    public Document getMainPage() {
        try {
            return Jsoup
                    .connect(baseUrl)
                    .userAgent(userAgent)
                    .referrer(refferer)
                    .get();
        } catch (IOException e) {
            log.error("Cannot get document by '{}' url", baseUrl);
            return null;
        }
    }
}
