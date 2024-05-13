package run.attraction.api.v1.auth.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.auth.provider.AuthProvider;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.service.dto.login.LoginRequestDto;
import run.attraction.api.v1.auth.service.helper.AuthProviderAndTokenHelper;
import run.attraction.api.v1.user.User;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
  private final AuthProvider authProvider;
  private final AuthProviderAndTokenHelper authProviderAndTokenHelper;

  public Optional<UserTokenDto> login(String provider, String code) {
    final User authUser = authProvider.getUserProfileByCode(provider,code);
    return authProviderAndTokenHelper.getTokenAndRegisterUserByAuthUser(authUser);
  }


}
