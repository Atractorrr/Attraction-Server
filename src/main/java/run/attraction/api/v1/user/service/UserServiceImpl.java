package run.attraction.api.v1.user.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;

  @Override
  public boolean existsById(String email) {
    return userRepository.existsById(email);
  }

  public void updateUserExpiration(User user, LocalDate now){
    log.info("최신 접속 이력 update 시작");
    final Optional<UserDetail> optionalUserDetail = userDetailRepository.findById(user.getEmail());
    optionalUserDetail.ifPresent(userDetail -> updateUserDetailUpdateAt(user, userDetail, now));
    user.renewUpdateAt(now);
    userRepository.save(user);
    log.info("업데이트 완료");
  }

  private void updateUserDetailUpdateAt(User user, UserDetail userDetail,LocalDate now) {
    final long betweenDays = ChronoUnit.DAYS.between(user.getUpdateAt(), now);
    final LocalDate newExpiration = userDetail.getUserExpiration().plusDays(betweenDays);
    userDetail.updateUserExpiration(newExpiration);
    userDetailRepository.save(userDetail);
  }
}
