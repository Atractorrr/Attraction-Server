package run.attraction.api.v1.user.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public boolean existsById(String email) {
    return userRepository.existsById(email);
  }

  public void updateUserExpiration(User user, LocalDate now){
    log.info("최신 접속 이력 update 시작");
    final long betweenDays = ChronoUnit.DAYS.between(user.getUpdateAt(), now);
    user.renewUpdateAtAndExpirationAt(now,user.getUserExpiration().plusDays(betweenDays));
    userRepository.save(user);
    log.info("업데이트 완료");
  }
}
