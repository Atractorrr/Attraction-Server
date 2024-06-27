package run.attraction.api.v1.user.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.user.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,String > {
  boolean existsByNickname(String nickname);

  @Query("SELECT u.nickname FROM UserDetail u WHERE u.email = :email")
  Optional<String> findNicknameByEmail(@Param("email") String email);
}
