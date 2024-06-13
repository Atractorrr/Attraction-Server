package run.attraction.api.v1.rank.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.repository.ReadBoxRepository;
import run.attraction.api.v1.rank.ExtensiveRank;
import run.attraction.api.v1.rank.repository.ExtensiveRankRepository;

@Component
@RequiredArgsConstructor
public class RankCalculator {
  private final ReadBoxRepository readBoxRepository;
  private final ExtensiveRankRepository extensiveRankRepository;

  public List<ExtensiveRank> getExtensiveRank(LocalDate date) {
    List<ExtensiveRank> ranks = extensiveRankRepository.findAllByCreatedAt(date.minusDays(1));
    return ranks.isEmpty() ? calculateExtensiveRank(date) : ranks;
  }

  private List<ExtensiveRank> calculateExtensiveRank(LocalDate date) {
    LocalDate[] dateRange = getDateRange(date);

    LocalDate startDate = dateRange[0];
    LocalDate endDate = dateRange[1];

    System.out.println(startDate);
    System.out.println(endDate);

    final List<Object[]> top10Emails = readBoxRepository.findTop10ExtensiveUsers(startDate,endDate,date);

    final List<ExtensiveRank> ranks = top10Emails.stream()
        .map(obj -> ExtensiveRank.builder()
            .email((String) obj[0])
            .value(((Number) obj[1]).intValue())
            .createdAt(date.minusDays(1))
            .build())
        .toList();

    extensiveRankRepository.saveAll(ranks);
    return ranks;
  }

  private LocalDate[] getDateRange(LocalDate date) {
    LocalDate startDate;
    LocalDate endDate;

    if (date.getDayOfMonth() == 1) {
      LocalDate lastMonth = date.minusMonths(1);
      startDate = lastMonth.withDayOfMonth(1);
      endDate = lastMonth.with(TemporalAdjusters.lastDayOfMonth());
    } else {
      startDate = date.withDayOfMonth(1);
      endDate = date.with(TemporalAdjusters.lastDayOfMonth());
    }

    return new LocalDate[]{startDate, endDate};
  }


}
