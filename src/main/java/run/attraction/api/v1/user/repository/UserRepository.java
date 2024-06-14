package run.attraction.api.v1.user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.user.User;

public interface UserRepository extends JpaRepository<User, String> {

  @Query("""
        SELECT u.email, u.profileImg, ud.nickname
        FROM User u
        LEFT JOIN UserDetail ud ON u.email = ud.email
        WHERE u.email IN :emails
    """)
  List<Object[]> findProfileImgAndNicknameByUseremails(List<String> emails);
}
