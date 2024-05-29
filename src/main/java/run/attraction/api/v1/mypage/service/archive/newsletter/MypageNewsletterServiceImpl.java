package run.attraction.api.v1.mypage.service.archive.newsletter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.Subscribe;
import run.attraction.api.v1.archive.repository.SubscribeRepository;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

@Component
@RequiredArgsConstructor
public class MypageNewsletterServiceImpl implements MypageNewsletterService {
  private final SubscribeRepository subscribeRepository;

  @Override
  public List<MypageNewsletterDetail> getSubscribesByEmail(String email) {
    final Optional<Subscribe> subscribe = subscribeRepository.findByUserEmail(email);
    return subscribe.map(value -> value.getNewsletters().stream()
            .map(newsletter -> new MypageNewsletterDetail(
                newsletter.getId(),
                newsletter.getThumbnailUrl(),
                newsletter.getName()))
            .toList())
        .orElseGet(ArrayList::new);
  }
}