package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.Catalog;
import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class AmParserServiceImpl implements AmParserService {

    private AmClient amClient;

    private final Catalog catalog;

    public AmParserServiceImpl(@Qualifier("amClientStub")AmClient amClient) {
        this.amClient = amClient;
        Document document = this.amClient.getMainPage();
        catalog = new Catalog();
    }

    @Override
    public List<WineDto> parsePage(Document document) {
        return null;
    }

    @Override
    public Long getCatalogPagesAmount(Document document) {
        return null;
    }
}
