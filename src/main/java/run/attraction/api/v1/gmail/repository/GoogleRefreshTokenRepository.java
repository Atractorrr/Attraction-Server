package run.attraction.api.v1.gmail.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.gmail.entity.GoogleRefreshToken;

public interface GoogleRefreshTokenRepository extends JpaRepository<GoogleRefreshToken, String> {
  @Query("SELECT rt FROM GoogleRefreshToken rt WHERE rt.email = :userEmail AND rt.shouldReissueToken = true")
  Optional<GoogleRefreshToken> findInvalidTokenByEmail(String userEmail);
  GoogleRefreshToken findByEmail(String userEmail);

  @Query("""
      SELECT rt FROM GoogleRefreshToken rt 
      WHERE rt.email = :userEmail
        AND rt.shouldReissueToken = false 
  """)
  Optional<GoogleRefreshToken> findTokenByEmail(String userEmail);
}
