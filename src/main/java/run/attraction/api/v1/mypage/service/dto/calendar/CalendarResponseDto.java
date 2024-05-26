package run.attraction.api.v1.mypage.service.dto.calendar;

import java.util.List;
import java.util.Objects;

public record CalendarResponseDto(
    List<CalendarDay> calendarData
) {
  public CalendarResponseDto {
    Objects.requireNonNull(calendarData);
  }
}
