package run.attraction.api.v1.auth.service.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.user.Occupation;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;
import run.attraction.api.v1.user.validator.nickname.NicknameValidator;

@Component
@RequiredArgsConstructor
@Slf4j
public class JoinHelper {

  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;

  @Transactional
  public boolean checkNicknameDuplication(String nickname) {
    NicknameValidator.checkNickname(nickname);
    return userDetailRepository.existsByNickname(nickname);
  }

  @Transactional
  public void joinUser(String email, String nickname, List<String> interest,
                       String stringBirthDate, int userExpiration, String occupation, boolean adPolices) {
    // adPolices 처리 어떻게 할건지 정해야함.
    final User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저 입니다."));
    saveUserDetail(nickname, interest, stringBirthDate, userExpiration, occupation, user);
  }

  private void saveUserDetail(String nickname,
                         List<String> interest,
                         String stringBirthDate,
                         int userExpiration,
                         String occupation,
                         User user) {
    UserDetail userDetail = UserDetail.builder()
        .email(user.getEmail())
        .nickname(nickname)
        .interests(interest)
        .birthDate(convertToDate(stringBirthDate))
        .userExpiration(calculateExpirationDate(user.getUpdateAt(), userExpiration))
        .occupation(Occupation.valueOf(occupation))
        .build();
    userDetailRepository.save(userDetail);
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
