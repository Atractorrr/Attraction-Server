package run.attraction.api.v1.gmail.config;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  /* 해당 부분은 스프링 프레임워크 스펙에 따라 정해진 이름대로 빈 등록을 해주어야 함*/
  @Bean(name = APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
  public ApplicationEventMulticaster applicationEventMulticaster() {
    SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
    eventMulticaster.setTaskExecutor(getAsyncExecutor());
    return eventMulticaster;
  }

  @Bean("threadPoolTaskExecutor")
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(10);
    executor.setQueueCapacity(10000);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setAwaitTerminationSeconds(10);
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (Throwable ex, Method method, Object... params) -> {
      System.out.println("Exception message - " + ex.getMessage());
      System.out.println("Method name - " + method.getName());
      for (Object param : params) {
        System.out.println("Parameter value - " + param);
      }
    };
  }
}