package run.attraction.api.v1.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Occupation {
  PRODUCTION("생산"),
  MARKETING("마케팅"),
  SECURITY("보안"),
  IT("IT");

  private final String viewName;
}
