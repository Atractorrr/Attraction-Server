package run.attraction.api.v1.mypage.service.dto.calendar;

import java.time.LocalDate;
import java.util.Objects;

public record CalendarDay(
    LocalDate date,
    Integer count
) {
  public CalendarDay {
    Objects.requireNonNull(date);
    Objects.requireNonNull(count);
  }
}
