package com.wine.to.up.am.parser.service.scheduledupdate;

import com.wine.to.up.am.parser.service.service.AmWineService;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Job для периодического обновления данных о винах
 * @author Филимонов Олег
 */

@Component
@Slf4j
public class ActualizeWineJob {

    @Autowired
    @Qualifier("amWineServiceImpl")
    private final AmWineService amWineService;

    @Autowired
    private WineRepository wineRepository;

    /**
     * Каждую неделю обновляет список вин
     */
    @Scheduled(cron = "${job.cron.actualize.wine}")
    public void updateWineInfo() {
        log.info("Starting wine list update...");
        List<Wine> newWineInfo = wineService.getAllAmWines();
        boolean listChanged = false;
        for (Wine newWine : newWineInfo) {
            Wine sameWine = null;
            List<Wine> oldWineInfo = wineRepository.findAllByBrand(newWine.getBrand());
            for (Wine oldWine : oldWineInfo) {
                if (newWine.getBrand().equals(oldWine.getBrand())) {
                    sameWine = oldWine;
                }
            }
            if (sameWine == null || !sameWine.equals(newWine)) {
                wineRepository.save(newWine);
                listChanged = true;
            }
        }
        if(listChanged) {
            log.info("Wine list update finished. The list has changed since the last update.");
        } else {
            log.info("Wine list update finished. The list has not changed since the last update.");
        }
    }

}
