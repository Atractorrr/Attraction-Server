package run.attraction.api.v1.introduction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.introduction.Newsletter;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
}
