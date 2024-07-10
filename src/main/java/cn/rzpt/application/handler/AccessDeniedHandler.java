package cn.rzpt.application.handler;

import cn.hutool.json.JSONUtil;
import cn.rzpt.common.Result;
import cn.rzpt.infrastructure.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * 授权处理器
 */
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        Result result = new Result(String.valueOf(HttpStatus.FORBIDDEN.value()), "权限不足");
        WebUtil.renderString(response, JSONUtil.toJsonStr(result));
    }
}
