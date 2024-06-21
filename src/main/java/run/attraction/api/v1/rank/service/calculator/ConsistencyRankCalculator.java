package run.attraction.api.v1.rank.service.calculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.rank.ConsistencyRank;
import run.attraction.api.v1.rank.repository.ConsistencyRankRepository;
import run.attraction.api.v1.rank.repository.ReadBoxEventRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsistencyRankCalculator {
  private final ConsistencyRankRepository consistencyRankRepository;
  private final ReadBoxEventRepository readBoxEventRepository;

  public List<ConsistencyRank> getConsistencyRank(LocalDate date) {
    List<ConsistencyRank> ranks = consistencyRankRepository.findAllByCreatedAt(date.minusDays(1));
    return ranks.isEmpty() ? calculateConsistencyRank(date) : ranks;
  }

  private List<ConsistencyRank> calculateConsistencyRank(LocalDate date) {
    log.info("저장된 데이터가 존재하지 않아, 새로 계산합니다.");
    LocalDateTime[] dateRange = getDateRange(date);

    LocalDateTime startDate = dateRange[0];
    LocalDateTime endDate = dateRange[1];
    log.info("조회 범위");
    log.info("startDate: {}, endDate: {}", startDate, endDate);

    final List<Object[]> top10Emails = readBoxEventRepository.findTop10ConsistencyUserEmail(startDate, endDate);
    log.info("Top 10 조회 완료");

    final List<ConsistencyRank> ranks = top10Emails.stream()
        .map(obj -> createConsistencyRank(obj,date))
        .toList();

    consistencyRankRepository.saveAll(ranks);
    log.info("Top 10 저장 완료");
    return ranks;
}

  private LocalDateTime[] getDateRange(LocalDate date) {
    LocalDateTime startDate;
    LocalDateTime endDate;

    if (date.getDayOfMonth() == 1) {
      LocalDate lastMonth = date.minusMonths(1);
      startDate = getStartOfMonth(lastMonth.getYear(), lastMonth.getMonthValue());
      endDate = getEndOfMonth(lastMonth.getYear(), lastMonth.getMonthValue());
    } else {
      startDate = getStartOfMonth(date.getYear(), date.getMonthValue());
      endDate = LocalDateTime.now();
    }

    return new LocalDateTime[]{startDate, endDate};
  }

  private LocalDateTime getStartOfMonth(int year, int month) {
    LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
    return firstDayOfMonth.atStartOfDay();
  }

  private LocalDateTime getEndOfMonth(int year, int month) {
    YearMonth yearMonth = YearMonth.of(year, month);
    LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
    return lastDayOfMonth.atTime(23, 59, 59, 999999999);
  }

  private ConsistencyRank createConsistencyRank(Object[] obj, LocalDate date){
    String email = (String) obj[0];
    int baseValue = ((Number) obj[1]).intValue();
    LocalDate modifiedAt = ((Timestamp) obj[2]).toLocalDateTime().toLocalDate();

    int value = modifiedAt.equals(date) ? baseValue - 1 : baseValue;

    return ConsistencyRank.builder()
        .email(email)
        .value(value)
        .createdAt(date.minusDays(1))
        .build();
  }
}


