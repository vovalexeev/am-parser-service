package com.wine.to.up.am.parser.service.scheduledupdate;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.parse.Parser;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Job для периодического обновления данных о винах
 * @author Филимонов Олег
 */

@Component
public class ActualizeWineJob {

    private final Parser parser;

    private WineRepository wineRepository;

    public ActualizeWineJob(Parser parser) {
        this.parser = Objects.requireNonNull(parser, "Can't get Parser");
    }

    /**
     * Каждую неделю обновляет список вин
     */
    @Scheduled(cron = "${job.cron.actualize.wine}")
    public void updateWineInfo() {
        List<Wine> newWineInfo = parser.getWines();
        List<Wine> oldWineInfo = wineRepository.findAll();
        for (Wine newWine : newWineInfo) {
            Wine sameWine = null;
            for (Wine oldWine : oldWineInfo) {
                if (newWine.getName().equals(oldWine.getName())) {
                    sameWine = oldWine;
                }
            }
            if (sameWine != null && !sameWine.equals(newWine)) {
                wineRepository.save(newWine);
            }
        }
    }

}
