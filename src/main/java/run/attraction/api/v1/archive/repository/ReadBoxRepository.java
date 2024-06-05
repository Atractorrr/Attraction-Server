package run.attraction.api.v1.archive.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.ReadBox;

public interface ReadBoxRepository extends JpaRepository<ReadBox, Long> {
  @Query("SELECT rb FROM ReadBox rb WHERE rb.userEmail = :userEmail AND rb.readPercentage = 100")
  List<ReadBox> findCompletedReadBoxByEmail(String userEmail);
  Optional<ReadBox> findByUserEmailAndArticleId(String userEmail, Long articleId);
  List<ReadBox> findByUserEmail(String email);
}
