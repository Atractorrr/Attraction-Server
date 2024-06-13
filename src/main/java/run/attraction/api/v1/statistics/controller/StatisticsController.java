package run.attraction.api.v1.statistics.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.statistics.service.StatisticsService;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
  private final StatisticsService statisticsService;

  @GetMapping("/occupation")
  public ResponseEntity<?> makeOccupationStatistics(){
    statisticsService.makeOccupationStatistics(LocalDate.now());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/ageGroup")
  public ResponseEntity<?> makeAgeGroupStatistics(){
    statisticsService.makeAgeGroupStatistics(LocalDate.now());
    return ResponseEntity.ok().build();
  }
}
