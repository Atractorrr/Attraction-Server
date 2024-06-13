package run.attraction.api.v1.rank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.rank.ExtensiveRank;
import run.attraction.api.v1.rank.service.dto.RankDetailDto;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {

  private final RankCalculator rankCalculator;
  private final UserRepository userRepository;

  public List<RankDetailDto> getTop10ExtensiveRank(LocalDate date) {
    List<ExtensiveRank> ranks = rankCalculator.getExtensiveRank(date);

    final List<String> emails = ranks.stream().map(ExtensiveRank::getEmail).toList();
    final List<Object[]> userInfo = userRepository.findProfileImgAndNicknameByUseremails(emails);

    final Map<String, Integer> emailValueMap = getemailValudMap(ranks);
    final Map<String, String> emailProfileImgMap = getEmailProfileImgMap(userInfo);
    final Map<String, String> emailNicknameMap = getEmailNicknameMap(userInfo);

    return emails.stream().map(email -> new RankDetailDto(
        emailNicknameMap.get(email),
        emailProfileImgMap.get(email),
        emailValueMap.get(email))
    ).toList();
  }

  private static Map<String, Integer> getemailValudMap(List<ExtensiveRank> ranks) {
    return ranks.stream()
        .collect(Collectors.toMap(
            ExtensiveRank::getEmail,
            ExtensiveRank::getValue
        ));
  }

  private Map<String,String> getEmailProfileImgMap(List<Object[]> userInfo) {
    return userInfo.stream()
        .collect(Collectors.toMap(
            obj -> (String) obj[0],
            obj -> (String) obj[1]
        ));
  }

  private Map<String,String> getEmailNicknameMap(List<Object[]> userInfo) {
    return userInfo.stream()
        .collect(Collectors.toMap(
            obj -> (String) obj[0],
            obj -> (String) obj[2]
        ));
  }



}