package run.attraction.api.v1.user.validator.nickname;

import java.util.regex.Pattern;

public class KoreanValidator {
  private static final Pattern ONLY_KOREAN_PATTERN = Pattern.compile("^[가-힣]$");
  private static final String ERROR_MESSAGE = "한글 닉네임은 2자 이상 20자 이하로 입력해주세요.";

  public static String getErrorMessage(){
    return ERROR_MESSAGE;
  }

  public static boolean isValidKoreanPattern(String nickname){
    return ONLY_KOREAN_PATTERN.matcher(nickname).find() &&
        (nickname.length() < 2 || nickname.length() > 20);
  }
}
