package run.attraction.api.v1.mypage.service.archive.newsletter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.introduction.Subscribe;
import run.attraction.api.v1.introduction.repository.SubscribeRepository;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

@Component
@RequiredArgsConstructor
public class MypageNewsletterServiceImpl implements MypageNewsletterService {
  private final SubscribeRepository subscribeRepository;
  private final NewsletterRepository newsletterRepository;

  @Override
  public List<MypageNewsletterDetail> getSubscribesByEmail(String email) {
    final Optional<Subscribe> subscribe = subscribeRepository.findByUserEmail(email);
    return subscribe
        .map(sub -> {
          List<Newsletter> newsletters = newsletterRepository.findNewslettersByNewsletterIds(sub.getNewsletterIds());
          return newsletters.stream()
              .map(newsletter -> new MypageNewsletterDetail(
                  newsletter.getId(),
                  newsletter.getThumbnailUrl(),
                  newsletter.getName()))
              .toList();
        })
        .orElseGet(ArrayList::new);
  }
}