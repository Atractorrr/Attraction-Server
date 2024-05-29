package run.attraction.api.v1.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Occupation {
  PROFESSION("전문직"),
  TEACHING("교직"),
  MANAGER("관리직"),
  OFFICE("사무직"),
  SELFEMPLOY("자영업"),
  SELLING("판매직"),
  SERVICE("서비스직"),
  LABOR("생산/노무직"),
  TECH("기능직"),
  PRIMARY("농/축/광/수산업"),
  STUDENT("학생"),
  UNEMPLOY("무직"),
  RETIREE("무직자"),
  ETC("기타");

  private final String viewName;
}

