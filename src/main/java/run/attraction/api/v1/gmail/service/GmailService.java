package run.attraction.api.v1.gmail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.gmail.entity.GoogleRefreshToken;
import run.attraction.api.v1.gmail.dto.UserGmailDto;
import run.attraction.api.v1.gmail.event.UserSubscribedEvent;
import run.attraction.api.v1.gmail.repository.GoogleRefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class GmailService {
  private final ApplicationEventPublisher eventPublisher;
  private final GoogleRefreshTokenRepository tokenRepository;

  @Transactional
  public void applyLabelAndFilter(UserGmailDto userGmailDto) {
    final String userToken = findUserToken(userGmailDto.userEmail());
    eventPublisher.publishEvent(new UserSubscribedEvent(
        userGmailDto.userEmail(),
        userGmailDto.newsletterEmail(),
        userToken
    ));
  }

  private String findUserToken(String userEmail) {
    final GoogleRefreshToken googleRefreshToken = tokenRepository.findTokenByEmail(userEmail)
        .orElseThrow(() -> new IllegalArgumentException(userEmail + "에 해당하는 토큰이 없습니다."));
    return googleRefreshToken.getToken();
  }
}
