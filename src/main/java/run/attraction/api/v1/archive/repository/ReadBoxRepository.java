package run.attraction.api.v1.archive.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.ReadBox;

public interface ReadBoxRepository extends JpaRepository<ReadBox, Long> {
  @Query("SELECT rb FROM ReadBox rb WHERE rb.userEmail = :userEmail AND rb.readPercentage = 100")
  List<ReadBox> findCompletedReadBoxByEmail(String userEmail);

  @Query("SELECT rb FROM ReadBox rb WHERE rb.userEmail = :userEmail AND rb.articleId = :articleId")
  Optional<ReadBox> findByUserEmailAndArticleId(String userEmail, Long articleId);

  List<ReadBox> findByUserEmail(String email);

  @Query(value = """
        SELECT rb.user_email, COUNT(*)
        FROM read_box rb 
        WHERE rb.read_date BETWEEN :startDate AND :endDate
          AND rb.read_date <> :excludeDate
          AND rb.read_percentage = 100 
        GROUP BY rb.user_email
        ORDER BY COUNT(*) DESC
        LIMIT 10
    """, nativeQuery = true)
  List<Object[]> findTop10ExtensiveUsers(LocalDate startDate, LocalDate endDate,LocalDate excludeDate);

}
