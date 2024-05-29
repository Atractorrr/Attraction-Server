package run.attraction.api.v1.introduction.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessages {
  INVALID_TYPE("요청 파라미터 타입이 잘못되었습니다"),
  SEVER_ERROR("서버 에러"),
  NOT_EXIST_DATA("존재 하지 않는 데이터입니다"),
  NOT_EXIST_NEWSLETTER("존재하지 않는 뉴스레터입니다"),
  REQUEST_PARAMETER_MISSING("필수 요청 파라미터가 누락되었습니다: "),
  NOT_EXIST_NEWSLETTER_BY_CATEGORY("카테고리에 맞는 뉴스레터가 존재하지않습니다");

  private final String viewName;
}
