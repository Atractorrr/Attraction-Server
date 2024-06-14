package run.attraction.api.v1.rank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.rank.ConsistencyRank;
import run.attraction.api.v1.rank.ExtensiveRank;
import run.attraction.api.v1.rank.Rank;
import run.attraction.api.v1.rank.service.calculator.ConsistencyRankCalculator;
import run.attraction.api.v1.rank.service.calculator.ExtensiveRankCalculator;
import run.attraction.api.v1.rank.service.dto.RankDetailDto;
import run.attraction.api.v1.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {

  private final ExtensiveRankCalculator extensiveRankCalculator;
  private final ConsistencyRankCalculator consistencyRankCalculator;
  private final UserRepository userRepository;

  public List<RankDetailDto> getTop10ExtensiveRank(LocalDate date) {
    List<ExtensiveRank> ranks = extensiveRankCalculator.getExtensiveRank(date);

    final List<String> emails = ranks.stream().map(ExtensiveRank::getEmail).toList();
    final List<Object[]> userInfos = userRepository.findProfileImgAndNicknameByUseremails(emails);

    return getRankDetailDtos(ranks, userInfos, emails);
  }

  public List<RankDetailDto> getTop10ConsistencyRank(LocalDate date) {
    List<ConsistencyRank> ranks = consistencyRankCalculator.getConsistencyRank(date);

    final List<String> emails = ranks.stream().map(ConsistencyRank::getEmail).toList();
    final List<Object[]> userInfos = userRepository.findProfileImgAndNicknameByUseremails(emails);

    return getRankDetailDtos(ranks, userInfos, emails);
  }

  private List<RankDetailDto> getRankDetailDtos(List< ? extends Rank> ranks,
                                                List<Object[]> userInfos,
                                                List<String> emails) {
    final Map<String, Integer> emailValueMap = getEmailValueMap(ranks);
    final Map<String, String> emailProfileImgMap = getEmailProfileImgMap(userInfos);
    final Map<String, String> emailNicknameMap = getEmailNicknameMap(userInfos);

    return emails.stream().map(email -> new RankDetailDto(
        emailNicknameMap.get(email),
        emailProfileImgMap.get(email),
        emailValueMap.get(email))
    ).toList();
  }

  private static Map<String, Integer> getEmailValueMap(List<? extends Rank> ranks) {
    return ranks.stream()
        .collect(Collectors.toMap(
            Rank::getEmail,
            Rank::getValue
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