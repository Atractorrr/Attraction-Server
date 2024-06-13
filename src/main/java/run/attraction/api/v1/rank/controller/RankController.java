package run.attraction.api.v1.rank.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.rank.service.RankService;
import run.attraction.api.v1.rank.service.dto.ExtensiveRankResponseDto;
import run.attraction.api.v1.rank.service.dto.RankDetailDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class RankController {

  private final RankService rankService;

  @GetMapping("/extensive")
  public ResponseEntity<?> getExtensiveRank(){
    final List<RankDetailDto> rankDetails = rankService.getTop10ExtensiveRank(LocalDate.now().minusDays(1));
    return ResponseEntity.ok(new ExtensiveRankResponseDto(rankDetails));
  }
}
