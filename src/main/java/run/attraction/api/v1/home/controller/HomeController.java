package run.attraction.api.v1.home.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.home.service.dto.categories.CategoriesResponseDto;
import run.attraction.api.v1.home.service.HomeService;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class HomeController {

  private final HomeService service;

  @GetMapping("/newsletters/categories")
  public ResponseEntity<CategoriesResponseDto> getCategories(@RequestParam String email) {
    final List<String> categories = service.getCategoriesByEmail(email);
    return ResponseEntity.ok(new CategoriesResponseDto(categories));
  }
}
