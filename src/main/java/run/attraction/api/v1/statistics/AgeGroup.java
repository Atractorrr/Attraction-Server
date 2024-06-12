package run.attraction.api.v1.statistics;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgeGroup {
  TEENAGER("10대", 10),
  TWENTIES("20대", 20),
  THIRTIES("30대", 30),
  FORTIES("40대", 40),
  FIFTIES("50대", 50),
  SIXTIES("60대", 60),
  SEVENTIES("70대", 70),
  EIGHTIES("80대", 80);

  private final String viewName;
  private final int ageGroup;

  public static AgeGroup calculateAge(LocalDate date) {
    int age = LocalDate.now().getYear() - date.getYear();

    final MonthDay birthDate = MonthDay.of(date.getMonth(), date.getDayOfMonth());
    final MonthDay now = MonthDay.now();

    if (birthDate.isBefore(now)) {
      age--;
    }

    int ageGroup = (age / 10) * 10;

    return Arrays.stream(AgeGroup.values())
        .filter(group -> group.getAgeGroup() == ageGroup)
        .findFirst()
        .orElseThrow();  //예외를 던져야 하나 고민중...
  }
}
