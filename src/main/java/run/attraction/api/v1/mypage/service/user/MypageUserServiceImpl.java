package run.attraction.api.v1.mypage.service.user;

import java.time.Period;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageUserServiceImpl implements MypageUserService {
  private final UserRepository userRepository;

  private static final int EXPIRATION_FOREVER = 120;
  public UserDetailDto getUserDetails(String email) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    final UserDetailDto userDetailDto = getUserDetailDto(user);
    return userDetailDto;
  }

  private static UserDetailDto getUserDetailDto(User user) {
    return new UserDetailDto(
        user.getEmail(),
        user.getNickName(),
        user.getProfileImg(),
        user.getBackgroundImg(),
        user.getInterests(),
        user.getOccupation().name(),
        calculateExpiration(user));
  }

  private static int calculateExpiration(User user){
    final Period between = Period.between(user.getUpdateAt(), user.getUserExpiration());
    int betweenMonths = between.getYears() * 12 + between.getMonths();
    return betweenMonths == EXPIRATION_FOREVER ? 0 : betweenMonths;
  }

  @Transactional
  public void updateProfileImg(String email, String profileImg) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateProfileImg(profileImg);
  }

  @Transactional
  public void updateBackgroundImg(String email, String backgroundImg) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateBackgroundImg(backgroundImg);
  }

  @Transactional
  public void updateUserDetail(UpdateUserDetailDto updateUserDetailDto) {
    User user = userRepository.findById(updateUserDetailDto.getEmail())
        .orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    updateUserDetailDto.getNickName().ifPresent(user::updateNickName);
    updateUserDetailDto.getUserExpiration().ifPresent(user::updateExpiration);
    updateUserDetailDto.getInterest().ifPresent(user::updateInterest);
    updateUserDetailDto.getOccupation().ifPresent(user::updateOccupation);
  }

  @Transactional
  public boolean checkNickNameDuplication(String nickName) {
    return userRepository.existsByNickName(nickName);
  }
}
