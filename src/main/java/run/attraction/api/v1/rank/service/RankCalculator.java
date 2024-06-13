package run.attraction.api.v1.rank.service;

import java.time.LocalDate;
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
    List<ExtensiveRank> ranks = extensiveRankRepository.findAllByCreatedAt(date);
    return ranks.isEmpty() ? calculateExtensiveRank(date) : ranks;
  }

  private List<ExtensiveRank> calculateExtensiveRank(LocalDate date) {
    final List<Object[]> top10Emails = readBoxRepository.findTop10ExtensiveUsers(date);

    final List<ExtensiveRank> ranks = top10Emails.stream()
        .map(obj -> ExtensiveRank.builder()
            .email((String) obj[0])
            .value(((Number) obj[1]).intValue())
            .createdAt(LocalDate.now())
            .build())
        .toList();

    extensiveRankRepository.saveAll(ranks);
    return ranks;
  }


}
