package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.Catalog;
import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.service.AmCatalogService;
import com.wine.to.up.am.parser.service.service.AmService;
import com.wine.to.up.am.parser.service.service.AmWineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author : SSyrova
 * @since : 04.10.2020, вс
 **/
@Service
public class AmServiceStub implements AmService {

    @Autowired
    @Qualifier("amCatalogServiceStub")
    private AmCatalogService amCatalogService;

    @Autowired
    @Qualifier("amWineServiceStub")
    private AmWineService amWineService;

    @Override
    public void updateDatabase() {
        Catalog catalog = amCatalogService.getCatalog();
        amCatalogService.updateCatalog(catalog);

        List<WineDto> wineDtos = amWineService.getAllAmWines();
        amWineService.updateWines(wineDtos);
    }

    @Override
    public List<WineDto> getWine(Long page, Long perPage) {
        return Collections.emptyList();
    }
}
