package bg.softuni.artgalleryshop.web.rest;

import bg.softuni.artgalleryshop.model.view.StatisticViewModel;
import bg.softuni.artgalleryshop.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class RestApiController {

    private final StatisticService statisticService;

    public RestApiController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/api/statistic")
//    @Transactional
    public ResponseEntity<List<StatisticViewModel>> getStatistic() {
        List<StatisticViewModel> statisticViewModels = this.statisticService.getStatistic();


        return new ResponseEntity<>(statisticViewModels, HttpStatus.OK);
    }
}






