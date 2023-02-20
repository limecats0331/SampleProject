package edu.limecats.sample.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("UUID", uuid);
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        log.info("[{}][{}][{}] con", uuid, method, requestURI);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uuid = (String) request.getAttribute("UUID");
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        log.info("[{}][{}][{}] con", uuid, method, requestURI);
    }
}
