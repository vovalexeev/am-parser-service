package com.wine.to.up.am.parser.service.service;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
public interface AmClient {

    /**
     * Получение всех винных страниц
     * @return винные страницы
     */
    List<Document> getAllWinePages();

    /**
     * Получение главной страницы каталога.
     * Предполагается использовать этот метод для получения информации о том, сколько
     * всего винных страниц
     * @return главная страница каталога
     */
    Document getMainPage();
}

