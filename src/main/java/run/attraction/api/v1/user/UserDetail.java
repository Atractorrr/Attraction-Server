package run.attraction.api.v1.user;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import run.attraction.api.v1.archive.AuditableEntity;

@Slf4j
@Entity
@Table(name = "user_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail extends AuditableEntity {

  @Id
  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "nick_Name", unique = true, length = 20)
  private String nickname;

  @ElementCollection(targetClass = Interest.class)
  @Enumerated(EnumType.STRING)
  @JoinTable(name = "interests", joinColumns = @JoinColumn(name = "email"))
  private Set<Interest> interests = new HashSet<>();

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "user_expiration")
  private LocalDate userExpiration;

  @Enumerated(EnumType.STRING)
  @Column(name = "occupation")
  private Occupation occupation;

  @Builder
  private UserDetail(UserValidator userValidator,
                     String email,
                     String nickname,
                     List<String> interests,
                     LocalDate birthDate,
                     LocalDate userExpiration,
                     Occupation occupation) {
    if (userValidator.isSpecialPatternInNickname(nickname)) {
      throw new IllegalStateException("닉네임에 특수문자가 존재합니다.");
    }
    log.info("email 입력");
    this.email = email;
    this.nickname = nickname;
    this.interests.addAll(getInterestFromString(interests));
    this.birthDate = birthDate;
    this.userExpiration = userExpiration;
    this.occupation = occupation;
    log.info("userDetail 생성완료");
  }

  public void updateNickName(String nickname){
    this.nickname = nickname;
  }

  public void updateUserExpiration(LocalDate userExpiration){
    this.userExpiration = userExpiration;
  }

  public void updateInterest(List<String> interests){
    this.interests = interests.stream()
        .map(Interest::valueOf)
        .collect(Collectors.toSet());
  }

  public void updateOccupation(String occupation){
    this.occupation = Occupation.valueOf(occupation);
  }

  private static List<Interest> getInterestFromString(List<String> interests) {
    log.info("String -> Interest로 변환");
    return interests.stream()
        .map(Interest::valueOf)
        .collect(Collectors.toList());
  }
}
