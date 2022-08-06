package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.StatisticEntity;
import bg.softuni.artgalleryshop.model.view.StatisticViewModel;
import bg.softuni.artgalleryshop.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public StatisticEntity saveVisitationInDataBase(String ip) {
        StatisticEntity statistic = new StatisticEntity();
        statistic.setIpAddress(ip);
        statistic.setLocalDateTime(LocalDateTime.now());
        return this.statisticRepository.saveAndFlush(statistic);
    }

    public List<StatisticViewModel> getStatistic() {
        List<StatisticEntity> statistics = this.statisticRepository.findAll();
        return statistics
                .stream()
                .map(s ->
                        new StatisticViewModel(
                                s.getLocalDateTime(),
                                s.getIpAddress(),
                                s.getId()))
                .collect(Collectors.toList());
    }
    public void dropTable() {
        this.statisticRepository.deleteAll();
    }
}
