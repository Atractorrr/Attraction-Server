package run.attraction.api.v1.user.validator.nickname;

import java.util.Arrays;
import java.util.function.Predicate;
import run.attraction.api.v1.user.validator.exception.InvalidNicknameException;

public enum NicknameValidator {
  SPECIAL_PATTERN(SpecialValidator::isSpecialPattern, SpecialValidator.getErrorMessage()),
  ENGLISH_PATTERN(EnglishValidator::isValidEnglishPattern,EnglishValidator.getErrorMessage()),
  ONLY_KOREAN_PATTERN(KoreanValidator::isValidKoreanPattern, KoreanValidator.getErrorMessage()),
  ONLY_NUMBER_PATTERN(NumberValidator::isOnlyNumberPattern, NumberValidator.getErrorMessage());

  private final Predicate<String> predicate;

  private final String errorMessage;

  NicknameValidator(Predicate<String> predicate,String errorMessage){
    this.predicate = predicate;
    this.errorMessage = errorMessage;
  }

  public static void checkNickname(String nickname){
    Arrays.stream(NicknameValidator.values())
        .filter(validator -> validator.predicate.test(nickname))
        .findFirst()
        .ifPresent(validator -> {
          throw new InvalidNicknameException(validator.errorMessage);
        });
  }
}