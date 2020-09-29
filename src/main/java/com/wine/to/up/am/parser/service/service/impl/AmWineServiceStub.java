package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import com.wine.to.up.am.parser.service.service.AmWineService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
@Component
public class AmWineServiceStub implements AmWineService {

    @Autowired
    private AmClient client;

    @Autowired
    private AmParserService amParserService;

    @Autowired
    private WineRepository wineRepository;

    @Override
    public List<WineDto> getAllAmWines() {
        List<Document> catalogPages = client.getAllWinePages();
        List<WineDto> wineDtos = new ArrayList<>();
        for (Document document : catalogPages) {
            wineDtos.addAll(amParserService.parsePage(document));
        }
        return wineDtos;
    }

    public void saveWinesFromAm(){
        List<WineDto> wineDtos = getAllAmWines();
        for (WineDto wineDto: wineDtos) {
            Wine wine = new Wine();
            /*
             wineDto -> wine
             */
            wineRepository.save(wine);
        }
    }
}
