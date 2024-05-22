package run.attraction.api.v1.archive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.introduction.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

}
