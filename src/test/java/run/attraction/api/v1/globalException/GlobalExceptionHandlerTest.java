package run.attraction.api.v1.globalException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testIllegalArgumentException() throws Exception {
    // given: 테스트를 위한 초기 상태를 설정합니다.
    String url = "/test/illegal-argument";

    // when: 테스트할 동작을 실행합니다.
    mockMvc.perform(get(url))
        // then: 결과를 검증합니다.
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("IllegalArgumentException occurred"))
        .andExpect(jsonPath("$.status").value(400));
  }

  @Test
  public void testMethodArgumentNotValidException() throws Exception {
    // given: 테스트를 위한 초기 상태를 설정합니다.
    String url = "/test/method-argument-not-valid?value=-1";

    // when: 테스트할 동작을 실행합니다.
    mockMvc.perform(get(url))
        // then: 결과를 검증합니다.
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.status").value(400));
  }

  @Test
  public void testHttpRequestMethodNotSupportedException() throws Exception {
    // given: 테스트를 위한 초기 상태를 설정합니다.
    String url = "/test/get-only";

    // when: 테스트할 동작을 실행합니다.
    mockMvc.perform(post(url))
        // then: 결과를 검증합니다.
        .andExpect(status().isMethodNotAllowed())
        .andExpect(jsonPath("$.error").value("Method Not Allowed"))
        .andExpect(jsonPath("$.status").value(405));
  }

  @Test
  public void testConstraintViolationException() throws Exception {
    // given: 테스트를 위한 초기 상태를 설정합니다.
    String url = "/test/constraint-violation?value=-1";

    // when: 테스트할 동작을 실행합니다.
    mockMvc.perform(get(url))
        // then: 결과를 검증합니다.
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("Constraint violation"))
        .andExpect(jsonPath("$.status").value(400));
  }

  @Test
  public void testGlobalException() throws Exception {
    // given: 테스트를 위한 초기 상태를 설정합니다.
    String url = "/test/global-exception";

    // when: 테스트할 동작을 실행합니다.
    mockMvc.perform(get(url))
        // then: 결과를 검증합니다.
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.error").value("Internal Server Error"))
        .andExpect(jsonPath("$.status").value(500));
  }
}