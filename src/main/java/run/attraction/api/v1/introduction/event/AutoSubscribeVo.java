package run.attraction.api.v1.introduction.event;

import org.springframework.util.Assert;

public record AutoSubscribeVo(
    String userEmail,
    String userName,
    String subscribeUrl,
    boolean isAgreePersonalInfoCollection,  // 개인정보 수집 및 이용 동의 여부
    boolean isAgreeAdInfoReception  // 광고성 정보 수신 동의 여부
) {
  public AutoSubscribeVo{
    Assert.hasText(userEmail, "userEmail이 존재하지 않습니다.");
    Assert.hasText(userName, "userName이 존재하지 않습니다.");
    Assert.hasText(subscribeUrl, "subscribeUrl이 존재하지 않습니다.");
  }
}
