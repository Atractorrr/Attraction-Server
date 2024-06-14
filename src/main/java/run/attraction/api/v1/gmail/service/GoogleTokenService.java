package run.attraction.api.v1.gmail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.gmail.entity.GoogleRefreshToken;
import run.attraction.api.v1.gmail.dto.UserGmailToken;
import run.attraction.api.v1.gmail.repository.GoogleRefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class GoogleTokenService {
  private final GoogleRefreshTokenRepository tokenRepository;

  @Transactional
  public UserGmailToken findUserToken(String userEmail) {
    final GoogleRefreshToken googleRefreshToken = tokenRepository.findTokenByEmail(userEmail)
        .orElseThrow(IllegalAccessError::new);
    return new UserGmailToken(googleRefreshToken.getToken());
  }
}
