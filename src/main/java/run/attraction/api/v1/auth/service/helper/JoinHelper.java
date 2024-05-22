package run.attraction.api.v1.auth.service.helper;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
  public void joinUser(UserValidator userValidator, Long userId, String nickName, List<String> interest,
                       LocalDate birthDate, int userExpiration, int jobCode, boolean adPolices) {
    // adPolices 처리 어떻게 할건지 정해야함.
    final User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    final LocalDate userExpirationDate = calculateExpirationDate(user.getCreatedAt(), userExpiration);
    user.addExtraDetails(userValidator, nickName, interest, birthDate, userExpirationDate, jobCode);
  }

  private static LocalDate calculateExpirationDate(LocalDate date, int expiration) {
    return date.plus(Period.ofMonths(expiration));
  }
}
