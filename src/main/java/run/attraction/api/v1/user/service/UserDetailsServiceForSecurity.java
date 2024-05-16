package run.attraction.api.v1.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.user.repository.UserRepository;

@Service
public class UserDetailsServiceForSecurity implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceForSecurity(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("존재하지 않은 유저입니다."));
  }
}