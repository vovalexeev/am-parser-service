package com.wine.to.up.am.parser.service.service;

import com.wine.to.up.am.parser.service.model.dto.WineDto;

import java.util.List;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
public interface AmWineService {

    /**
     * Получение всех объектов вина
     * @return все вина
     */
    List<WineDto> getAllAmWines();
}
