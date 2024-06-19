package run.attraction.api.v1.archive.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
  @Query("""
      SELECT a 
      FROM Article a 
      WHERE a.userEmail = :userEmail 
      AND a.id IN :articleIds 
      AND a.receivedAt BETWEEN :startDate AND :endDate
    """)
  List<Article> findArticleByIdAndUserEmail(List<Long> articleIds, String userEmail, LocalDate startDate, LocalDate endDate);

  @Query("""
      SELECT new run.attraction.api.v1.home.service.dto.article.ArticleDetailDto(
          a.id, n.thumbnailUrl, n.name, a.thumbnailUrl, a.title, a.receivedAt, a.readingTime, COALESCE(MAX(r.readPercentage), 0)
      )
      FROM Article a
      JOIN Newsletter n ON a.newsletterEmail = n.email
      LEFT JOIN ReadBox r ON a.id = r.articleId
      WHERE a.userEmail = :email
        AND a.receivedAt BETWEEN :startDate AND :endDate
      GROUP BY a.id, n.thumbnailUrl, n.name, a.thumbnailUrl, a.title, a.receivedAt, a.readingTime
    """)
  List<ArticleDetailDto> getReceivedArticleByEmail(String email, LocalDate startDate, LocalDate endDate);

  @Query("""
    select new run.attraction.api.v1.archive.dto.ArticleDTO(
      a, COALESCE(r.readPercentage, 0), n
    ) 
    FROM Article a
    JOIN Newsletter n ON a.newsletterEmail = n.email
    LEFT JOIN ReadBox r ON a.id = r.articleId
    WHERE a.userEmail = :userEmail
    AND a.id IN :articleIds
   """)
  Page<ArticleDTO> findArticlesByIdsAndUserEmail(List<Long> articleIds, String userEmail, Pageable pageable);

  @Query("""
    SELECT DISTINCT new run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse(
        a.id, a.title, a.thumbnailUrl, a.contentUrl, a.contentSummary, a.readingTime, a.receivedAt,n.name,
        new run.attraction.api.v1.archive.dto.NewsletterDTO(n.id, n.name, n.category, n.thumbnailUrl)
    )
    FROM Article a JOIN Newsletter n ON a.newsletterEmail = n.email
    WHERE (a.title LIKE %:search%) 
      AND a.isDeleted = false
    ORDER BY a.receivedAt DESC
    """)
  Page<PreviousArticleResponse> findArticleBySearch(String search, Pageable pageable);

}
