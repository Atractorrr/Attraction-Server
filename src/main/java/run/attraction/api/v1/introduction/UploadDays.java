package run.attraction.api.v1.introduction;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UploadDays {
  MON("월"),
  TUE("화"),
  WED("수"),
  THU("목"),
  FRI("금"),
  SAT("토"),
  SUN("일");

  private final String viewName;

  public List<UploadDays> weekDay() {
    return List.of(UploadDays.MON, UploadDays.TUE, UploadDays.WED, UploadDays.THU, UploadDays.FRI);
  }

  public List<UploadDays> weekEnd() {
    return List.of(UploadDays.SAT, UploadDays.SUN);
  }
}
