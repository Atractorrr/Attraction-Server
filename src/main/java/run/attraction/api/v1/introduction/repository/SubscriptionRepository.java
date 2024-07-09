package run.attraction.api.v1.introduction.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.introduction.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
  List<Subscription> findByUserEmail(String userEmail);

  @Query(value = """ 
     SELECT newsletter_id
     FROM subscription
     GROUP BY newsletter_id
     ORDER BY COUNT(newsletter_id) DESC
     """, nativeQuery = true)
  List<Long> findMostSubscribedNewsletterIds();

  @Query(" SELECT s.newsletterId" +
          " FROM Subscription s" +
          " WHERE  s.userEmail  =:userEmail AND s.isDeleted = false")
  List<Long> findNewsletterIdsByUserEmailWithoutIsDeleted(@Param("userEmail") String userEmail);

  @Query(" SELECT s " +
          " FROM Subscription s" +
          " WHERE s.isDeleted = false ")
  List<Subscription> findByUserEmailAndIsDeleted(String userEmail);

  @Query(" SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
      " FROM Subscription s " +
      " WHERE s.userEmail = :userEmail " +
      " AND s.newsletterId = :newsletterId" +
      " AND s.isDeleted = false ")
  boolean existsByUserEmailAndNewsletterId(@Param("userEmail") String userEmail, @Param("newsletterId") Long newsletterId);

  Optional<Subscription> findByUserEmailAndNewsletterId(String userEmail, Long newsletterId);
}
