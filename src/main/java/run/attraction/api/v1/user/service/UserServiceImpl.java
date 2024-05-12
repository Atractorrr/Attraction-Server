package run.attraction.api.v1.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public boolean existsByUserId(Long userId) {
    return userRepository.existsById(userId);
  }
}
