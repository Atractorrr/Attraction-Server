package run.attraction.api.v1.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestLogFilter extends OncePerRequestFilter {
    private static final String REQUEST_ID = "request_id";
    public static final int LENGTH = 8;

    @Value("${path.prometheus}")
    public String prometheusUri;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (prometheusUri.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        long start = System.currentTimeMillis();

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        String requestId = CustomUUID.getCustomUUID(LENGTH);
        MDC.put(REQUEST_ID, requestId);

        filterChain.doFilter(requestWrapper, responseWrapper);

        long end = System.currentTimeMillis();
        log.info(
                HttpLogMessage.createInstance(
                        requestWrapper,
                        responseWrapper,
                        (double) (end - start) / 1000).toPrettierLog()
        );
        MDC.remove(REQUEST_ID);
    }

    private static final class CustomUUID {
        private static final int DEFAULT_START_NUMBER = 0;
        private CustomUUID() {
        }

        public static String getCustomUUID(int length) {
            return getRandomUUID()
                .substring(DEFAULT_START_NUMBER, length);
        }

        private static String getRandomUUID() {
            return UUID.randomUUID().toString();
        }
    }
}
