package run.attraction.api.v1.introduction.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.introduction.Article;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;

@Service
@RequiredArgsConstructor
public class IntroductionService {

  private final NewsletterRepository newsletterRepository;

  public NewsletterResponse getNewsletter(Long newsletterId) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));

    return new NewsletterResponse(newsletter);
  }

  public List<PreviousArticleResponse> getPreviousArticles(Long newsletterId, int size) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    List<Article> previousArticles = newsletter.getArticles();

    return previousArticles.stream()
        .limit(size)
        .map(PreviousArticleResponse::new)
        .collect(Collectors.toList());
  }
}


