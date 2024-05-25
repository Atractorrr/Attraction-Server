package run.attraction.api.v1.archive.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.ReadBox;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
  @Query("SELECT a FROM Article a WHERE a.userEmail = :userEmail AND a.id IN :articleIds")
  List<Article> findArticleByIdAndUserEmail(List<Long> articleIds, String userEmail);

}
