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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

  private final AuthProvider authProvider;
  private final JoinHelper joinHelper;
  private final AuthProviderAndTokenHelper authProviderAndTokenHelper;

  public UserTokenDto login(String provider, final String code) {
    log.info("로그인 진입");
    final User authUser = authProvider.getUserProfileByCode(provider, code);
    log.info("회원 정보 반환 및 Google RefreshToken 처리 완료");
    log.info("구글 API 회원 조회 결과 email = {}",authUser.getEmail());
    log.info("구글 API 회원 조회 결과 프로필이미지 = {}",authUser.getProfileImg());
    return authProviderAndTokenHelper.getTokenAndRegisterUserByAuthUser(authUser);
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

  public void logout(String accessToken) {
    authProviderAndTokenHelper.saveAccessTokenAndDeleteRefreshToken(accessToken);
  }

  public UserTokenDto reissueToken(String refreshToken, Date issuedAt) {
    return authProviderAndTokenHelper.reissueToken(refreshToken, issuedAt);
  }

}
