package run.attraction.api.v1.introduction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

class CategoryTest {
  @Test
  public void testEnumValues() {
    // Category enum의 모든 값이 제대로 설정되었는지 테스트
    assertThat(Category.TREND_LIFE.getViewName()).isEqualTo("트렌드/라이프");
    assertThat(Category.ENTERTAINMENT.getViewName()).isEqualTo("엔터테이먼트");
    assertThat(Category.BUSINESS_FINANCIAL_TECHNOLOGY.getViewName()).isEqualTo("비즈/재테크");
    assertThat(Category.LOCAL_TRAVEL.getViewName()).isEqualTo("지역/여행");
    assertThat(Category.FOOD.getViewName()).isEqualTo("푸드");
    assertThat(Category.IT_TECH.getViewName()).isEqualTo("IT/테크");
    assertThat(Category.DESIGN.getViewName()).isEqualTo("디자인");
    assertThat(Category.CURRENT_AFFAIRS_SOCIETY.getViewName()).isEqualTo("시사/사회");
    assertThat(Category.HOBBY_SELF_DEVELOPMENT.getViewName()).isEqualTo("취미/자기개발");
    assertThat(Category.CULTURE_ART.getViewName()).isEqualTo("문화/예술");
    assertThat(Category.LIVING_INTERIOR.getViewName()).isEqualTo("리빙/인테리어");
    assertThat(Category.HEALTH_MEDICINE.getViewName()).isEqualTo("건강/의학");
  }

  @Test
  public void testEnumCount() {
    // Category enum의 값 개수가 맞는지 테스트
    assertThat(Category.values().length).isEqualTo(12);
  }

  @Test
  public void testEnumNames() {
    // Category enum의 이름이 제대로 설정되었는지 테스트
    assertThat(Category.valueOf("TREND_LIFE")).isNotNull();
    assertThat(Category.valueOf("ENTERTAINMENT")).isNotNull();
    assertThat(Category.valueOf("BUSINESS_FINANCIAL_TECHNOLOGY")).isNotNull();
    assertThat(Category.valueOf("LOCAL_TRAVEL")).isNotNull();
    assertThat(Category.valueOf("FOOD")).isNotNull();
    assertThat(Category.valueOf("IT_TECH")).isNotNull();
    assertThat(Category.valueOf("DESIGN")).isNotNull();
    assertThat(Category.valueOf("CURRENT_AFFAIRS_SOCIETY")).isNotNull();
    assertThat(Category.valueOf("HOBBY_SELF_DEVELOPMENT")).isNotNull();
    assertThat(Category.valueOf("CULTURE_ART")).isNotNull();
    assertThat(Category.valueOf("LIVING_INTERIOR")).isNotNull();
    assertThat(Category.valueOf("HEALTH_MEDICINE")).isNotNull();
  }
}