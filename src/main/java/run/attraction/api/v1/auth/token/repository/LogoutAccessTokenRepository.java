package run.attraction.api.v1.auth.token.repository;

import org.springframework.data.repository.CrudRepository;
import run.attraction.api.v1.auth.token.LogoutAccessToken;

public interface LogoutAccessTokenRepository extends CrudRepository<LogoutAccessToken, String> {
}