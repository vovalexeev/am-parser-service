package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public List<Document> getAllWinePages() {
        Long pagesAmount = amParserService.getCatalogPagesAmount(getMainPage());
        List<Document> list = new ArrayList<>();
        for (long i = 1; i <= pagesAmount; i++) {
            /*
            Получение всех страниц
             */
        }
        return list;
    }

    @Override
    public Document getMainPage() {
        return new Document("https://amwine.ru/catalog/vino/");
    }
}
