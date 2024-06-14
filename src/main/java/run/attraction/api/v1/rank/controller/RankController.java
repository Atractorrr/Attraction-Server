package run.attraction.api.v1.rank.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.rank.service.RankService;
import run.attraction.api.v1.rank.service.dto.RankResponseDto;
import run.attraction.api.v1.rank.service.dto.RankDetailDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class RankController {

  private final RankService rankService;

  @GetMapping("/extensive")
  public ResponseEntity<?> getExtensiveRank(){
    log.info("다독 랭킹 조회 시작");
    final List<RankDetailDto> rankDetails = rankService.getTop10ExtensiveRank(LocalDate.now());
    log.info("다독 랭킹 조회 완료");
    return ResponseEntity.ok(new RankResponseDto(rankDetails));
  }

  @GetMapping("/consistency")
  public ResponseEntity<?> getConsistencyRank(){
    log.info("꾸준함 랭킹 조회 시작");
    final List<RankDetailDto> rankDetails = rankService.getTop10ConsistencyRank(LocalDate.now());
    log.info("꾸준함 랭킹 조회 완료");
    return ResponseEntity.ok(new RankResponseDto(rankDetails));
  }
}
