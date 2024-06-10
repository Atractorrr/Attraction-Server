package run.attraction.api.v1.user.validator.nickname;

import java.util.regex.Pattern;

public class EnglishValidator {
  private static final Pattern ENGLISH_PATTERN = Pattern.compile(".*[a-zA-Z].*");
  private static final String ERROR_MESSAGE = "영어 포함 닉네임은 4자 이상 20자 이하로 입력해주세요.";

  public static String getErrorMessage(){
    return ERROR_MESSAGE;
  }

  public static boolean isValidEnglishPattern(String nickname){
    return ENGLISH_PATTERN.matcher(nickname).find() &&
        (nickname.length() < 4 || nickname.length() > 20);
  }
}