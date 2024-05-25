package run.attraction.api.v1.mypage.service.archive.article;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.archive.repository.ReadBoxRepository;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.mypage.service.dto.archive.article.MypageArticle;
import run.attraction.api.v1.mypage.service.dto.archive.article.UserArticleDetail;
import run.attraction.api.v1.mypage.service.dto.archive.article.UserArticleImg;
import run.attraction.api.v1.mypage.service.dto.archive.article.UserReadBoxDetail;

@Component
@RequiredArgsConstructor
public class MypageArticleServiceImpl implements MypageArticleService {

  private final ArticleRepository articleRepository;
  private final NewsletterRepository newsletterRepository;
  private final ReadBoxRepository readBoxRepository;

  public List<MypageArticle> getUserRecentArticles(String email) {
    final List<UserReadBoxDetail> userReadBoxDetails = getReadBoxDetailsByEmail(email);
    return getMypageArticles(email, userReadBoxDetails);
  }

  private List<UserReadBoxDetail> getReadBoxDetailsByEmail(String email) {
    final List<ReadBox> readBoxes = readBoxRepository.findByUserEmail(email);
    return readBoxes.stream()
        .map(readBox -> new UserReadBoxDetail(
            readBox.getArticleId(),
            readBox.getReadDate(),
            readBox.getPercentage()))
        .toList();
  }

  private List<MypageArticle> getMypageArticles(String email, List<UserReadBoxDetail> userReadBoxDetails) {
    final List<Long> articleIds = extractArticleIds(userReadBoxDetails);
    final Map<Long, LocalDate> readDates = extractReadDates(userReadBoxDetails);
    final Map<Long, Integer> percentages = extractPercentage(userReadBoxDetails);
    final List<Article> articles = articleRepository.findArticleByIdAndUserEmail(articleIds, email);
    final Map<String, Newsletter> newsletters = extractNewsletterProfiles(articles);

    return articles.stream()
        .map(article -> {
          Newsletter newsletter = newsletters.get(article.getNewsletterEmail());
          return new MypageArticle(
              article.getId(),
              getImage(article, newsletter),
              getInfo(article, newsletter, readDates, percentages)
          );
        })
        .toList();
  }

  private Map<Long, LocalDate> extractReadDates(List<UserReadBoxDetail> userReadBoxDetails) {
    return userReadBoxDetails.stream().collect(Collectors.toMap(
        userReadBoxDetail -> userReadBoxDetail.articleId(),
        userReadBoxDetail -> userReadBoxDetail.readDate()
    ));
  }

  private Map<Long, Integer> extractPercentage(List<UserReadBoxDetail> userReadBoxDetails) {
    return userReadBoxDetails.stream().collect(Collectors.toMap(
        UserReadBoxDetail::articleId,
        UserReadBoxDetail::readPercentage
    ));
  }

  //사용자의 ReadBox에서 article Id 값만 추출
  private List<Long> extractArticleIds(List<UserReadBoxDetail> userReadBoxDetails) {
    return userReadBoxDetails.stream()
        .map(UserReadBoxDetail::articleId)
        .toList();
  }

  //뉴스레터 Email값을 이용해 Newsletter Entitu 추출
  //Map<String,String> => KEY : newsletterEmail / VALUE : Newsletter Entitu
  private Map<String, Newsletter> extractNewsletterProfiles(List<Article> articles) {
    final List<String> newsletterEmails = extractnewsletterEamils(articles);
    final List<Object[]> thumbnails = newsletterRepository.findNewslettersByNewsletterEmails(newsletterEmails);
    return thumbnails.stream()
        .collect(Collectors.toMap(
            newsletterthumnail -> (String) newsletterthumnail[0],
            newsletterthumnail -> (Newsletter) newsletterthumnail[1]
        ));
  }

  //사용자 email과 article Id을 이용해 뉴스레터의 email값 추출 (중복X)
  private List<String> extractnewsletterEamils(List<Article> articles) {
    return articles.stream()
        .map(Article::getNewsletterEmail)
        .distinct()
        .toList();
  }

  private static UserArticleImg getImage(Article article, Newsletter newsletter) {
    return new UserArticleImg(article.getThumbnailUrl(), newsletter.getThumbnailUrl());
  }

  private static UserArticleDetail getInfo(Article article,
                                           Newsletter newsletter,
                                           Map<Long, LocalDate> readDates,
                                           Map<Long, Integer> percentages) {
    return new UserArticleDetail(
        article.getTitle(),
        newsletter.getName(),
        readDates.get(article.getId()),
        article.getReadingTime(),
        percentages.get(article.getId())
    );
  }

}
