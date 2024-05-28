package run.attraction.api.v1.auth.token.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.auth.token.GoogleRefreshToken;

public interface GoogleRefreshTokenRepository extends JpaRepository<GoogleRefreshToken, String> {
  @Query("SELECT rt FROM GoogleRefreshToken rt WHERE rt.email = :userEmail AND rt.shouldReissueToken = true")
  Optional<GoogleRefreshToken> findTokenByEmail(String userEmail);
}
