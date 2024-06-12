package run.attraction.api.v1.statistics.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.statistics.AgeGroup;
import run.attraction.api.v1.statistics.NewsletterEvent;
import run.attraction.api.v1.user.Occupation;

public interface NewsletterEventRepository extends JpaRepository<NewsletterEvent,Long> {
  List<NewsletterEvent> findByOccupation(Occupation occupation);
  List<NewsletterEvent> findByAgeGroup(AgeGroup ageGroup);
}
