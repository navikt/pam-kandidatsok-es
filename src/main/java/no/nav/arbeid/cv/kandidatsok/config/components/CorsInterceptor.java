package no.nav.arbeid.cv.kandidatsok.config.components;

import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static com.google.common.net.HttpHeaders.ORIGIN;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CorsInterceptor extends HandlerInterceptorAdapter {

  private final List<String> allowedOrigins;

  public CorsInterceptor(String... allowedOrigins) {
    this(Arrays.asList(allowedOrigins));
  }

  public CorsInterceptor(List<String> allowedOrigins) {
    this.allowedOrigins = allowedOrigins;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String origin = request.getHeader(ORIGIN);
    if (allowedOrigins.contains(origin)) {
      response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
      response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
    }
    return true;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " [allowedOrigins=" + allowedOrigins + "]";
  }
}
