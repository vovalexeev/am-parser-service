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
     *
     * @return все вина
     */
    List<WineDto> getAllAmWines();

    /**
     * Метод должен обновлять данные в базе данных или добавлять новые.
     * Сравнение сущностей производится по importId.
     * В лог должно выводится сколько сущностей обновилось, удалилось или создалось
     *
     * @param wineDtos объекты вина из винного магазина
     */
    void updateWines(List<WineDto> wineDtos);
}
