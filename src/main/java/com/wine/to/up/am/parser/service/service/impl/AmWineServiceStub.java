package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import com.wine.to.up.am.parser.service.service.AmWineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : SSyrova
 * @since : 30.09.2020, ср
 **/
@Component
@Slf4j
public class AmWineServiceStub implements AmWineService {

    @Autowired
    @Qualifier("amClientStub")
    private AmClient client;

    @Autowired
    @Qualifier("amParserServiceStub")
    private AmParserService amParserService;


    @Override
    public List<WineDto> getAllAmWines() {
        long pages = amParserService.getCatalogPagesAmount(client.getMainPage());
        List<WineDto> list = new ArrayList<>();
        long page = 1;
        while (page <= pages) {
            list.addAll(amParserService.parsePage(client.getPage(page)));
            page++;
        }
        return list;
    }

    @Override
    public void updateWines(List<WineDto> wineDtos) {
        log.info("updated {} wines", 1);
        log.info("created {} wines", 1);
        log.info("deleted {} wines", 1);
    }
}
