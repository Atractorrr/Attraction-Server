package run.attraction.api.v1.rank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.rank.service.RankService;
import run.attraction.api.v1.rank.service.dto.ExtensiveRankResponseDto;
import run.attraction.api.v1.rank.service.dto.ConsistencyRankResponseDto;
import run.attraction.api.v1.rank.service.dto.RankDetailDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
@Tag(name = "랭크", description = "RankController")
public class RankController {
  private final RankService rankService;

  @GetMapping("/article")
  @Operation(summary = "아티클 Top10", description = "")
  public ResponseEntity<?> getExtensiveRank(){
    final List<RankDetailDto> rankDetails = rankService.getTop10ExtensiveRank(LocalDate.now());
    return ResponseEntity.ok(new ExtensiveRankResponseDto(rankDetails));
  }

  @GetMapping("/streak")
  @Operation(summary = "스트릭 Top10", description = "")
  public ResponseEntity<?> getConsistencyRank(){
    final List<RankDetailDto> rankDetails = rankService.getTop10ConsistencyRank(LocalDate.now());
    return ResponseEntity.ok(new ConsistencyRankResponseDto(rankDetails));
  }
}
