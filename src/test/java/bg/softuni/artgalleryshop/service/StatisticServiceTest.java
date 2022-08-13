package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.StatisticEntity;
import bg.softuni.artgalleryshop.repository.StatisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {
    @Mock
    private StatisticRepository statisticRepository;
    private StatisticService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StatisticService(statisticRepository);
    }

    @Test
    public void getStatistic() {
        underTest.getStatistic();
        verify(statisticRepository).findAll();
    }

    @Test
    public void saveVisitationInDataBase(){
        StatisticEntity statistic = new StatisticEntity();
        statistic.setIpAddress("01.01.00.01.05");
        statistic.setLocalDateTime(LocalDateTime.now());
        underTest.saveVisitationInDataBase("01.01.00.01.05");
        ArgumentCaptor<StatisticEntity> statisticArgumentCaptor = ArgumentCaptor.forClass(StatisticEntity.class);
        verify(statisticRepository).saveAndFlush(statisticArgumentCaptor.capture());
    }

    @Test
    public void dropTable(){
        underTest.dropTable();
        verify(statisticRepository).deleteAll();
    }
}
