package run.attraction.api.v1.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.user.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,String > {
  boolean existsByNickname(String nickname);

}
