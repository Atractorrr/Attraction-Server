package run.attraction.api.v1.introduction.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.introduction.UserSubscribedNewsletterCategory;

public interface UserSubscribedNewsletterCategoryRepository extends JpaRepository<UserSubscribedNewsletterCategory, Long> {

  Optional<UserSubscribedNewsletterCategory> findByUserEmail(String userEmail);
}
