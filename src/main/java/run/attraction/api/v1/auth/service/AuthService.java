package run.attraction.api.v1.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.auth.provider.AuthProvider;
import run.attraction.api.v1.auth.service.dto.UserStateDto;
import run.attraction.api.v1.auth.service.dto.join.JoinRequestDto;
import run.attraction.api.v1.auth.service.helper.LoginHelper;
import run.attraction.api.v1.auth.service.helper.JoinHelper;
import run.attraction.api.v1.user.User;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

  private final AuthProvider authProvider;
  private final JoinHelper joinHelper;
  private final LoginHelper loginHelper;

  public UserStateDto login(String provider, final String code) {
    final User authUser = authProvider.getUserProfileByCode(provider, code);
    return loginHelper.getUserState(authUser);
  }

  public boolean checkNicknameDuplication(String nickname) {
    return joinHelper.checkNicknameDuplication(nickname);
  }

  public void join(JoinRequestDto joinRequestDto) {
    joinHelper.joinUser(
        joinRequestDto.getEmail(),
        joinRequestDto.getNickname(),
        joinRequestDto.getInterest(),
        joinRequestDto.getBirthDate(),
        joinRequestDto.getUserExpiration(),
        joinRequestDto.getOccupation(),
        joinRequestDto.isAdPolices()
    );
  }
}
