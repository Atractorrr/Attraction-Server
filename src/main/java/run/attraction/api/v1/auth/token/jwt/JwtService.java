package run.attraction.api.v1.auth.token.jwt;

import io.jsonwebtoken.Claims;
import java.util.Date;
import run.attraction.api.v1.user.User;

public interface JwtService {
  String generateAccessToken(User user, Date issueAt);

  String generateRefreshToken(User user, Date issuedAt);

  String extractEmailFromToken(String token);

  Claims extractClaims(String token);

  long getExpireTimeFromToken(String token);
}
