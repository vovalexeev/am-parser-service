package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import com.wine.to.up.am.parser.service.service.AmWineService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
@Component
@Slf4j
public class AmWineServiceImpl implements AmWineService {

    @Autowired
    @Qualifier("jsoupAmClientImpl")
    private AmClient client;

    @Autowired
    @Qualifier("amParserServiceImpl")
    private AmParserService amParserService;

    @Autowired
    private WineRepository wineRepository;

    @Override
    public List<WineDto> getAllAmWines() {
        Long pages = amParserService.getCatalogPagesAmount();
        List<WineDto> wineDtos = new CopyOnWriteArrayList<>();
        AtomicLong page = new AtomicLong(1);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Callable<String> callableTask = () -> {
            log.info("Started client process...");
            while (page.longValue() <= pages) {
                Document document = client.getPage(page.getAndIncrement());
                if (document != null) {
                    List<WineDto> wines = amParserService.parsePage(document);
                    wineDtos.addAll(wines);
                }
            }
            log.info("Finished client process!");
            return "Task's execution";
        };
        List<Callable<String>> callableTasks = Collections.nCopies(20, callableTask);

        try {
            List<Future<String>> futures = executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return wineDtos;
    }

    public void saveWinesFromAm() {
        List<WineDto> wineDtos = getAllAmWines();
        for (WineDto wineDto : wineDtos) {
            Wine wine = new Wine();
            /*
             wineDto -> wine
             */
            wineRepository.save(wine);
        }
    }
}
