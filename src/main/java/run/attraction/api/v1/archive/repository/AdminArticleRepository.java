package run.attraction.api.v1.archive.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.archive.AdminArticle;

public interface AdminArticleRepository extends JpaRepository<AdminArticle, Long> {
  Optional<List<AdminArticle>> findByNewsletterEmail(String newsletterEmail);
}
