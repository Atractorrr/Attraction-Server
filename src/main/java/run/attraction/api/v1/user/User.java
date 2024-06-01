package run.attraction.api.v1.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

  @Id
  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "profile_img")
  private String profileImg;

  @Column(name = "background_img")
  private String backgroundImg;

  @Column(name = "created_at")
  private LocalDate createdAt;

  @Column(name = "update_at")
  private LocalDate updateAt;

  @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
  private boolean isDeleted;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  private User(
      String email,
      String profileImg,
      String backgroundImg,
      LocalDate createdAt,
      LocalDate updateAt,
      Role role
  ) {
    this.email = email;
    this.profileImg = profileImg;
    this.backgroundImg = backgroundImg;
    this.createdAt = createdAt;
    this.updateAt = updateAt;
    this.role = role;
  }

  public void renewUpdateAt(LocalDate updateAt){
    this.updateAt = updateAt;
  }

  public void updateProfileImg(String profileImg){
    this.profileImg = profileImg;
  }

  public void updateBackgroundImg(String backgroundImg){
    this.backgroundImg = backgroundImg;
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
