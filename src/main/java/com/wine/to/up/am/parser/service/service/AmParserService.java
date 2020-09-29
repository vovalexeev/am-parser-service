package com.wine.to.up.am.parser.service.service;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
public interface AmParserService {

    /**
     * Получение объектов вина из страницы каталога
     * @param document страница каталога
     * @return объект WineDto
     */
    List<WineDto> parsePage(Document document);

    /**
     * Получение количества страниц каталога
     * @param document главная страница каталога
     * @return количество страниц
     */
    Long getCatalogPagesAmount(Document document);
}
