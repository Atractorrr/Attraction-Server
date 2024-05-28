package run.attraction.api.v1.mypage.service.archive.newsletter;

import java.util.List;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

public interface MypageNewsletterService {
  List<MypageNewsletterDetail> getSubscribesByEmail(String email);
}
