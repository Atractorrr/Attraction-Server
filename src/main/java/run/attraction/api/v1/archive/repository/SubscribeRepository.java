package run.attraction.api.v1.archive.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
  Optional<Subscribe> findByUserEmail(String userEmail);

  @Query(""" 
      SELECT n.id 
      FROM Subscribe s
      JOIN s.newsletters n
      GROUP BY n.id 
      ORDER BY COUNT(n.id) DESC 
      """)
  List<Long> findMostSubscribedNewsletterIds();
}
