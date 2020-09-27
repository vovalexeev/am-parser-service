package com.wine.to.up.am.parser.service.parse.service;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.parse.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ParseService {

    private Client client;

    public ParseService(Client client) {
        this.client = Objects.requireNonNull(client, "Can't get Client");
    }

    /**
     * Парсинг текущей страницы
     *
     * @return список винных позиций со страницы
     */
    private List<Wine> parsePage(int page) {
        try {
            List<Wine> productList = new ArrayList<>();
            String path = "/?page=" + page;
            Document document = client.getDocumentByPath(path);
            return null;
        } catch (Exception ex) {
            log.error("Can't parse page = {}", page, ex);
            return null;
        }
    }

    /**
     * Парсинг сайта для получения списка предложений о покупке вина
     *
     * @return полный список винных позиций c сайта
     */
    public List<Wine> parseDocument() {
        try {
            int page = 1;
            List<Wine> result = new ArrayList<>();
            List<Wine> wines;
            do {
                wines = parsePage(page);
                if (wines == null) {
                    break;
                }
                result.addAll(wines);
                page++;
            } while (page < 458);
            return result;
        } catch (Exception ex) {
            log.error("Can't parse web-page", ex);
            return null;
        }
    }
}
