package run.attraction.api.v1.auth.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SessionFilterCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    // yml 설정값 가져오기
    return Boolean.parseBoolean(context.getEnvironment().getProperty("enable.session.filter"));
  }
}
