package run.attraction.api.v1.user.init;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.user.Role;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserValidator;
import run.attraction.api.v1.user.repository.UserRepository;

import java.time.LocalDate;

//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class Userinit {
//
//  private final InitUserService initUserService;
//
//  @PostConstruct
//  public void init() {
//    initUserService.init();
//  }
//
//  @Service
//  @RequiredArgsConstructor
//  public static class InitUserService {
//    private final UserRepository userRepository;
//
//    private void init() {
//      final User user = User.builder()
//          .email("test@gmail.com")
//          .profileImg("test_profile_img")
//          .backgroundImg("test_background_img")
//          .createdAt(LocalDate.now())
//          .updateAt(LocalDate.now())
//          .role(Role.USER)
//          .build();
//
//      userRepository.save(user);
//
//      log.info("user email = {}", user.getEmail());
//    }
//  }
//}
