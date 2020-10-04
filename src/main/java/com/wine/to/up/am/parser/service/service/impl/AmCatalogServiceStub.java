package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.Catalog;
import com.wine.to.up.am.parser.service.service.AmCatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : SSyrova
 * @since : 04.10.2020, вс
 **/
@Service
@Slf4j
public class AmCatalogServiceStub implements AmCatalogService {

    @Override
    public Catalog getCatalog() {
        return new Catalog();
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        /*
            обновление каталога
         */
        log.info("updated {} colors", 1);
        log.info("created {} colors", 1);
        log.info("deleted {} colors", 1);

        log.info("updated {} brands", 1);
        log.info("created {} brands", 1);
        log.info("deleted {} brands", 1);

        log.info("updated {} countries", 1);
        log.info("created {} countries", 1);
        log.info("deleted {} countries", 1);

        log.info("updated {} grapes", 1);
        log.info("created {} grapes", 1);
        log.info("deleted {} grapes", 1);

        log.info("updated {} sugars", 1);
        log.info("created {} sugars", 1);
        log.info("deleted {} sugars", 1);
    }
}
