package run.attraction.api.v1.introduction.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.introduction.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
  Optional<Subscribe> findByUserEmail(String userEmail);

  @Query(value = """ 
     SELECT newsletter_id
     FROM newsletter_ids
     GROUP BY newsletter_id
     ORDER BY COUNT(newsletter_id) DESC
     """, nativeQuery = true)
  List<Long> findMostSubscribedNewsletterIds();

  @Query(value = """
          SELECT newsletter_id
          FROM newsletter_ids
          JOIN subscribe
          WHERE  subscribe.user_email = :userEmail
          AND subscribe_id = subscribe.id
          """, nativeQuery = true)
  List<Long> findNewsletterIdsByUserEmail(@Param("user_email") String userEmail);

  @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END " +
      "FROM Subscribe s JOIN s.newsletterIds n " +
      "WHERE s.userEmail = :userEmail " +
      "AND n = :newsletterId")
  boolean existsByUserEmailAndNewsletterId(@Param("userEmail") String userEmail, @Param("newsletterId") Long newsletterId);
}
