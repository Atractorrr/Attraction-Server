package run.attraction.api.v1.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import run.attraction.api.v1.archive.AuditableEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Entity
@Table(name = "user_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail extends AuditableEntity {

  @Id
  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "nick_name", columnDefinition = "VARCHAR(20) BINARY")
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
  private UserDetail(String email,
                     String nickname,
                     List<String> interests,
                     LocalDate birthDate,
                     LocalDate userExpiration,
                     Occupation occupation) {
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
