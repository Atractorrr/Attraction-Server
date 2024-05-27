package run.attraction.api.v1.archive.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.archive.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
  Optional<Subscribe> findByUserEmail(String userEmail);
}
