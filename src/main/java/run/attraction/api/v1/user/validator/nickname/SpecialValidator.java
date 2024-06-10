package run.attraction.api.v1.user.validator.nickname;

import java.util.regex.Pattern;

public class SpecialValidator {

  private static final Pattern SPECIAL_PATTERN = Pattern.compile("[^a-zA-Z0-9가-힣_]");
  private static final String ERROR_MESSAGE = "특수문자가 포함되어 있습니다.";

  public static String getErrorMessage(){
    return ERROR_MESSAGE;
  }

  public static boolean isSpecialPattern(String nickname){
    return SPECIAL_PATTERN.matcher(nickname).find();
  }

}