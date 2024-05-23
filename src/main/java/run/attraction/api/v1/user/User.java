package run.attraction.api.v1.user;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "profile_img")
  private String profileImg;

  @Column(name = "background_img")
  private String backgroundImg;

  @Column(name = "created_at")
  private LocalDate createdAt;

  @Column(name = "modified_at")
  private LocalDate modifiedAt;

  @Column(name = "is_deleted")
  @ColumnDefault("false")
  private boolean isDeleted;

  @Enumerated(EnumType.STRING)
  private Role role;

  /*
   * 간편로그인 후 추가로 받을 정보
   */

  @Column(name = "nick_Name", unique = true, length = 20)
  private String nickName;

  @ElementCollection(targetClass = Interest.class)
  @Enumerated(EnumType.STRING)
  @JoinTable(name = "interests", joinColumns = @JoinColumn(name = "user_id"))
  private Set<Interest> interests = new HashSet<>();

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "user_expiration")
  private LocalDate userExpiration;

  @Column(name = "job_code")
  private Integer jobCode;

  @Builder
  private User(
      String nickName,
      String email,
      String profileImg,
      String backgroundImg,
      LocalDate createdAt,
      LocalDate modifiedAt,
      boolean isDeleted,
      Role role
  ) {
    this.nickName = nickName;
    this.email = email;
    this.profileImg = profileImg;
    this.backgroundImg = backgroundImg;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.isDeleted = isDeleted;
    this.role = role;
  }

  public void addExtraDetails(UserValidator userValidator,
                              String nickName, List<String> interests, LocalDate birthDate,
                              LocalDate userExpiration, Integer jobCode) {
    if (userValidator.isSpecialPatternInNickname(nickName)) {
      throw new IllegalStateException("닉네임에 특수문자가 존재합니다.");
    }

    this.nickName = nickName;
    this.interests.addAll(getInterestFromString(interests));
    this.birthDate = birthDate;
    this.userExpiration = userExpiration;
    this.jobCode = jobCode;
  }

  private static List<Interest> getInterestFromString(List<String> interests) {
    return interests.stream()
        .map(Interest::valueOf)
        .toList();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}