package run.attraction.api.v1.archive.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import run.attraction.api.v1.archive.dto.ArticleDTO;

public interface ArticleRepositoryCustom {
  Page<ArticleDTO> findArticlesByUserId(Long userId, String category, Boolean isRead, String search,  Pageable pageable);
}
