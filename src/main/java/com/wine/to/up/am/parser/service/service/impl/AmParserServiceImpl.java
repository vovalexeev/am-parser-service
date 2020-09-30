package com.wine.to.up.am.parser.service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.model.dto.Catalog;
import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class AmParserServiceImpl implements AmParserService {

    private AmClient amClient;

    private final Catalog catalog;

    public AmParserServiceImpl(@Qualifier("amClientStub") AmClient amClient) throws IOException {
        this.amClient = amClient;
        //File file = new File("/Users/happyline/am-parser-service/src/main/resources", "docum.html");
        Document document = this.amClient.getMainPage();
        catalog = new Catalog(document);
    }

    @Override
    public List<WineDto> parsePage(Document document) {
        Elements elements = document.getAllElements();
        String jsonStr = "";
        for (Element element : elements) {
            if (element.data().contains("window.products")) {
                Pattern pattern = Pattern.compile(".*window\\.products = (\\{.*});");
                Matcher matcher = pattern.matcher(element.data());
                if (matcher.find()) {
                    jsonStr = matcher.group(1);
                } else {
                    System.err.println("No match found!");
                }
                break;
            }
        }
        try {
            return Arrays.asList(new ObjectMapper().readValue(jsonStr, WineDto[].class));
        } catch (JsonProcessingException ex) {
            log.error("Can't parse document", ex);
            return new ArrayList<>();
        }
    }

    @Override
    public Long getCatalogPagesAmount(Document document) {
        return null;
    }
}
