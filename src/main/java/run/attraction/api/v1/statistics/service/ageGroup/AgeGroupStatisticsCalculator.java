package run.attraction.api.v1.statistics.service.ageGroup;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.statistics.AgeGroup;
import run.attraction.api.v1.statistics.AgeGroupStatistics;
import run.attraction.api.v1.statistics.repository.AgeGroupStatisticsRepository;
import run.attraction.api.v1.statistics.repository.NewsletterEventRepository;

@Component
@RequiredArgsConstructor
public class AgeGroupStatisticsCalculator {
  private final NewsletterEventRepository newsletterEventRepository;
  private final AgeGroupStatisticsRepository ageGroupStatisticsRepository;

  public List<AgeGroupStatistics> getAgeGroupStatistics(LocalDate date){
    final List<AgeGroupStatistics> statistics = ageGroupStatisticsRepository.findAllByCreatedAt(date.minusDays(1));
    return statistics.isEmpty() ? calculateAgeGroupStatistics(date) : statistics;
  }

  public List<AgeGroupStatistics> calculateAgeGroupStatistics(LocalDate date) {
    List<Object[]> newsletterAgeGroupPairs = newsletterEventRepository.findMostPopularNewsletterAgeGroupPairs(date.atStartOfDay());

    List<AgeGroupStatistics> statistics = newsletterAgeGroupPairs.stream()
        .map(obj -> AgeGroupStatistics.builder()
            .ageGroup(AgeGroup.valueOf((String)obj[0]))
            .newsletterId((Long) obj[1])
            .createdAt(date.minusDays(1))
            .build()
    ).toList();

    ageGroupStatisticsRepository.saveAll(statistics);

    return statistics;
  }
}
