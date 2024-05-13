package run.attraction.api.v1.auth.token.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.auth.token.RefreshToken;
import run.attraction.api.v1.user.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findTokenByUser(User user);
  Optional<RefreshToken> findByToken(String refreshToken);

}
