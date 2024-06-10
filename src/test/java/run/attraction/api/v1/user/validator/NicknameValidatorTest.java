package run.attraction.api.v1.user.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import run.attraction.api.v1.user.validator.exception.InvalidNicknameException;
import run.attraction.api.v1.user.validator.nickname.NicknameValidator;

public class NicknameValidatorTest {
  @Test
  @DisplayName("닉네임에 특수문자가 포함된 경우 테스트")
  public void specialPatternTest() {
    //given
    String nickname = "닉네임!!";

    //when
    InvalidNicknameException exception = assertThrows(InvalidNicknameException.class,
        () -> NicknameValidator.checkNickname(nickname));
    
    //then
    String message = exception.getMessage();
    assertEquals("특수문자가 포함되어 있습니다.",message);
  }

  @Test
  @DisplayName("숫자 닉네임인 경우 테스트")
  public void onlyNumberPatternTest() {
    //given
    String nickname = "1234";

    //when
    InvalidNicknameException exception = assertThrows(InvalidNicknameException.class,
        () -> NicknameValidator.checkNickname(nickname));

    //then
    String message = exception.getMessage();
    assertEquals("한글 혹은 영문이 포함되어 있어야 합나다.",message);
  }

  @Test
  @DisplayName("한글 닉네임 글자 수 예외 테스트")
  public void onlyKoreanPatternTest() {
    //given
    String nickname = "김";

    //when
    InvalidNicknameException exception = assertThrows(InvalidNicknameException.class,
        () -> NicknameValidator.checkNickname(nickname));

    //then
    String message = exception.getMessage();
    assertEquals("한글 닉네임은 2자 이상 20자 이하로 입력해주세요.",message);
  }

  @Test
  @DisplayName("영문이 포함된 닉네임 글자 수 테스트")
  public void nicknameValidatorTest() {
    //given
    String nickname = "abc";

    //when
    InvalidNicknameException exception = assertThrows(InvalidNicknameException.class,
        () -> NicknameValidator.checkNickname(nickname));

    //then
    String message = exception.getMessage();
    assertEquals("영어 포함 닉네임은 4자 이상 20자 이하로 입력해주세요.",message);
  }
}
