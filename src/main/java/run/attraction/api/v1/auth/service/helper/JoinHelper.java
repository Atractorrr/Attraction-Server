package run.attraction.api.v1.auth.service.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.user.Occupation;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserValidator;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class JoinHelper {

  private final UserRepository userRepository;

  @Transactional
  public boolean checkNicknameDuplication(String nickname) {
    return userRepository.existsByNickName(nickname);
  }

  @Transactional
  public void joinUser(UserValidator userValidator, String email, String nickName, List<String> interest,
                       String stringBirthDate, int userExpiration, String occupation, boolean adPolices) {
    // adPolices 처리 어떻게 할건지 정해야함.
    final User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저 입니다."));
    final LocalDate birthDate = convertToDate(stringBirthDate);
    final LocalDate userExpirationDate = calculateExpirationDate(user.getCreatedAt(), userExpiration);

    user.addExtraDetails(userValidator, nickName, interest, birthDate, userExpirationDate, Occupation.valueOf(occupation));
  }

  private static LocalDate calculateExpirationDate(LocalDate date, int expiration) {
    if (expiration == 0) {
      return date.plus(Period.ofMonths(120));
    }
    return date.plus(Period.ofMonths(expiration));
  }

  private static LocalDate convertToDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    return LocalDate.parse(dateString, formatter);
  }
}
