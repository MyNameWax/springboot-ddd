package cn.rzpt.framework.web.exception;

import cn.rzpt.framework.web.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
public class GlobalException extends Throwable {

    @ExceptionHandler(RuntimeException.class)
    public Result handleException(RuntimeException e) {
        log.error("【GlobalException】 -> （RunTimeException） =>:{}", e.getMessage());
        return Result.buildErrorResult(e.getMessage());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public Result handleException(AuthorizationDeniedException e) {
        log.error("【GlobalException】 -> （AuthorizationDeniedException） =>:{}", e.getMessage());
        return Result.buildErrorResult("权限不足");
    }

}
