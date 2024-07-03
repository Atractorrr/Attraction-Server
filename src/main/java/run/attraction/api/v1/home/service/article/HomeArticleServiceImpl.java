package run.attraction.api.v1.home.service.article;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.introduction.repository.SubscriptionRepository;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;

import java.util.List;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.introduction.utils.SubscriptionUtil;

@Component
@RequiredArgsConstructor
public class HomeArticleServiceImpl implements HomeArticleService {
  private final ArticleRepository articleRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final NewsletterRepository newsletterRepository;
  private final SubscriptionUtil subscriptionUtil;

  public List<ReceivedArticlesDto> getReceivedArticles(String userEmail, int size){
    List<String> newsletterEmails = subscriptionUtil.getNewsletterEmailsSubscribedByUser(userEmail,
        subscriptionRepository, newsletterRepository);

    if(newsletterEmails.isEmpty()) {
      return Collections.emptyList();
    }

    return articleRepository.findReceivedArticlesByUserEmail(userEmail, newsletterEmails, size);
  }

  public Page<ArticleSearchDto> getArticleBySearch(String search, int page, int size){
    Pageable pageable = PageRequest.of(page,size);
    return articleRepository.findArticleBySearch(search, pageable);
  }

}
