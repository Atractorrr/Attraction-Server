package run.attraction.api.v1.introduction.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

  @Query("SELECT COUNT(n) FROM Newsletter n WHERE n.category = :category AND n.id <> :id")
  int countByCategoryAndIdNot(@Param("id") Long id, @Param("category") Category category);

  @Query(value = "SELECT * FROM Newsletter n WHERE n.category = :category AND n.id <> :id LIMIT :size OFFSET :offset", nativeQuery = true)
  List<Newsletter> findByCategoryAndIdNotWithOffset(@Param("id") Long id, @Param("category") String category,
                                                    @Param("size") int size, @Param("offset") int offset);

  @Query("SELECT n.email, n FROM Newsletter n WHERE n.email IN :newsletterEmails")
  List<Object[]> findNewslettersByNewsletterEmails(List<String> newsletterEmails);

  @Query("SELECT n.id, n FROM Newsletter n WHERE n.category = :category")
  List<Object[]> findByCategory(Category category);

  @Query ("SELECT n FROM Newsletter n WHERE n.id NOT IN :newsletterIds ORDER BY function('RAND') LIMIT :size")
  List<Newsletter> findNewsletterRandom(List<Long>newsletterIds,int size);

}
