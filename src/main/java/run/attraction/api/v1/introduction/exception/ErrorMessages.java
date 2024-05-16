package run.attraction.api.v1.introduction.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessages {
  INVALID_TYPE("올바른 타입으로 요청해주세요"),
  SEVER_ERROR("서버 에러"),
  NOT_EXIST_NEWSLETTER("존재하지 않는 뉴스레터입니다");

  private final String viewName;
}
