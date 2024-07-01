package run.attraction.api.v1.statistics.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.statistics.service.StatisticsService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Tag(name = "통계", description = "StatisticsController")
public class StatisticsController {
  private final StatisticsService statisticsService;

  @GetMapping("/occupation")
  public ResponseEntity<?> createOccupationStatistics(){
    statisticsService.createOccupationStatistics(LocalDate.now());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/ageGroup")
  public ResponseEntity<?> createAgeGroupStatistics(){
    statisticsService.createAgeGroupStatistics(LocalDate.now());
    return ResponseEntity.ok().build();
  }
}
