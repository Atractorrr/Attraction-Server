package run.attraction.api.v1.statistics.service.occupation;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.statistics.OccupationStatistics;
import run.attraction.api.v1.statistics.repository.NewsletterEventRepository;
import run.attraction.api.v1.statistics.repository.OccupationStatisticsRepository;
import run.attraction.api.v1.user.Occupation;

@Component
@RequiredArgsConstructor
public class OccupationStatisticsCalculator {
  private final NewsletterEventRepository newsletterEventRepository;
  private final OccupationStatisticsRepository occupationStatisticsRepository;

  public List<OccupationStatistics> getOccupationStatistics(LocalDate date){
    final List<OccupationStatistics> statistics = occupationStatisticsRepository.findAllByCreatedAt(date.minusDays(1));
    return statistics.isEmpty() ? calculateOccupationStatistics(date) : statistics;
  }

  public List<OccupationStatistics> calculateOccupationStatistics(LocalDate date) {
    List<Object[]> newsletterOccupationPairs = newsletterEventRepository.findMostPopularNewsletterOccupationPairs(date.atStartOfDay());

    List<OccupationStatistics> statistics = newsletterOccupationPairs.stream()
        .map(obj -> OccupationStatistics.builder()
            .occupation(Occupation.valueOf((String)obj[0]))
            .newsletterId((Long)obj[1])
            .createdAt(date.minusDays(1))
            .build()
    ).toList();

    occupationStatisticsRepository.saveAll(statistics);

    return statistics;
  }

}
