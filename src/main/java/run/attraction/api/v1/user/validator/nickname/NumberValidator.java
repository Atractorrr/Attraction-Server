package run.attraction.api.v1.user.validator.nickname;

import java.util.regex.Pattern;

public class NumberValidator {
  private static final Pattern ONLY_NUMBER_PATTERN = Pattern.compile("^\\d+$");
  private static final String ERROR_MESSAGE = "한글 혹은 영문이 포함되어 있어야 합나다.";

  public static String getErrorMessage(){
    return ERROR_MESSAGE;
  }

  public static boolean isOnlyNumberPattern(String nickname){
    return ONLY_NUMBER_PATTERN.matcher(nickname).find();
  }
}
