package run.attraction.api.v1.statistics.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.statistics.service.ageGroup.AgeGroupStatisticsCalculator;
import run.attraction.api.v1.statistics.service.occupation.OccupationStatisticsCalculator;

@Service
@RequiredArgsConstructor
public class StatisticsService {
  private final OccupationStatisticsCalculator occupationStatisticsCalculator;
  private final AgeGroupStatisticsCalculator ageGroupStatisticsCalculator;

  public void makeOccupationStatistics(LocalDate date){
    occupationStatisticsCalculator.getOccupationStatistics(date);
  }

  public void makeAgeGroupStatistics(LocalDate date){
    ageGroupStatisticsCalculator.getAgeGroupStatistics(date);
  }





}
