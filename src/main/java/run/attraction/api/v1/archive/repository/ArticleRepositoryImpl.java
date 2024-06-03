package run.attraction.api.v1.archive.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import run.attraction.api.v1.archive.QArticle;
import run.attraction.api.v1.archive.QReadBox;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.QArticleDTO;
import run.attraction.api.v1.introduction.QNewsletter;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

  private final JPAQueryFactory queryFactory;
  private final QArticle article = QArticle.article;
  private final QReadBox readBox = QReadBox.readBox;
  private final QNewsletter newsletter = QNewsletter.newsletter;

  public ArticleRepositoryImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public Page<ArticleDTO> findArticlesByUserEmail(String userEmail, String category, Boolean isHideRead, String search,
                                               Pageable pageable) {
    BooleanExpression predicate = buildPredicate(userEmail, category, isHideRead, search);
    List<ArticleDTO> articles = getArticles(predicate, pageable);
    Long total = getTotal(predicate);

    return new PageImpl<>(articles, pageable, total);
  }

  private BooleanExpression buildPredicate(String userEmail, String category, boolean isHideRead, String search) {
    BooleanExpression predicate = readBox.userEmail.eq(userEmail);

    if (isNotNullAndNotEmpty(category)) {
      predicate = predicate.and(null);
    }
    if (isHideRead) {
      predicate = predicate.and(readBox.percentage.notIn(100));
    }
    if (isNotNullAndNotEmpty(search)) {
      predicate = predicate.and(article.title.containsIgnoreCase(search));
    }

    return predicate;
  }

  private boolean isNotNullAndNotEmpty(String value) {
    return value != null && !value.isEmpty();
  }

  private List<ArticleDTO> getArticles(BooleanExpression predicate, Pageable pageable) {
    JPAQuery<ArticleDTO> articles = queryFactory
        .select(new QArticleDTO(this.article, readBox.percentage, newsletter))
        .from(this.article)
        .join(readBox).on(this.article.id.eq(readBox.articleId))
        .join(newsletter).on(this.article.newsletterEmail.eq(newsletter.email))
        .where(predicate)
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
            .join(readBox).on(article.id.eq(readBox.articleId))
            .where(predicate)
            .fetchOne()
    ).orElse(0L);
  }

  @Override
  public Optional<ArticleDTO> findArticleByUserEmailAndArticleId(String userEmail, Long articleId) {
    return Optional.ofNullable(queryFactory
        .select(new QArticleDTO(this.article, readBox.percentage, newsletter))
        .from(this.article)
        .join(readBox).on(this.article.id.eq(readBox.articleId).and(this.article.userEmail.eq(readBox.userEmail)))
        .join(newsletter).on(this.article.newsletterEmail.eq(newsletter.email))
        .where(article.id.eq(articleId).and(article.userEmail.eq(userEmail)))
        .fetchOne());
  }
}
