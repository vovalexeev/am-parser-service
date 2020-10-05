package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
@Component
public class AmClientStub implements AmClient {

    @Autowired
    @Qualifier("amParserServiceStub")
    private AmParserService amParserService;

    @Override
    public Document getPage(Long page) {
        return new Document("https://amwine.ru/catalog/vino/");
    }

    @Override
    public Document getMainPage() {
        try {
            return Jsoup
                    .connect("https://amwine.ru/catalog/vino/")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
                    .referrer("https://www.google.com/")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
