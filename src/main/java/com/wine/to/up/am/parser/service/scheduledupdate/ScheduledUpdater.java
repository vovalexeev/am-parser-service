package com.wine.to.up.am.parser.service.scheduledupdate;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.parse.Parser;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ScheduledUpdater {

    private Parser parser;

    WineRepository wineRepository;

    public ScheduledUpdater(Parser parser) {
        this.parser = Objects.requireNonNull(parser, "Can't get Parser");
    }

    @Scheduled(cron = "0 0 1 * * MON")
    public void updateWineInfo() {
        List<Wine> newWineInfo = parser.getWines();
        List<Wine> oldWineInfo = wineRepository.findAll();
        for(Wine newWine: newWineInfo) {
            boolean wineIsInDB = false;
            for(Wine oldWine: oldWineInfo) {
                if(newWine.equals(oldWine)) {
                    wineIsInDB = true;
                    if(!newWine.equals(oldWine)) {
                        wineRepository.save(newWine);
                    }
                }
            }
            if(!wineIsInDB) {
                wineRepository.save(newWine);
            }
        }
    }

}
