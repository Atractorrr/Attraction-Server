package run.attraction.api.v1.auth.token.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.user.User;

@Component
public class JwtServiceImpl implements JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Getter
  @Value("${application.security.jwt.expiration}")
  private long accessExpiration;

  @Getter
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  @Override
  public String generateAccessToken(User user, Date issuedAt) {
    return buildJwtToken(user, issuedAt, accessExpiration);
  }

  @Override
  public String generateRefreshToken(User user, Date issuedAt) {
    return buildJwtToken(user, issuedAt, refreshExpiration);
  }

  private String buildJwtToken(User user, Date issuedAt, long expiration) {
    return Jwts.builder()
        .setHeader(createHeader())
        .setClaims(createClaims(user))
        .setSubject(user.getEmail())
        .setIssuedAt(issuedAt)
        .setExpiration(new Date(issuedAt.getTime() + expiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  // jwt Header 설정
  // type : Jwt
  private Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<>();
    header.put("typ", "JWT");
    header.put("alg", "HS256"); // 해시 256 암호화
    return header;
  }

  // jwt claims 설정
  // Id, NickName, Role 담기
  private Map<String, Object> createClaims(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    claims.put("role", user.getRole());
    return claims;
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
