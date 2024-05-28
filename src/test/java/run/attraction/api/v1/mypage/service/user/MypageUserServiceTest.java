package run.attraction.api.v1.mypage.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.user.Interest;
import run.attraction.api.v1.user.Occupation;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserValidator;
import run.attraction.api.v1.user.repository.UserRepository;

public class MypageUserServiceTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private MypageUserServiceImpl mypageUserService;

  @InjectMocks
  private UserValidator userValidator;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    user = User.builder()
        .email("test@gmail.com")
        .profileImg("beforeProfileImg")
        .backgroundImg("beforeBackgroundImg")
        .createdAt(LocalDate.of(2024,1,1))
        .updateAt(LocalDate.of(2024,1,1))
        .build();

    user.addExtraDetails(
        userValidator,
        "Kim",
        List.of("TREND_LIFE","ENTERTAINMENT","BUSINESS_FINANCIAL_TECHNOLOGY"),
        LocalDate.of(1999,10,12),
        LocalDate.of(2024,6,1),
        Occupation.SERVICE);

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

  @Test
  @DisplayName("프로필 배경이미지 변경 요청 API 테스트")
  void updateBackgroundImg(){
    //Given
    String email = "test@gmail.com";
    String backgroundImg = "AfterBackgroundImg";

    //When
    mypageUserService.updateBackgroundImg(email, backgroundImg);

    //then
    assertEquals(backgroundImg,userRepository.findById(email).get().getBackgroundImg());
  }

  @Test
  @DisplayName("마이페이지 개인설정 API 테스트")
  void updateUserDetails(){
    //Given
    String email = "test@gmail.com";
    String afterNickname = "AfterNickname";
    Integer expiration = 12;
    List<String> interests = List.of("IT_TECH", "CURRENT_AFFAIRS_SOCIETY", "CULTURE_ART");
    String occupation="SELLING";

    //When
    mypageUserService.updateUserDetail(
        UpdateUserDetailDto.builder()
        .email(email)
        .nickName(afterNickname)
        .userExpiration(expiration)
        .interest(interests)
        .occupation(occupation)
        .build()
        );

    //then
    final User user = userRepository.findById(email).get();
    assertEquals(afterNickname,user.getNickName());
    assertEquals(LocalDate.of(2025,1,1),user.getUserExpiration());
    assertEquals(interests.stream().map(Interest::valueOf).collect(Collectors.toSet()),user.getInterests());
    assertEquals(Occupation.valueOf(occupation),user.getOccupation());
  }


}
