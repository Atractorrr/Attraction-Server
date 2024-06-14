package run.attraction.api.v1.statistics.repository;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.statistics.AgeGroup;
import run.attraction.api.v1.statistics.NewsletterEvent;
import run.attraction.api.v1.user.Occupation;

public interface NewsletterEventRepository extends JpaRepository<NewsletterEvent,Long> {
  List<NewsletterEvent> findByOccupation(Occupation occupation);
  List<NewsletterEvent> findByAgeGroup(AgeGroup ageGroup);

  @Query(value = """
          SELECT occupation, newsletter_id 
          FROM (SELECT occupation, newsletter_id, 
                       ROW_NUMBER() OVER (PARTITION BY occupation ORDER BY COUNT(newsletter_id) DESC) as rn 
                FROM statistics_newsletter_event
                WHERE created_at < :endDate
                GROUP BY occupation, newsletter_id) subquery 
          WHERE rn = 1
       """,nativeQuery = true)
  List<Object[]> findMostPopularNewsletterOccupationPairs(LocalDateTime endDate);

  @Query(value = """
          SELECT age_group, newsletter_id 
          FROM (SELECT age_group, newsletter_id, 
                       ROW_NUMBER() OVER (PARTITION BY age_group ORDER BY COUNT(newsletter_id) DESC) as rn 
                FROM statistics_newsletter_event
                WHERE created_at < :endDate
                GROUP BY age_group, newsletter_id) subquery 
          WHERE rn = 1
       """,nativeQuery = true)
  List<Object[]> findMostPopularNewsletterAgeGroupPairs(LocalDateTime endDate);
}
