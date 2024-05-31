package run.attraction.api.v1.archive.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
  Optional<Subscribe> findByUserEmail(String userEmail);

  @Query(value = """ 
     SELECT newsletter_id
     FROM newsletter_ids
     GROUP BY newsletter_id
     ORDER BY COUNT(newsletter_id) DESC
     """, nativeQuery = true)
  List<Long> findMostSubscribedNewsletterIds();
}
