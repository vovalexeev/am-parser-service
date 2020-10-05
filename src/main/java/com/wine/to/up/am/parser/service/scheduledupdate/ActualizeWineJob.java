package com.wine.to.up.am.parser.service.scheduledupdate;

import com.wine.to.up.am.parser.service.service.AmService;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Job для периодического обновления данных о винах
 * @author Филимонов Олег
 */

@Component
@Slf4j
public class ActualizeWineJob {

    @Autowired
    private AmService amService;

    @Autowired
    private WineRepository wineRepository;

    /**
     * Каждую неделю обновляет список вин
     */
    @Scheduled(cron = "${job.cron.actualize.wine}")
    public void runJob() {
        long startDate = new Date().getTime();
        log.info("start ActualizeWineJob run job method at " + startDate);
        amService.updateDatabase();
        log.info("end ActualizeWineJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

}
