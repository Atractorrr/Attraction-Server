package run.attraction.api.v1.introduction.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

  @Query("SELECT COUNT(n) FROM Newsletter n WHERE n.category = :category AND n.id <> :id")
  int countByCategoryAndIdNot(@Param("category") Category category, @Param("id") Long id);

  @Query(value = "SELECT * FROM Newsletter n WHERE n.category = :category AND n.id <> :id LIMIT :size OFFSET :offset", nativeQuery = true)
  List<Newsletter> findByCategoryAndIdNotWithOffset(@Param("category") String category, @Param("id") Long id, @Param("size") int size, @Param("offset") int offset);
}
