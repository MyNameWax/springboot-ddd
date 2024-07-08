package cn.rzpt.common;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class GlobalException extends Throwable {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.buildErrorResult(e.getMessage());
    }

}
