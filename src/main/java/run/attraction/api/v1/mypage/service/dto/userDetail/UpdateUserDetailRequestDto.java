package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.List;

public record UpdateUserDetailRequestDto (
  String nickName,
  Integer userExpiration,
  List<String> interest,
  String occupation
){
}
