package run.attraction.api.v1.mypage.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

public class MypageUserServiceTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private MypageUserServiceImpl mypageUserService;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    user = User.builder()
        .email("test@gmail.com")
        .profileImg("beforeProfileImg").build();

    when(userRepository.findById("test@gmail.com")).thenReturn(Optional.of(user));
  }

  @Test
  @DisplayName("프로필 사진 변경 요청 API 테스트")
  void updateProfileImgTest(){
    //Given
    String email = "test@gmail.com";
    String profileImg = "AfterProfileImg";

    //When
    mypageUserService.updateProfileImg(email, profileImg);

    //then
    assertEquals(profileImg,userRepository.findById(email).get().getProfileImg());
  }
}
