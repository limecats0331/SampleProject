package edu.limecats.sample.exception;

import edu.limecats.sample.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNameErrorException.class)
    public ErrorDto userNameErrorException(Exception e, HttpServletRequest request) {
        String uuid = (String) request.getAttribute("UUID");
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        log.error("[{}][{}][{}][{}] error", uuid, method, requestURI, e.getClass().toString());
        return ErrorDto.builder()
                .errorName(e.getClass().toString())
                .errorMsg(e.getMessage())
                .build();
    }
}
