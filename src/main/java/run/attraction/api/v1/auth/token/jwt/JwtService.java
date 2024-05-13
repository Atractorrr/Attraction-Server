package run.attraction.api.v1.auth.token.jwt;

import java.util.Date;
import run.attraction.api.v1.user.User;

public interface JwtService {
  String generateAccessToken(User user, Date issueAt);

  String generateRefreshToken(User user, Date issuedAt);
}
