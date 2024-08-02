package cn.rzpt.framework.common.handler;

import cn.hutool.json.JSONUtil;
import cn.rzpt.framework.web.result.Result;
import cn.rzpt.framework.common.toolkit.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 认证处理器
 */
@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Result result = new Result(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "请先登录");
        WebUtil.renderString(response, JSONUtil.toJsonStr(result));
    }
}
