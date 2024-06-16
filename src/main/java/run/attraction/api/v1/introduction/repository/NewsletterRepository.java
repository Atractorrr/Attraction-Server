package run.attraction.api.v1.introduction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;

import java.util.List;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

  @Query("SELECT COUNT(n) FROM Newsletter n WHERE n.category = :category AND n.id <> :id")
  int countByCategoryAndIdNot(@Param("id") Long id, @Param("category") Category category);

  @Query(value = "SELECT * FROM newsletter n WHERE n.category = :category AND n.id <> :id LIMIT :size OFFSET :offset", nativeQuery = true)
  List<Newsletter> findByCategoryAndIdNotWithOffset(@Param("id") Long id, @Param("category") String category,
                                                    @Param("size") int size, @Param("offset") int offset);

  @Query("SELECT n.email, n FROM Newsletter n WHERE n.email IN :newsletterEmails")
  List<Object[]> findNewslettersByNewsletterEmails(List<String> newsletterEmails);

  @Query("SELECT n.id, n FROM Newsletter n WHERE n.category = :category")
  List<Object[]> findByCategory(Category category);

  @Query ("SELECT n FROM Newsletter n WHERE n.id NOT IN :newsletterIds ORDER BY function('RAND') LIMIT :size")
  List<Newsletter> findNewsletterRandom(List<Long>newsletterIds,int size);

  @Query ("SELECT n FROM Newsletter n WHERE n.id IN :newsletterIds")
  List<Newsletter> findNewslettersByNewsletterIds(@Param("newsletterIds") List<Long> newsletterIds);

  @Query("""
    SELECT new run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse(
       n.id, n.thumbnailUrl, n.name, n.description
    )
    FROM Newsletter n
    WHERE (n.name LIKE %:search%) AND n.isDeleted = false
    """)
  Page<NewslettersByCategoryResponse> findNewsletterBySearch(String search, Pageable pageable);

}
