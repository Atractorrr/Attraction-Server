package run.attraction.api.v1.archive.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

  @Query("""
    SELECT DISTINCT new run.attraction.api.v1.home.service.dto.search.ArticleSearchDto(
        a.id, a.title, a.thumbnailUrl, a.contentSummary, a.readingTime, a.receivedAt,
        new run.attraction.api.v1.archive.dto.NewsletterDTO(n.id, n.name, n.category, n.thumbnailUrl, n.mainLink, n.prevArticleListUrl)
    )
    FROM Article a JOIN Newsletter n ON a.newsletterEmail = n.email
    WHERE (a.title LIKE %:search%) 
      AND a.isDeleted = false
    ORDER BY a.receivedAt DESC
    """)
  Page<ArticleSearchDto> findArticleBySearch(String search, Pageable pageable);

  @Query("""
    SELECT n.id 
    FROM Article a
    JOIN Newsletter n ON a.newsletterEmail = n.email
    WHERE a.id = :articleId
    """)
  Long findNewsletterIdByArticleId(Long articleId);
}
