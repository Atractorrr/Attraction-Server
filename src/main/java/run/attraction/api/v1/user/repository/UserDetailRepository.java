package run.attraction.api.v1.user.repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.attraction.api.v1.user.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,String > {
  boolean existsByNickname(String nickname);

//  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT u FROM UserDetail u WHERE u.email = :email")
  Optional<UserDetail> findUserDetailByEmail(@Param("email") String email);
  //  @QueryHints({
//      @QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")
//  })
}
