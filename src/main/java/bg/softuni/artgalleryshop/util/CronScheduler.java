package bg.softuni.artgalleryshop.util;

import bg.softuni.artgalleryshop.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);
    private final StatisticService statisticService;

    public CronScheduler(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    //once a day at 14.00 AM
    @Scheduled(cron = "0 0 14 1/1 * ?")
    public void dropVisitations() {
        this.statisticService.dropTable();
        LOGGER.info("The statistic DB was refreshed!");
    }
}

