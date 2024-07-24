package run.attraction.api.v1.archive.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import run.attraction.api.v1.archive.QArticle;
import run.attraction.api.v1.archive.QReadBox;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.QArticleDTO;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.home.service.dto.article.QReceivedArticlesDto;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.QNewsletter;
import run.attraction.api.v1.introduction.QSubscription;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.dto.response.QPreviousArticleResponse;
import run.attraction.api.v1.mypage.service.dto.archive.article.QRecentArticlesDto;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesDto;

@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QArticle article = QArticle.article;
  private final QReadBox readBox = QReadBox.readBox;
  private final QNewsletter newsletter = QNewsletter.newsletter;
  private final QSubscription subscription = QSubscription.subscription;

  public ArticleRepositoryImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public Page<ArticleDTO> findArticlesByUserEmail(String userEmail, List<String> newsletterEmails, UserArticlesRequest request,
                                                  Pageable pageable) {


    BooleanExpression predicate = buildPredicate(newsletterEmails, request);
    List<ArticleDTO> articles = getArticles(predicate, pageable, userEmail);
    Long total = getTotal(predicate);

    return new PageImpl<>(articles, pageable, total);
  }

  private BooleanExpression buildPredicate(List<String> newsletterEmails, UserArticlesRequest request) {

    String HIDE_READ_TRUE = "true";
    boolean isHideRead = request.getIsHideRead().equalsIgnoreCase(HIDE_READ_TRUE);

    BooleanExpression predicate = article.newsletterEmail.in(newsletterEmails);

    if (isNotNullAndNotEmpty(request.getCategory()) && isNullOrEmpty(request.getNewsletterId())) {
      predicate = predicate.and(article.newsletterEmail.in(
          JPAExpressions.select(newsletter.email)
              .from(newsletter)
              .where(newsletter.category.eq(Category.valueOf(request.getCategory())))
      ));
    } else if (isNotNullAndNotEmpty(request.getNewsletterId()) && isNullOrEmpty(request.getCategory())) {
      predicate = predicate.and(article.newsletterEmail.in(
          JPAExpressions.select(newsletter.email)
              .from(newsletter)
              .where(newsletter.id.eq(Long.valueOf(request.getNewsletterId())))
      ));
    }
    if (isHideRead) {
      predicate = predicate.and(article.id.notIn(
          JPAExpressions.select(readBox.articleId)
              .from(readBox)
              .where(readBox.readPercentage.eq(100))
      ));
    }
    if (isNotNullAndNotEmpty(request.getQ())) {
      predicate = predicate.and(article.title.containsIgnoreCase(request.getQ()));
    }

    return predicate;
  }

  private boolean isNotNullAndNotEmpty(String requestValue) {
    return requestValue != null && !requestValue.isEmpty();
  }

  private boolean isNullOrEmpty(String requestValue) {
    return requestValue == null || requestValue.isEmpty();
  }

  private List<ArticleDTO> getArticles(BooleanExpression predicate, Pageable pageable, String userEmail) {

    log.info("Current Date: " + getCurrentDate());
    log.info("seven Days Ago: " + getSevenDaysAgo());

    JPAQuery<ArticleDTO> articles = queryFactory
        .select(new QArticleDTO(this.article, readBox.readPercentage.coalesce(0), newsletter))
        .from(this.article)
        .leftJoin(readBox).on(this.article.id.eq(readBox.articleId).and(readBox.userEmail.eq(userEmail)))
        .join(newsletter).on(this.article.newsletterEmail.eq(newsletter.email))
        .where(predicate)
        .where(article.receivedAt.between(getSevenDaysAgo(), getCurrentDate()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    applySorting(articles, pageable);

    return articles.fetch();
  }

  private void applySorting(JPAQuery<ArticleDTO> articles, Pageable pageable) {
    articles.orderBy(pageable.getSort().isSorted() && pageable.getSort().iterator().next().isAscending()
        ? article.receivedAt.asc()
        : article.receivedAt.desc());
  }

  private Long getTotal(BooleanExpression predicate) {
    return Optional.ofNullable(
        queryFactory
            .select(article.count())
            .from(article)
            .where(predicate)
            .fetchOne()
    ).orElse(0L);
  }

  @Override
  public Optional<ArticleDTO> findArticleByUserEmailAndArticleId(String userEmail, Long articleId) {
    return Optional.ofNullable(queryFactory
        .select(new QArticleDTO(this.article, readBox.readPercentage, newsletter))
        .from(this.article)
        .join(readBox).on(this.article.id.eq(readBox.articleId).and(readBox.userEmail.eq(userEmail)))
        .join(newsletter).on(this.article.newsletterEmail.eq(newsletter.email))
        .where(article.id.eq(articleId))
        .fetchOne());
  }

  @Override
  public Page<ArticleDTO> findArticlesByArticleIds(List<Long> articleIds, String category, String search,
                                                   Pageable pageable) {
    BooleanExpression predicate = buildPredicateByArticleIds(articleIds, category, search);
    String userEmail = "";
    List<ArticleDTO> articles = getArticles(predicate, pageable, userEmail);
    Long total = getTotal(predicate);

    return new PageImpl<>(articles, pageable, total);
  }

  private BooleanExpression buildPredicateByArticleIds(List<Long> articleIds, String category, String search) {

    BooleanExpression predicate = article.id.in(articleIds);

    if (isNotNullAndNotEmpty(category)) {
      predicate = predicate.and(article.newsletterEmail.in(
          JPAExpressions.select(newsletter.email)
              .from(newsletter)
              .where(newsletter.category.eq(Category.valueOf(category)))
      ));
    }

    if (isNotNullAndNotEmpty(search)) {
      predicate = predicate.and(article.title.containsIgnoreCase(search));
    }

    return predicate;
  }

  public List<RecentArticlesDto> findRecentArticlesByUserEmail(String userEmail, int size) {
    JPAQuery<RecentArticlesDto> articles = queryFactory
        .select(new QRecentArticlesDto(this.article, readBox.readPercentage.coalesce(0), newsletter))
        .from(this.article)
        .join(readBox).on(this.article.id.eq(readBox.articleId)
            .and(readBox.userEmail.eq(userEmail)))
        .join(newsletter).on(this.article.newsletterEmail.eq(newsletter.email))
        .join(subscription).on(subscription.userEmail.eq(userEmail))
        .where(readBox.modifiedAt.isNotNull()
            .and(Expressions.dateTemplate(LocalDate.class, "DATE({0})", readBox.modifiedAt)
                .between(getSevenDaysAgo(), getCurrentDate()))
            .and(newsletter.id.eq(subscription.newsletterId)))
        .where(article.receivedAt.between(getSevenDaysAgo(), getCurrentDate()))
        .orderBy(readBox.modifiedAt.desc())
        .limit(size);

    return articles.fetch();
  }

  public List<ReceivedArticlesDto> findReceivedArticlesByUserEmail(String userEmail, List<String> newsletterEmails, int size) {
    JPAQuery<ReceivedArticlesDto> articles = queryFactory
        .select(new QReceivedArticlesDto(this.article, readBox.readPercentage.coalesce(0), newsletter))
        .from(this.article)
        .leftJoin(readBox).on(this.article.id.eq(readBox.articleId).and(readBox.userEmail.eq(userEmail)))
        .join(newsletter).on(this.article.newsletterEmail.eq(newsletter.email))
        .where(article.newsletterEmail.in(newsletterEmails))
        .where(article.receivedAt.between(getSevenDaysAgo(), getCurrentDate()))
        .orderBy(article.receivedAt.desc())
        .limit(size);

    return articles.fetch();
  }

  public List<PreviousArticleResponse> findPreviousArticlesByUserEmail(String userEmail, int size) {
    return queryFactory
        .select(new QPreviousArticleResponse(this.article, newsletter))
        .from(this.article)
        .join(newsletter).on(newsletter.email.eq(userEmail))
        .where(this.article.newsletterEmail.eq(newsletter.email))
        .orderBy(this.article.receivedAt.desc())
        .limit(size)
        .fetch();
  }

  private static LocalDate getCurrentDate() {
    return LocalDate.now();
  }

  private static LocalDate getSevenDaysAgo() {
    return LocalDate.now().minusDays(7);
  }

}
