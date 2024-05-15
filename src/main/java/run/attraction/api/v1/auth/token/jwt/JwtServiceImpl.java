package run.attraction.api.v1.auth.token.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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
import org.springframework.stereotype.Component;
import run.attraction.api.v1.auth.token.exception.InvalidTokenException;
import run.attraction.api.v1.auth.token.exception.TokenExpirationException;
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

  @Override
  public String extractEmailFromToken(String token) {
    try {
      Claims claims = extractClaims(token);
      return claims.getSubject();
    } catch (ExpiredJwtException e) {
      throw new TokenExpirationException("만료된 토큰입니다.");
    } catch (JwtException | NullPointerException exception) {
      throw new InvalidTokenException("토큰이 유효하지 않습니다.");
    }
  }

  @Override
  public long getExpireTimeFromToken(String token) {
    try {
      final Claims claims = extractClaims(token);
      return claims.getExpiration().getTime();
    } catch (ExpiredJwtException exception) {
      throw new TokenExpirationException("만료된 토큰 입니다.");
    } catch (JwtException | NullPointerException exception) {
      throw new InvalidTokenException("토큰이 유효하지 않습니다.");
    }
  }

  public Claims extractClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
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
