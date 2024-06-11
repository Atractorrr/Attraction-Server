package run.attraction.api.v1.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.user.User;

public interface UserRepository extends JpaRepository<User, String> {
}
