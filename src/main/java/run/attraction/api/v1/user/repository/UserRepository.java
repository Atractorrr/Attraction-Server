package run.attraction.api.v1.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByNickName(String nickName);

  Optional<User> findByEmail(String email);
}
