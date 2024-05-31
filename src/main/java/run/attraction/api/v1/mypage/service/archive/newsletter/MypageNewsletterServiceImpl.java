package run.attraction.api.v1.mypage.service.archive.newsletter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.Subscribe;
import run.attraction.api.v1.archive.repository.SubscribeRepository;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageNewsletterServiceImpl implements MypageNewsletterService {
  private final SubscribeRepository subscribeRepository;
  private final NewsletterRepository newsletterRepository;

  @Override
  public List<MypageNewsletterDetail> getSubscribesByEmail(String email) {
    Subscribe subscribe = subscribeRepository.findByUserEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("올바른 email이 아닙니다"));

    return subscribe.getNewsletterIds().stream().map(newsletterId -> {
      Newsletter newsletter = newsletterRepository.findById(newsletterId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스레터입니다"));

      return new MypageNewsletterDetail(newsletter.getId(), newsletter.getThumbnailUrl(), newsletter.getName());
    }).toList();
  }
}