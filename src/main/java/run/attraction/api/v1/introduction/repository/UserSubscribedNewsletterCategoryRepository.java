package run.attraction.api.v1.introduction.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.UserSubscribedNewsletterCategory;

public interface UserSubscribedNewsletterCategoryRepository extends JpaRepository<UserSubscribedNewsletterCategory, Long> {


  List<UserSubscribedNewsletterCategory> findByUserEmail(String userEmail);

  @Query("SELECT c "
      + "FROM UserSubscribedNewsletterCategory c "
      + "WHERE c.userEmail = :userEmail "
      + "AND c.category = :category")
  Optional<UserSubscribedNewsletterCategory> findByUserEmailAndCategory(String userEmail, Category category);
}
