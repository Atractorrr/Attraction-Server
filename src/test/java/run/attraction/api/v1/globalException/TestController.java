package run.attraction.api.v1.globalException;

import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class TestController {

  @GetMapping("/test/illegal-argument")
  public String illegalArgument() {
    throw new IllegalArgumentException("IllegalArgumentException occurred");
  }

  @GetMapping("/test/method-argument-not-valid")
  public String methodArgumentNotValid(@RequestParam @Min(1) int value) {
    return "Valid";
  }

  @GetMapping("/test/get-only")
  public String getOnly() {
    return "GET only";
  }

  @GetMapping("/test/constraint-violation")
  public String constraintViolation(@RequestParam @Min(1) int value) {
    return "Valid";
  }

  @GetMapping("/test/global-exception")
  public String globalException() {
    throw new RuntimeException("Global exception occurred");
  }
}