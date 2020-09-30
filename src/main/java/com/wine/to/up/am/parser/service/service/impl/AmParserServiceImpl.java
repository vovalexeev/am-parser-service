package com.wine.to.up.am.parser.service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
import org.springframework.util.StringUtils;

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

    private static final ObjectMapper mapper = new ObjectMapper();

    public AmParserServiceImpl(@Qualifier("amClientStub") AmClient amClient) {
        this.amClient = amClient;
        //File file = new File("/Users/happyline/am-parser-service/src/main/resources", "docum.html");
        Document document = this.amClient.getMainPage();
        catalog = new Catalog();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<WineDto> parsePage(Document document) {
        Elements elements = document.getElementsByTag("script");
        String jsonStr = "";
        for (Element element : elements) {
            if (element.data().contains("window.products")) {
                Pattern pattern = Pattern.compile(".*window\\.products\\s*=\\s*(\\[.*]);");
                Matcher matcher = pattern.matcher(element.data());
                if (matcher.find()) {
                    jsonStr = matcher.group(1);
                } else {
                    System.err.println("No match found!");
                }
                break;
            }
        }
        jsonStr = jsonStr.replaceAll("'", "\"");
        try {
            return mapper.readValue(jsonStr, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            log.error("Can't parse document", ex);
            return new ArrayList<>();
        }
    }

    @Override
    public Long getCatalogPagesAmount(Document document) {
        Elements elements = document.getElementsByTag("script");
        String totalCount = "";
        String perPageCount = "";
        for (Element element : elements) {
            if (element.data().contains("window.productsTotalCount")) {
                Pattern totalCountPattern = Pattern.compile(".*window\\.productsTotalCount\\s*=\\s*(\\d*);");
                Matcher totalCountMatcher = totalCountPattern.matcher(element.data());
                if (totalCountMatcher.find()) {
                    totalCount = totalCountMatcher.group(1);
                } else {
                    log.error("Can't find totalCount");
                }
                Pattern perPageCountPattern = Pattern.compile(".*window\\.productsPerServerPage\\s*=\\s*(\\d*);");
                Matcher perPageCountMatcher = perPageCountPattern.matcher(element.data());
                if (perPageCountMatcher.find()) {
                    perPageCount = totalCountMatcher.group(1);
                } else {
                    log.error("Can't find perPageCount");
                }
            }
        }
        if (StringUtils.hasText(totalCount) && StringUtils.hasText(perPageCount)) {
            Long longTotalCount = Long.getLong(totalCount);
            Long longPerPageCount = Long.getLong(perPageCount);
            return (longTotalCount % longPerPageCount == 0)
                    ? (longTotalCount / longPerPageCount)
                    : (longTotalCount / longPerPageCount) + 1;
        } else {
            return null;
        }
    }
}
