package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
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
public class AmParserServiceStub implements AmParserService {

    private AmClient amClient;

    private final Catalog catalog;

    public AmParserServiceStub(@Qualifier("amClientStub") AmClient amClient) {
        this.amClient = amClient;
        Document document = this.amClient.getMainPage();
        catalog = getCatalog(document);
    }

    @Override
    public List<WineDto> parsePage(Document document) {
        List<AmWine> wines = new ArrayList<>();
        List<WineDto> wineDtos = new ArrayList<>() {{ add(new WineDto()); add(new WineDto()); add(new WineDto()); add(new WineDto()); }};
        /*
            Этот кусок кода переводит с помощью каталога информацию
         */
        return wineDtos;
    }

    @Override
    public Long getCatalogPagesAmount() {
        return 457L;
    }

    private Catalog getCatalog(Document document) {
        return new Catalog();
    }

    private static class Catalog {

    }

    private static class AmWine {

    }
}
