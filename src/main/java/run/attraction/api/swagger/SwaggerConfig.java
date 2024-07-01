package run.attraction.api.swagger;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {

    Server localServer = new Server(); // 로컬 서버 설정
    localServer.setUrl("http://localhost:8080");
    localServer.setDescription("Local server");

    Server prodServer = new Server(); // 운영 서버 설정
    prodServer.setUrl("https://atrserver.store");
    prodServer.setDescription("Production server");

    Info info = new Info()
        .title("Attraction Server API")
        .version("v1.0.0")
        .description("개선중");

    return new OpenAPI()
        .info(info)
        .servers(List.of(localServer, prodServer));
  }
}