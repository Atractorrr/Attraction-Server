package run.attraction.api.v1.mypage.service.calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.archive.repository.ReadBoxRepository;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageCalendarServiceImpl implements MypageCalendarService {
  private final ReadBoxRepository readBoxRepository;

  private static final LocalDate FIRST_DAY = LocalDate.of(LocalDate.now().getYear(), 1, 1);
  private static final LocalDate LAST_DAY = LocalDate.of(LocalDate.now().getYear(), 12, 31);

  public Map<LocalDate, Integer> getUserCalendar(String email) {
    final List<ReadBox> completedArticles = readBoxRepository.findCompletedReadBoxByEmail(email);
    Map<LocalDate, Integer> countMap = completedArticles.stream()
        .collect(Collectors.groupingBy(ReadBox::getReadDate, Collectors.summingInt(e -> 1)));
    getFirstDayAndLastDay(countMap);
    return countMap;
  }

  //필수 날짜 데이터 1월 1일, 12월 31일 확인 후 넣기
  private static void getFirstDayAndLastDay(Map<LocalDate, Integer> countMap) {
    checkAndPutFirstDay(countMap);
    checkAndPutLastDay(countMap);
  }

  private static void checkAndPutFirstDay(Map<LocalDate, Integer> countMap) {
    if (!countMap.containsKey(FIRST_DAY)) {
      countMap.put(FIRST_DAY, 0);
    }
  }

  private static void checkAndPutLastDay(Map<LocalDate, Integer> countMap) {
    if (!countMap.containsKey(LAST_DAY)) {
      countMap.put(LAST_DAY, 0);
    }
  }

}
