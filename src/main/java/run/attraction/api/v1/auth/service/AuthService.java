package run.attraction.api.v1.auth.service;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.auth.provider.AuthProvider;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.service.dto.join.JoinRequestDto;
import run.attraction.api.v1.auth.service.helper.AuthProviderAndTokenHelper;
import run.attraction.api.v1.auth.service.helper.JoinHelper;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserValidator;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

  private final AuthProvider authProvider;
  private final JoinHelper joinHelper;
  private final AuthProviderAndTokenHelper authProviderAndTokenHelper;
  private final UserValidator userValidator;

  public UserTokenDto login(String provider, final String code) {
    final User authUser = authProvider.getUserProfileByCode(provider, code);
    return authProviderAndTokenHelper.getTokenAndRegisterUserByAuthUser(authUser);
  }

  public boolean checkNicknameDuplication(String nickname) {
    return joinHelper.checkNicknameDuplication(nickname);
  }

  public void join(JoinRequestDto joinRequestDto) {
    joinHelper.joinUser(userValidator,
        joinRequestDto.getEmail(),
        joinRequestDto.getNickName(),
        joinRequestDto.getInterest(),
        joinRequestDto.getBirthDate(),
        joinRequestDto.getUserExpiration(),
        joinRequestDto.getOccupation(),
        joinRequestDto.isAdPolices());
  }

  public void logout(String accessToken) {
    authProviderAndTokenHelper.saveAccessTokenAndDeleteRefreshToken(accessToken);
  }

  public UserTokenDto reissueToken(String refreshToken, Date issuedAt) {
    return authProviderAndTokenHelper.reissueToken(refreshToken, issuedAt);
  }

}
