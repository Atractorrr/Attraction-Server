package run.attraction.api.v1.home.service.article;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.Subscribe;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.archive.repository.SubscribeRepository;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;

import java.util.List;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;

@Component
@RequiredArgsConstructor
public class HomeArticleServiceImpl implements HomeArticleService {
  private final ArticleRepository articleRepository;
  private final SubscribeRepository subscribeRepository;
  private final NewsletterRepository newsletterRepository;

  public List<ReceivedArticlesDto> getReceivedArticles(String userEmail, int size){
    Subscribe subscribe = subscribeRepository.findByUserEmail(userEmail)
        .orElse(null);

    if(subscribe == null) {
      return new ArrayList<>();
    }

    List<String> newsletterEmails = subscribe.getNewsletterIds()
        .stream()
        .map(id -> newsletterRepository.findById(id).get().getEmail())
        .toList();

    return articleRepository.findReceivedArticlesByUserEmail(userEmail, newsletterEmails, size);
  }

  public Page<ArticleSearchDto> getArticleBySearch(String search, int page, int size){
    Pageable pageable = PageRequest.of(page,size);
    return articleRepository.findArticleBySearch(search, pageable);
  }

}
