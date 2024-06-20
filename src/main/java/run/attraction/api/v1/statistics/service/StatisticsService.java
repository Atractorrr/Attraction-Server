package run.attraction.api.v1.statistics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.statistics.service.ageGroup.AgeGroupStatisticsCalculator;
import run.attraction.api.v1.statistics.service.occupation.OccupationStatisticsCalculator;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StatisticsService {
  private final OccupationStatisticsCalculator occupationStatisticsCalculator;
  private final AgeGroupStatisticsCalculator ageGroupStatisticsCalculator;

  public void createOccupationStatistics(LocalDate date){
    occupationStatisticsCalculator.getOccupationStatistics(date);
  }

  public void createAgeGroupStatistics(LocalDate date){
    ageGroupStatisticsCalculator.getAgeGroupStatistics(date);
  }





}
