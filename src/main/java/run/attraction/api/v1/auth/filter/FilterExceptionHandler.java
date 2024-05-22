package run.attraction.api.v1.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FilterExceptionHandler {

  private final ObjectMapper mapper;

  public FilterExceptionHandler(ObjectMapper objectMapper) {
    this.mapper = objectMapper;
  }

  public void sendExceptionMessage(HttpServletResponse response, ErrorType error) {
    response.setStatus(error.getCode());
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    try {
      String json = new ObjectMapper().writeValueAsString(
          FilterMessageResponseDto.builder()
              .errorCode(error.getCode())
              .message(error.getMessage())
              .build()
      );
      response.getWriter().write(json);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
