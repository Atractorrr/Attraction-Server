package run.attraction.api.v1.user;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserValidator {
  private static final Pattern SPECIAL_PATTERN = Pattern.compile("[^가-힣a-zA-Z0-9]");
  private static final Pattern URL_PATTERN = Pattern.compile(
      "^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?$");

  public boolean isSpecialPatternInNickname(String url) {
    return SPECIAL_PATTERN.matcher(url).find();
  }

  public boolean isUrlPattern(String url) {
    return URL_PATTERN.matcher(url).matches();
  }
}
